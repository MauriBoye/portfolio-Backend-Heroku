package com.example.demo.services.impl;

import com.example.demo.DTO.ProjectDTO;
import com.example.demo.entities.Project;
import com.example.demo.repository.IProjectRepository;
import com.example.demo.services.IProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class implProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ProjectDTO findById(@NotNull Integer id) {
        Project project = projectRepository.getById(id);
        return mapDTO(project);
    }

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        Project project = mapEntity(projectDTO);
        Project newProjectSave = projectRepository.save(project);
        return mapDTO(newProjectSave);
    }

    @Override
    public void deleteById(Integer id) {
        Project project = projectRepository.getById(id);
        projectRepository.delete(project);
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        Project project = mapEntity(projectDTO);
        Project newProjectSave = projectRepository.save(project);
        return mapDTO(newProjectSave);
    }

    @Override
    public Set<ProjectDTO> findAll() {
        List<Project> projectList = projectRepository.findAll();
        Set<ProjectDTO> projectDTOSet = new HashSet<>();
        for (Project project : projectList){
            projectDTOSet.add(mapDTO(project));
        }
        return projectDTOSet;
    }

    //----------Mapper----------
    private ProjectDTO mapDTO(Project project){
        ProjectDTO projectDTO = objectMapper.convertValue(project, ProjectDTO.class);
        return projectDTO;
    }

    private Project mapEntity(ProjectDTO projectDTO){
        Project project = objectMapper.convertValue(projectDTO, Project.class);
        return project;
    }
}
