package com.example.demo.services.impl;

import com.example.demo.DTO.ExperienceDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.entities.Experience;
import com.example.demo.entities.Person;
import com.example.demo.repository.IExperienceRepository;
import com.example.demo.services.IExperienceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class implExperienceService implements IExperienceService {

    @Autowired
    private IExperienceRepository experienceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ExperienceDTO findById(@NotNull Integer id) {
        Experience experience = experienceRepository.getById(id);
        return mapDTO(experience);
    }

    @Override
    public ExperienceDTO create(ExperienceDTO experienceDTO) {
        Experience experience = mapEntity(experienceDTO);
        Experience newExperienceSave = experienceRepository.save(experience);
        return mapDTO(newExperienceSave);
    }

    @Override
    public void deleteById(Integer id) {
        Experience experience = experienceRepository.getById(id);
        experienceRepository.delete(experience);
    }

    @Override
    public ExperienceDTO update(ExperienceDTO experienceDTO) {
        Experience experience = mapEntity(experienceDTO);
        Experience newExperienceSave = experienceRepository.save(experience);
        return mapDTO(newExperienceSave);
    }

    @Override
    public Set<ExperienceDTO> findAll() {
        List<Experience> experienceList = experienceRepository.findAll();
        Set<ExperienceDTO> experienceDTOSet = new HashSet<>();
        for (Experience experience : experienceList){
            experienceDTOSet.add(mapDTO(experience));
        }
        return experienceDTOSet;
    }

    //----------Mapper----------
    private ExperienceDTO mapDTO(Experience experience){
        ExperienceDTO experienceDTO = objectMapper.convertValue(experience, ExperienceDTO.class);
        return experienceDTO;
    }

    private Experience mapEntity(ExperienceDTO experienceDTO){
        Experience experience = objectMapper.convertValue(experienceDTO, Experience.class);
        return experience;
    }
}
