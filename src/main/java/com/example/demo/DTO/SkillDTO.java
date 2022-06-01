package com.example.demo.DTO;

import com.example.demo.entities.Person;
import lombok.Data;

@Data
public class SkillDTO {
    private Integer id;
    private String type;
    private String name;
    private Integer percentage;
    private Person person;
}
