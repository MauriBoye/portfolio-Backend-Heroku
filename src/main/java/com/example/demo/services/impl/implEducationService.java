package com.example.demo.services.impl;

import com.example.demo.DTO.EducationDTO;
import com.example.demo.entities.Education;
import com.example.demo.repository.IEducationRepository;
import com.example.demo.services.IEducationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class implEducationService implements IEducationService {

    @Autowired
    private IEducationRepository educationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public EducationDTO findById(Integer id) {
        Education education = educationRepository.getById(id);
        return mapDTO(education);
    }

    @Override
    public EducationDTO create(EducationDTO educationDTO) {
        Education education = mapEntity(educationDTO);
        Education newEducationSave = educationRepository.save(education);
        return mapDTO(newEducationSave);
    }

    @Override
    public void deleteById(Integer id) {
        Education education = educationRepository.getById(id);
        educationRepository.delete(education);
    }

    @Override
    public EducationDTO update(EducationDTO educationDTO) {
        Education education = mapEntity(educationDTO);
        Education newEducationSave = educationRepository.save(education);
        return mapDTO(newEducationSave);
    }

    @Override
    public Set<EducationDTO> findAll() {
        List<Education> educationList = educationRepository.findAll();
        Set<EducationDTO> educationDTOSet = new HashSet<>();
        for (Education education : educationList){
            educationDTOSet.add(mapDTO(education));
        }
        return educationDTOSet;
    }

    //----------Mapper----------
    private EducationDTO mapDTO(Education education){
        EducationDTO educationDTO = objectMapper.convertValue(education, EducationDTO.class);
        return educationDTO;
    }

    private Education mapEntity(EducationDTO educationDTO){
        Education education = objectMapper.convertValue(educationDTO, Education.class);
        return education;
    }
}
