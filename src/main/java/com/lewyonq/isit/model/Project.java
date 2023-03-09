package com.lewyonq.isit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @SequenceGenerator(
            name = "project_id_generator",
            sequenceName = "project_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_id_generator"
    )
    private Long id;
    private String name;
    private String description;
    private double weight;
    private double maturityLevel;
    @OneToMany
    private List<User> usersWhoDidSurvey;
    @OneToMany
    private List<Survey> surveys;
}
