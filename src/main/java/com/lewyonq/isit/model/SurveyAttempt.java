package com.lewyonq.isit.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Data
public class SurveyAttempt {
    @Id
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

        double sumOfAnswers = answers.entrySet().stream().mapToDouble(k -> k.getValue()).sum();
        double numberOfProvidedAnswers = answers.entrySet().stream().filter(k -> k.getValue() != NOT_ANSWERED).count();

        maturityLevel = (sumOfAnswers * user.getWeight()) / numberOfProvidedAnswers;
    }
}
