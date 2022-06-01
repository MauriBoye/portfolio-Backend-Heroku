package com.example.demo.DTO;

import com.example.demo.entities.Person;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private Integer id;
    private String name;
    private LocalDate date;
    private String description;
    private String projectUrl;
    private String imgUrl;
    private Person person;
}
