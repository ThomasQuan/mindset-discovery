package com.tq.mindset.dto.createDto;

import lombok.Data;
import java.util.Set;

@Data
public class CreateBlogDto {
    private String title;
    private String subtitle;
    private String description;
    private Set<Long> tagIds;
    private Long userId;
    private Set<CreateContentDto> contents;
    private int likedCount;
    private int dislikedCount;
    private int visitedCount;
}