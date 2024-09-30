package com.tq.mindset.controller;

import com.tq.mindset.dto.viewDto.BlogDto;
import com.tq.mindset.dto.createDto.CreateBlogDto;
import com.tq.mindset.dto.updateDto.UpdateBlogDto;
import com.tq.mindset.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@Valid @RequestBody CreateBlogDto createBlogDto) {
        System.out.println("Create Blog Controller");
        BlogDto createdBlog = blogService.createBlog(createBlogDto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable Long id) {
        BlogDto blog = blogService.getBlog(id);
        return ResponseEntity.ok(blog);
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @Valid @RequestBody UpdateBlogDto updateBlogDto) {
        BlogDto updatedBlog = blogService.updateBlog(id, updateBlogDto);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}