package com.example.demo.services.impl;

import com.example.demo.DTO.SkillDTO;
import com.example.demo.entities.Skill;
import com.example.demo.repository.ISkillRepository;
import com.example.demo.services.ISkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class implSkillService implements ISkillService {

    @Autowired
    private ISkillRepository skillRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SkillDTO findById(@NotNull Integer id) {
        Skill skill = skillRepository.getById(id);
        return mapDTO(skill);
    }

    @Override
    public SkillDTO create(SkillDTO skillDTO) {
       Skill skill = mapEntity(skillDTO);
       Skill newSkillSave = skillRepository.save(skill);
       return mapDTO(newSkillSave);
    }

    @Override
    public void deleteById(Integer id) {
        Skill skill = skillRepository.getById(id);
        skillRepository.delete(skill);
    }

    @Override
    public SkillDTO update(SkillDTO skillDTO) {
        Skill skill = mapEntity(skillDTO);
        Skill newSkillSave = skillRepository.save(skill);
        return mapDTO(newSkillSave);
    }

    @Override
    public Set<SkillDTO> findAll() {
        List<Skill> skillList = skillRepository.findAll();
        Set<SkillDTO> skillDTOSet = new HashSet<>();
        for (Skill skill : skillList){
            skillDTOSet.add(mapDTO(skill));
        }
        return skillDTOSet;
    }

    //----------Mapper----------
    private SkillDTO mapDTO(Skill skill){
        SkillDTO skillDTO = objectMapper.convertValue(skill, SkillDTO.class);
        return skillDTO;
    }

    private Skill mapEntity(SkillDTO skillDTO){
        Skill skill = objectMapper.convertValue(skillDTO, Skill.class);
        return skill;
    }
}
