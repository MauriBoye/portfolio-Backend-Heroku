package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String institute;
    private String career;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate entryDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate exitDate;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String logoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", institute='" + institute + '\'' +
                ", career='" + career + '\'' +
                ", entryDate=" + entryDate +
                ", exitDate=" + exitDate +
                ", description='" + description + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Education)) return false;
        Education education = (Education) o;
        return Objects.equals(getId(), education.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
