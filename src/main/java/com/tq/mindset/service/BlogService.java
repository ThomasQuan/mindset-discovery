package com.tq.mindset.service;

import com.tq.mindset.domain.Blog;
import com.tq.mindset.domain.Content;
import com.tq.mindset.domain.Tag;
import com.tq.mindset.domain.User;
import com.tq.mindset.dto.createDto.CreateBlogDto;
import com.tq.mindset.dto.createDto.CreateContentDto;
import com.tq.mindset.dto.updateDto.UpdateBlogDto;
import com.tq.mindset.dto.updateDto.UpdateContentDto;
import com.tq.mindset.dto.viewDto.BlogDto;
import com.tq.mindset.mapper.BlogMapper;
import com.tq.mindset.mapper.ContentMapper;
import com.tq.mindset.repository.BlogRepository;
import com.tq.mindset.repository.ContentRepository;
import com.tq.mindset.repository.TagRepository;
import com.tq.mindset.repository.UserRepository;
import com.tq.mindset.utils.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final ContentMapper contentMapper;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;
    private final Slugify slugify;

    @Autowired
    public BlogService(BlogRepository blogRepository, BlogMapper blogMapper,
                       UserRepository userRepository, TagRepository tagRepository,
                       ContentRepository contentRepository, ContentMapper contentMapper, Slugify slugify) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
        this.slugify = slugify;
    }

    @Transactional
    public BlogDto createBlog(CreateBlogDto createBlogDto) {
        Blog blog = blogMapper.createDtoToBlog(createBlogDto);
        User user = userRepository.findById(createBlogDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        blog.setUser(user);

        if (createBlogDto.getTagIds() != null) {
            List<Tag> tags = tagRepository.findAllById(createBlogDto.getTagIds());
            blog.setTags(new HashSet<>(tags));
        }

        // Save the blog first to get its ID
        Blog savedBlog = blogRepository.save(blog);

        // Create and set the contents
        if (createBlogDto.getContents() != null) {
            Blog finalSavedBlog = savedBlog;
            Set<Content> contents = createBlogDto.getContents().stream()
                    .map(contentDto -> {
                        Content content = contentMapper.createDtoToContent(contentDto);
                        content.setBlog(finalSavedBlog);
                        content.setSlug(slugify.generateSlug(content.getTitle())); // Generate slug from title
                        return content;
                    })
                    .collect(Collectors.toSet());
            savedBlog.setContents(contents);
        }

        // Save the blog again with the new contents
        savedBlog = blogRepository.save(savedBlog);
        return blogMapper.blogToDto(savedBlog);
    }

    @Transactional(readOnly = true)
    public BlogDto getBlog(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found"));
        return blogMapper.blogToDto(blog);
    }

    @Transactional(readOnly = true)
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll().stream()
                .map(blogMapper::blogToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BlogDto updateBlog(Long id, UpdateBlogDto updateBlogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Blog not found"));

        // Handle updating existing contents and creating new ones
        if (updateBlogDto.getContents() != null) {
            Set<Content> updatedContents = new HashSet<>();

            for (UpdateContentDto contentDto : updateBlogDto.getContents()) {
                Content content;
                // If the content has an ID, try to find and update it
                if (contentDto.getId() != null) {
                    content = contentRepository.findById(contentDto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Content not found"));
                    contentMapper.updateContentFromDto(contentDto, content);
                    // Update slug if title has changed
                    if (!content.getTitle().equals(contentDto.getTitle())) {
                        content.setSlug(slugify.generateSlug(contentDto.getTitle()));
                    }
                } else {
                    // If no ID, create a new content
                    CreateContentDto createContentDto = contentMapper.updateContentDtoToCreateContentDto(contentDto);
                    content = contentMapper.createDtoToContent(createContentDto);
                    content.setBlog(blog); // Ensure new content is associated with the blog
                    content.setSlug(slugify.generateSlug(content.getTitle())); // Generate slug for new content
                }

                // Save updated or new content
                updatedContents.add(contentRepository.save(content));
            }
            blog.getContents().clear(); // Clear existing contents
            blog.getContents().addAll(updatedContents); // Add updated contents
        }

        blogMapper.updateBlogFromDto(updateBlogDto, blog);
        blog = blogRepository.save(blog);
        return blogMapper.blogToDto(blog);
    }

    @Transactional
    public void deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new EntityNotFoundException("Blog not found");
        }
        blogRepository.deleteById(id);
    }
}