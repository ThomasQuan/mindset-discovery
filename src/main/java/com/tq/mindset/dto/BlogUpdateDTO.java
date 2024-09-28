package com.tq.mindset.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BlogUpdateDTO {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String subtitle;

    @NotBlank(message = "Content is required")
    private String content;
}