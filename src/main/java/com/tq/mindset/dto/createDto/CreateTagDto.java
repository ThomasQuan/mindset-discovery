package com.tq.mindset.dto.createDto;

import lombok.Data;

@Data
public class CreateTagDto {
    private String title;
    private String description;
    private String color;
    private String category;
    private int categoryOrder;
    private int tagOrder;
}
