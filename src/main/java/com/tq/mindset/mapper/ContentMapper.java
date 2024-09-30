package com.tq.mindset.mapper;

import com.tq.mindset.domain.Content;
import com.tq.mindset.dto.viewDto.ContentDto;
import com.tq.mindset.dto.createDto.CreateContentDto;
import com.tq.mindset.dto.updateDto.UpdateContentDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    @Mapping(target = "blog", ignore = true)
    Content createDtoToContent(CreateContentDto createContentDto);

    void updateContentFromDto(UpdateContentDto updateContentDto, @MappingTarget Content content);

    @Mapping(source = "blog.id", target = "blogId")
    ContentDto contentToDto(Content content);

    @InheritInverseConfiguration
    Content dtoToContent(ContentDto contentDto);

    CreateContentDto updateContentDtoToCreateContentDto(UpdateContentDto updateContentDto);
}