package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.model.Tag;
import com.hackathon.mvp.infobase.respository.TagRepository;
import com.hackathon.mvp.infobase.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}

