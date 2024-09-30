package com.tq.mindset.dto.updateDto;

import com.tq.mindset.domain.enums.ContentStatus;
import lombok.Data;
import java.util.Set;

@Data
public class UpdateContentDto {
    private Long id;
    private String title;
    private String markdownContent;
    private String htmlContent;
    private ContentStatus status;
    private int orderNo;
    private String objectAs;
    private Set<Long> assetIds;
}