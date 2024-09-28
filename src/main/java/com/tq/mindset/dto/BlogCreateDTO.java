package com.tq.mindset.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class BlogCreateDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private String subtitle;

    @NotBlank(message = "Content is required")
    private String content;

    private Set<Long> tagIds;  // Assuming you want to link tags by their IDs
}
