package com.lewyonq.isit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@AllArgsConstructor
public class SurveyAttempt {
    @Id
    @SequenceGenerator(
            name = "survey_attempt_id_generator",
            sequenceName = "survey_attempt_id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_attempt_id_generator"
    )
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Project project;
    @Transient
    private Survey survey;
    @ElementCollection
    private Map<Question, Double> answers;

    private double maturityLevel;

    public SurveyAttempt() {
        this.survey = Survey.getInstance();
        answers = survey.getQuestions()
                .stream()
                .collect(Collectors.toMap(Function.identity(), e -> 0.0));
    }

    public void countMaturityLevel() {
        final double NOT_ANSWERED = 0.001;

        double sumOfAnswers = answers.entrySet().stream()
                .filter(k -> k.getValue() != NOT_ANSWERED)
                .mapToDouble(k -> k.getValue())
                .sum();

        double numberOfProvidedAnswers = answers.entrySet().stream()
                .filter(k -> k.getValue() != NOT_ANSWERED)
                .count();

        maturityLevel = (sumOfAnswers * user.getWeight()) / numberOfProvidedAnswers;
    }
}
