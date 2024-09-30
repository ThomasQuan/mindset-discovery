package com.tq.mindset.dto.viewDto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BlogDto {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean hasEdited;
    private Set<TagDto> tags;
    private UserDto user;
    private Set<ContentDto> contents;
    private int likedCount;
    private int dislikedCount;
    private int visitedCount;
}