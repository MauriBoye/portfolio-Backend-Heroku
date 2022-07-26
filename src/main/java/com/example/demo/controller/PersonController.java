package com.example.demo.controller;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;


@RestController
@RequestMapping("api/person")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Integer id){
        PersonDTO personDTO = personService.findById(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO){
        PersonDTO newPersonDTO = personService.create(personDTO);
        return new ResponseEntity<>(newPersonDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        personService.deleteById(id);
        return new ResponseEntity<>("Patient deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO)throws ServerException {
        if(personService.findById(personDTO.getId()) == null){
            throw new ServerException("Person not found");
        }else{
            PersonDTO updatePatientDTO = personService.update(personDTO);
            return new ResponseEntity<>(updatePatientDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public Collection<PersonDTO> findAll() {
        return personService.findAll();
    }
}
