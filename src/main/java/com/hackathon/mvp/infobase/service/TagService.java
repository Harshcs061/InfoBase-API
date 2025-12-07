package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    Tag getTagById(Long id);

    List<Tag> getAllTags();
}
