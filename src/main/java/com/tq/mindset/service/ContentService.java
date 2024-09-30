package com.tq.mindset.service;

import com.tq.mindset.domain.Content;
import com.tq.mindset.dto.viewDto.ContentDto;
import com.tq.mindset.dto.createDto.CreateContentDto;
import com.tq.mindset.dto.updateDto.UpdateContentDto;
import com.tq.mindset.mapper.ContentMapper;
import com.tq.mindset.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Autowired
    public ContentService(ContentRepository contentRepository, ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
    }

    @Transactional
    public ContentDto createContent(CreateContentDto createContentDto) {
        Content content = contentMapper.createDtoToContent(createContentDto);
        content = contentRepository.save(content);
        return contentMapper.contentToDto(content);
    }

    @Transactional(readOnly = true)
    public ContentDto getContent(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Content not found"));
        return contentMapper.contentToDto(content);
    }

    @Transactional(readOnly = true)
    public List<ContentDto> getAllContents() {
        return contentRepository.findAll().stream()
                .map(contentMapper::contentToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContentDto updateContent(Long id, UpdateContentDto updateContentDto) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Content not found"));
        contentMapper.updateContentFromDto(updateContentDto, content);
        content = contentRepository.save(content);
        return contentMapper.contentToDto(content);
    }

    @Transactional
    public void deleteContent(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new EntityNotFoundException("Content not found");
        }
        contentRepository.deleteById(id);
    }
}