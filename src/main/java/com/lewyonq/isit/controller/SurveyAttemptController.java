package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.SurveyAttemptRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.service.ProjectService;
import com.lewyonq.isit.service.SurveyAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyAttemptController {

    private final SurveyAttemptService surveyService;
    private final ProjectService projectService;

    @GetMapping("/fullfill")
    public String fullfillSurvey(Model model) {
        //choose project
        model.addAttribute("surveyAttempt", new SurveyAttemptRequest());
        return "go-to-survey-form";
    }

    @PostMapping("/fullfill")
    public String goToSurvey(@ModelAttribute SurveyAttemptRequest surveyAttemptRequest,
                             @AuthenticationPrincipal User user) throws Exception {
        surveyService.fullfillSurvey(user, projectService.findProjectById(surveyAttemptRequest.getProjectId()));
        return "redirect:/survey-form";
    }
}
