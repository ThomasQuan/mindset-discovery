package com.tq.mindset.dto.updateDto;

import lombok.Data;

@Data
public class UpdateTagDto {
    private Long id;
    private String title;
    private String description;
    private String color;
    private String category;
    private int categoryOrder;
    private int tagOrder;
}