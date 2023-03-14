package com.lewyonq.isit.model;

import com.lewyonq.isit.enums.ECategory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SurveyAttemptTest {

    @Test
    public void testSurveyAttemptInitialization() {
        SurveyAttempt attempt = new SurveyAttempt();
        Map<Question, Double> answers = attempt.getAnswers();

        assertEquals(answers.size(), Survey.getInstance().getQuestions().size());

        assertTrue(answers.values()
                .stream()
                .allMatch(v -> v == 0.0));
    }

    @Test
    public void countMaturityLevelEquals4() {
        User user = User.builder()
                .weight(2.0)
                .build();

        Map<Question, Double> answers = Map.of(new Question("Text1", ECategory.SCRUM_ROLES), 1.0,
                new Question("Text2", ECategory.SCRUM_ROLES), 2.0,
                new Question("Text3", ECategory.SCRUM_EVENTS), 3.0,
                new Question("Text4", ECategory.SCRUM_EVENTS), 0.0,
                new Question("Text5", ECategory.SCRUM_ARTEFACTS), 4.0,
                new Question("Text6", ECategory.SCRUM_ARTEFACTS), 2.0);

        SurveyAttempt attempt = new SurveyAttempt();
        attempt.setUser(user);
        attempt.setAnswers(answers);

        attempt.countMaturityLevel();

        assertEquals((12.0 * 2.0) / 6, attempt.getMaturityLevel());
        //answers
    }

    @Test
    public void countMaturityLevelEquals3() {
        final double NOT_ANSWERED = 0.001;
        User user = User.builder()
                .weight(1.0)
                .build();

        Map<Question, Double> answers = Map.of(new Question("Text1", ECategory.SCRUM_ROLES), 3.0,
                new Question("Text2", ECategory.SCRUM_ROLES), NOT_ANSWERED,
                new Question("Text3", ECategory.SCRUM_EVENTS), NOT_ANSWERED);

        SurveyAttempt attempt = new SurveyAttempt();
        attempt.setUser(user);
        attempt.setAnswers(answers);

        attempt.countMaturityLevel();

        assertEquals(3.0, attempt.getMaturityLevel());
        //answers
    }

}