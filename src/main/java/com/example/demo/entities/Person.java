package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String job;
    private String ubication;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String profileUrl;
    @Column(columnDefinition="TEXT")
    private String bannerUrl;

    @OneToMany(mappedBy = "person")
    @JsonIgnore //Si este objeto viaja en formato JSON, podría entrar en un bucle infinito
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "person")
    @JsonIgnore //Si este objeto viaja en formato JSON, podría entrar en un bucle infinito
    private Set<Education> educations;

    @OneToMany(mappedBy = "person")
    @JsonIgnore //Si este objeto viaja en formato JSON, podría entrar en un bucle infinito
    private Set<Skill> skills;

    @OneToMany(mappedBy = "person")
    @JsonIgnore //Si este objeto viaja en formato JSON, podría entrar en un bucle infinito
    private Set<Language> languages;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", job='" + job + '\'' +
                ", ubication='" + ubication + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
