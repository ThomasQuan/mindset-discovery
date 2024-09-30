package com.tq.mindset.mapper;

import com.tq.mindset.domain.Blog;
import com.tq.mindset.dto.viewDto.BlogDto;
import com.tq.mindset.dto.createDto.CreateBlogDto;
import com.tq.mindset.dto.updateDto.UpdateBlogDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {UserMapper.class, TagMapper.class, ContentMapper.class})
public interface BlogMapper {

    // Create mapping: contents are ignored
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "contents", ignore = true)  // Ignore contents during blog creation
    Blog createDtoToBlog(CreateBlogDto createBlogDto);

    // Update mapping: contents are ignored to prevent overwriting or editing
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contents", ignore = true)  // Ignore contents during blog update
    void updateBlogFromDto(UpdateBlogDto updateBlogDto, @MappingTarget Blog blog);

    // DTO to entity mapping: contents are ignored to avoid relationship changes
    BlogDto blogToDto(Blog blog);

    // Inverse mapping: contents are ignored to prevent modification during inverse mapping
    @InheritInverseConfiguration
    @Mapping(target = "contents", ignore = true)  // Ensure contents are ignored when mapping back
    Blog dtoToBlog(BlogDto blogDto);

    @AfterMapping
    default void linkContents(@MappingTarget Blog blog) {
        if (blog.getContents() != null) {
            System.out.println("Hello");
            blog.getContents().forEach(content -> content.setBlog(blog));
        }
    }
}
