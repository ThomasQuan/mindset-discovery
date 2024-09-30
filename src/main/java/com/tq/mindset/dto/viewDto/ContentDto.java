package com.tq.mindset.dto.viewDto;

import com.tq.mindset.domain.enums.ContentStatus;
import lombok.Data;
import java.util.Set;

@Data
public class ContentDto {
    private Long id;
    private String title;
    private String slug;
    private String markdownContent;
    private String htmlContent;
    private ContentStatus status;
    private int orderNo;
    private String objectAs;
    private Set<Asset> assets;
    private Long languageId;
    private Long blogId;
    private Long projectId;
}