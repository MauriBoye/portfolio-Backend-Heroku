package com.example.demo.services.impl;

import com.example.demo.DTO.LanguageDTO;
import com.example.demo.entities.Language;
import com.example.demo.repository.ILanguageRepository;
import com.example.demo.services.ILanguageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class implLanguageService implements ILanguageService {

    @Autowired
    private ILanguageRepository languageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public LanguageDTO findById(@NotNull Integer id) {
        Language language = languageRepository.getById(id);
        return mapDTO(language);
    }

    @Override
    public LanguageDTO create(LanguageDTO languageDTO) {
        Language language = mapEntity(languageDTO);
        Language newLanguageSave = languageRepository.save(language);
        return mapDTO(newLanguageSave);
    }

    @Override
    public void deleteById(Integer id) {
        Language language = languageRepository.getById(id);
        languageRepository.delete(language);
    }

    @Override
    public LanguageDTO update(LanguageDTO languageDTO) {
        Language language = mapEntity(languageDTO);
        Language newLanguageSave = languageRepository.save(language);
        return mapDTO(newLanguageSave);
    }

    @Override
    public Set<LanguageDTO> findAll() {
        List<Language> languageList = languageRepository.findAll();
        Set<LanguageDTO> languageDTOSet = new HashSet<>();
        for (Language language : languageList){
            languageDTOSet.add(mapDTO(language));
        }
        return languageDTOSet;
    }

    //----------Mapper----------
    private LanguageDTO mapDTO(Language language){
        LanguageDTO languageDTO = objectMapper.convertValue(language, LanguageDTO.class);
        return languageDTO;
    }

    private Language mapEntity(LanguageDTO languageDTO){
        Language language = objectMapper.convertValue(languageDTO, Language.class);
        return language;
    }
}
