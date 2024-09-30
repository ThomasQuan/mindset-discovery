package com.tq.mindset.dto.updateDto;

import com.tq.mindset.dto.createDto.CreateContentDto;
import lombok.Data;
import java.util.Set;

@Data
public class UpdateBlogDto {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private Set<Long> tagIds;
    private Set<UpdateContentDto> contents;
    private int likedCount;
    private int dislikedCount;
    private int visitedCount;
}
