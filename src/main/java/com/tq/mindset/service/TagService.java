package com.tq.mindset.service;

import com.tq.mindset.domain.Tag;
import com.tq.mindset.dto.viewDto.TagDto;
import com.tq.mindset.dto.createDto.CreateTagDto;
import com.tq.mindset.dto.updateDto.UpdateTagDto;
import com.tq.mindset.mapper.TagMapper;
import com.tq.mindset.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Transactional
    public TagDto createTag(CreateTagDto createTagDto) {
        Tag tag = tagMapper.createDtoToTag(createTagDto);
        tag.setCreatedAt(LocalDateTime.now());
        tag.setUpdatedAt(LocalDateTime.now());
        tag.setSlug(createSlug(createTagDto.getTitle()));
        tag = tagRepository.save(tag);
        return tagMapper.tagToDto(tag);
    }

    @Transactional(readOnly = true)
    public TagDto getTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        return tagMapper.tagToDto(tag);
    }

    @Transactional(readOnly = true)
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::tagToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TagDto updateTag(Long id, UpdateTagDto updateTagDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        tagMapper.updateTagFromDto(updateTagDto, tag);
        tag.setUpdatedAt(LocalDateTime.now());
        if (!tag.getTitle().equals(updateTagDto.getTitle())) {
            tag.setSlug(createSlug(updateTagDto.getTitle()));
        }
        tag = tagRepository.save(tag);
        return tagMapper.tagToDto(tag);
    }

    @Transactional
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException("Tag not found");
        }
        tagRepository.deleteById(id);
    }

    private String createSlug(String title) {
        return title.toLowerCase().replaceAll("[^a-z0-9]+", "-");
    }
}