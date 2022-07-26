package com.example.demo.controller;

import com.example.demo.DTO.ExperienceDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.services.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;


@RestController
@RequestMapping("api/experience")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")
public class ExperienceController {

    @Autowired
    private IExperienceService experienceService;

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> findById(@PathVariable("id") Integer id){
        ExperienceDTO experienceDTO = experienceService.findById(id);
        return new ResponseEntity<>(experienceDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ExperienceDTO> create(@RequestBody ExperienceDTO experienceDTO){
        ExperienceDTO newExperienceDTO = experienceService.create(experienceDTO);
        return new ResponseEntity<>(newExperienceDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        experienceService.deleteById(id);
        return new ResponseEntity<>("Experience deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ExperienceDTO> update(@RequestBody ExperienceDTO experienceDTO)throws ServerException {
        if(experienceService.findById(experienceDTO.getId()) == null){
            throw new ServerException("Experience not found");
        }else{
            ExperienceDTO updateExperienceDTO = experienceService.update(experienceDTO);
            return new ResponseEntity<>(updateExperienceDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public Collection<ExperienceDTO> findAll() {
        return experienceService.findAll();
    }
}
