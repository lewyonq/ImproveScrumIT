package com.lewyonq.isit.service;

import com.lewyonq.isit.enums.ECategory;
import com.lewyonq.isit.model.*;
import com.lewyonq.isit.repository.SurveyAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveyAttemptService {

    private final SurveyAttemptRepository surveyRepository;
    private final ProjectService projectService;

    public void fullfillSurvey(@AuthenticationPrincipal User user, Project project) {
        SurveyAttempt surveyAttempt = SurveyAttempt.builder()
                .survey(Survey.getInstance())
                .user(user)
                .project(project)
                .build();
        //todo: change it to take answers from real survey
        Map<Question, Double> answers = Map.of(new Question("Text1", ECategory.SCRUM_ROLES), 1.0,
                new Question("Text2", ECategory.SCRUM_ROLES), 2.0,
                new Question("Text3", ECategory.SCRUM_EVENTS), 3.0,
                new Question("Text4", ECategory.SCRUM_EVENTS), 0.0,
                new Question("Text5", ECategory.SCRUM_ARTEFACTS), 4.0,
                new Question("Text6", ECategory.SCRUM_ARTEFACTS), 2.0);

        surveyAttempt.setAnswers(answers);
        surveyRepository.save(surveyAttempt);
    }
}
