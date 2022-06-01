package com.example.demo.DTO;

import com.example.demo.entities.Person;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationDTO {
    private Integer id;
    private String institute;
    private String career;
    private LocalDate entryDate;
    private LocalDate exitDate;
    private String description;
    private Person person;
    private String logoUrl;
}
