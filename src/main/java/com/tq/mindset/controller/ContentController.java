package com.tq.mindset.controller;

import com.tq.mindset.dto.viewDto.ContentDto;
import com.tq.mindset.dto.createDto.CreateContentDto;
import com.tq.mindset.dto.updateDto.UpdateContentDto;
import com.tq.mindset.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity<ContentDto> createContent(@Valid @RequestBody CreateContentDto createContentDto) {
        ContentDto createdContent = contentService.createContent(createContentDto);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getContent(@PathVariable Long id) {
        ContentDto content = contentService.getContent(id);
        return ResponseEntity.ok(content);
    }

    @GetMapping
    public ResponseEntity<List<ContentDto>> getAllContents() {
        List<ContentDto> contents = contentService.getAllContents();
        return ResponseEntity.ok(contents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentDto> updateContent(@PathVariable Long id, @Valid @RequestBody UpdateContentDto updateContentDto) {
        ContentDto updatedContent = contentService.updateContent(id, updateContentDto);
        return ResponseEntity.ok(updatedContent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}