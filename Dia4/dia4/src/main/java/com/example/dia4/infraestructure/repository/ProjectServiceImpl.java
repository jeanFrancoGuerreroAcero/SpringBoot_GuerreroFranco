package com.example.dia4.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dia4.applicatin.service.ProjectService;
import com.example.dia4.domain.Project;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository _projectRepository){
        projectRepository = _projectRepository;
    }

    @Override
    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

}
