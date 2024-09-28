package com.tq.mindset.controller;

import com.tq.mindset.domain.Blog;
import com.tq.mindset.dto.BlogCreateDTO;
import com.tq.mindset.dto.BlogUpdateDTO;
import com.tq.mindset.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PostMapping
    public Blog createBlog(@RequestBody BlogCreateDTO blog) {
        return blogService.createBlog(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable Long id, @RequestBody BlogUpdateDTO updatedBlogDTO) {
        Blog updatedBlog = blogService.editBlog(id, updatedBlogDTO);
        return ResponseEntity.ok(updatedBlog);
    }
}
