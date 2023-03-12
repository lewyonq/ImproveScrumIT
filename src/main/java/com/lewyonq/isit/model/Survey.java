package com.lewyonq.isit.model;

import com.lewyonq.isit.enums.ECategory;
import lombok.Getter;

import java.util.List;

@Getter
public class Survey {
    private List<Question> questions = initializeQuestions();

    private Survey() {
    }

    private static class SurveyHolder {
        public static final Survey instance = new Survey();
    }

    public static Survey getInstance() {
        return SurveyHolder.instance;
    }

    private List<Question> initializeQuestions() {
        return List.of(new Question("Text1", ECategory.SCRUM_ROLES),
                new Question("Text2", ECategory.SCRUM_ROLES),
                new Question("Text3", ECategory.SCRUM_ROLES),
                new Question("Text4", ECategory.SCRUM_EVENTS),
                new Question("Text5", ECategory.SCRUM_EVENTS),
                new Question("Text6", ECategory.SCRUM_EVENTS),
                new Question("Text7", ECategory.SCRUM_ARTEFACTS),
                new Question("Text8", ECategory.SCRUM_ARTEFACTS),
                new Question("Text9", ECategory.SCRUM_ARTEFACTS),
                new Question("Text10", ECategory.SCRUM_ARTEFACTS));
    }
}
