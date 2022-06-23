package com.example.demo.controller;

import com.example.demo.DTO.EducationDTO;
import com.example.demo.services.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;


@RestController
@RequestMapping("api/education")
public class EducationController {

    @Autowired
    private IEducationService educationService;

    @GetMapping("/{id}")
    public ResponseEntity<EducationDTO> findById(@PathVariable("id") Integer id){
        EducationDTO educationDTO = educationService.findById(id);
        return new ResponseEntity<>(educationDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EducationDTO> create(@RequestBody EducationDTO educationDTO){
        EducationDTO newEducationDTO = educationService.create(educationDTO);
        return new ResponseEntity<>(newEducationDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        educationService.deleteById(id);
        return new ResponseEntity<>("Education deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EducationDTO> update(@RequestBody EducationDTO educationDTO)throws ServerException{
        if(educationService.findById(educationDTO.getId()) == null){
            throw new ServerException("Education not found");
        }else{
            EducationDTO updateEducationDTO = educationService.update(educationDTO);
            return new ResponseEntity<>(updateEducationDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public Collection<EducationDTO> findAll() {
        return educationService.findAll();
    }
}
