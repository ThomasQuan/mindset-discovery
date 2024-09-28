package com.tq.mindset.service;

import com.tq.mindset.domain.Blog;
import com.tq.mindset.dto.BlogCreateDTO;
import com.tq.mindset.dto.BlogUpdateDTO;
import com.tq.mindset.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow();
    }

    public Blog createBlog(BlogCreateDTO blogCreateDTO) {
        Blog blog = new Blog();
        blog.setTitle(blogCreateDTO.getTitle());
        blog.setDescription(blogCreateDTO.getDescription());
        blog.setSubtitle(blogCreateDTO.getSubtitle());
        blog.setContent(blogCreateDTO.getContent());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());

        // Handle tags if necessary, e.g., fetching from the database using tagIds
        // blog.setTags(...);

        return blogRepository.save(blog);
    }


    // Method to edit a single blog
    public Blog editBlog(Long id, BlogUpdateDTO updatedBlogDTO) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));

        // Update fields from the DTO
        existingBlog.setTitle(updatedBlogDTO.getTitle());
        existingBlog.setDescription(updatedBlogDTO.getDescription());
        existingBlog.setSubtitle(updatedBlogDTO.getSubtitle());
        existingBlog.setContent(updatedBlogDTO.getContent());
        // Do not set ID or other sensitive fields

        return blogRepository.save(existingBlog);
    }
}
