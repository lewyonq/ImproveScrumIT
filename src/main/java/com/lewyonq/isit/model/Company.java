package com.lewyonq.isit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @SequenceGenerator(
            name = "company_id_generator",
            sequenceName = "company_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_id_generator"
    )
    private Long id;
    private String name;
    private double maturityLevel;
    @OneToMany
    private List<Project> projects = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }
}
