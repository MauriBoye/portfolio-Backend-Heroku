package com.example.demo.controller;

import com.example.demo.DTO.SkillDTO;
import com.example.demo.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;

@RestController
@RequestMapping("api/skill")
@CrossOrigin(origins = "*")
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> findById(@PathVariable("id") Integer id){
        SkillDTO skillDTO = skillService.findById(id);
        return new ResponseEntity<>(skillDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SkillDTO> create(@RequestBody SkillDTO skillDTO){
        SkillDTO newSkillDTO = skillService.create(skillDTO);
        return new ResponseEntity<>(newSkillDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        skillService.deleteById(id);
        return new ResponseEntity<>("Skill deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<SkillDTO> update(@RequestBody SkillDTO skillDTO) throws ServerException{
        if(skillService.findById(skillDTO.getId())==null){
            throw new ServerException(("Skill not found"));
        }else{
            SkillDTO updateSkillDTO = skillService.update(skillDTO);
            return new ResponseEntity<>(updateSkillDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public Collection<SkillDTO> findAll(){
        return skillService.findAll();
    }
}
