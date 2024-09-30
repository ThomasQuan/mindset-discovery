package com.tq.mindset.mapper;

import com.tq.mindset.domain.Tag;
import com.tq.mindset.dto.viewDto.TagDto;
import com.tq.mindset.dto.createDto.CreateTagDto;
import com.tq.mindset.dto.updateDto.UpdateTagDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag createDtoToTag(CreateTagDto createTagDto);

    void updateTagFromDto(UpdateTagDto updateTagDto, @MappingTarget Tag tag);

    TagDto tagToDto(Tag tag);

    Tag dtoToTag(TagDto tagDto);
}