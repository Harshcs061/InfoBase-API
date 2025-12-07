package com.hackathon.mvp.infobase.serviceImpl;

import com.hackathon.mvp.infobase.model.Project;
import com.hackathon.mvp.infobase.model.Tag;
import com.hackathon.mvp.infobase.respository.ProjectRepository;
import com.hackathon.mvp.infobase.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }
}
