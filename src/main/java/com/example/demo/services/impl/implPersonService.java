package com.example.demo.services.impl;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.entities.Person;
import com.example.demo.repository.IPersonRepository;
import com.example.demo.services.IPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class implPersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public PersonDTO findById(@NotNull Integer id) {
        Person person = personRepository.getById(id);
        return mapDTO(person);
    }

    @Override
    public PersonDTO create(@NotNull PersonDTO personDTO) {
        Person person = mapEntity(personDTO);
        Person newPersonSave = personRepository.save(person);
        return mapDTO(newPersonSave);
    }

    @Override
    public void deleteById(@NotNull Integer id) {
    Person person = personRepository.getById(id);
    personRepository.delete(person);
    }

    @Override
    public PersonDTO update(@NotNull PersonDTO personDTO) {
        Person person = mapEntity(personDTO);
        Person newPersonSave = personRepository.save(person);
        return mapDTO(newPersonSave);
    }

    @Override
    public Set<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        Set<PersonDTO> personDTOSet = new HashSet<>();
        for (Person person : personList){
            personDTOSet.add(mapDTO(person));
        }
        return personDTOSet;
    }

    //----------Mapper----------
    private PersonDTO mapDTO(Person person){
        PersonDTO personDTO = objectMapper.convertValue(person, PersonDTO.class);
        return personDTO;
    }

    private Person mapEntity(PersonDTO personDTO){
        Person person = objectMapper.convertValue(personDTO, Person.class);
        return person;
    }
}
