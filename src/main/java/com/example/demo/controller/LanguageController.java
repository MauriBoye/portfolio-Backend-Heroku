package com.example.demo.controller;

import com.example.demo.DTO.LanguageDTO;
import com.example.demo.services.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;


@RestController
@RequestMapping("api/language")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")
public class LanguageController {

    @Autowired
    private ILanguageService languageService;

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> findById(@PathVariable("id") Integer id){
        LanguageDTO languageDTO = languageService.findById(id);
        return new ResponseEntity<>(languageDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LanguageDTO> create(@RequestBody LanguageDTO languageDTO){
        LanguageDTO newLanguageDTO = languageService.create(languageDTO);
        return new ResponseEntity<>(newLanguageDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        languageService.deleteById(id);
        return new ResponseEntity<>("Language deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<LanguageDTO> update(@RequestBody LanguageDTO languageDTO)throws ServerException{
        if(languageService.findById(languageDTO.getId())==null){
            throw new ServerException("Language not found");
        }else{
            LanguageDTO updateLanguageDTO = languageService.update(languageDTO);
            return new ResponseEntity<>(updateLanguageDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public Collection<LanguageDTO> findAll(){
        return languageService.findAll();
    }

}
