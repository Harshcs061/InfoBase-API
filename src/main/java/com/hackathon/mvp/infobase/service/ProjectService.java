package com.hackathon.mvp.infobase.service;

import com.hackathon.mvp.infobase.model.Project;
import com.hackathon.mvp.infobase.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project getProjectById(Long id);

    List<Project> getAllProject();
}
