package com.lewyonq.isit.model;

import com.lewyonq.isit.enums.ECategory;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Question {
    private String questionContent;
    private ECategory questionCategory;
}
