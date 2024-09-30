package com.tq.mindset.dto.viewDto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TagDto {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String color;
    private String category;
    private int categoryOrder;
    private int tagOrder;
}