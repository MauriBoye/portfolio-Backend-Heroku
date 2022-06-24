package com.example.demo.controller;

import com.example.demo.DTO.ProjectDTO;
import com.example.demo.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;


@RestController
@RequestMapping("api/project")
@CrossOrigin(origins = "https://portfolio-arg-progama.web.app/", maxAge = 3600, allowCredentials="true")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable("id") Integer id){
        ProjectDTO projectDTO = projectService.findById(id);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO projectDTO){
        ProjectDTO newProjectDTO = projectService.create(projectDTO);
        return new ResponseEntity<>(newProjectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        projectService.deleteById(id);
        return new ResponseEntity<>("Project deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO projectDTO) throws ServerException{
        if(projectService.findById(projectDTO.getId())==null){
            throw new ServerException("Project not found");
        }else{
            ProjectDTO updateProjectDTO = projectService.update(projectDTO);
            return new ResponseEntity<>(updateProjectDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public Collection<ProjectDTO> findAll(){
        return projectService.findAll();
    }
}
