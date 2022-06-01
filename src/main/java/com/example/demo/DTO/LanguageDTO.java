package com.example.demo.DTO;

import com.example.demo.entities.Person;
import lombok.Data;

@Data
public class LanguageDTO {
    private Integer id;
    private String type;
    private String description;
    private Person person;
}
