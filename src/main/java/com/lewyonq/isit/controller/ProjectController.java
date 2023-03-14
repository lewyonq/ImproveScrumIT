package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.Project;
import com.lewyonq.isit.model.ProjectRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.service.ProjectService;
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
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/add")
    public String addProjectPage(Model model) {
        model.addAttribute("newProject", new ProjectRequest());
        return "new-project-form";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute ProjectRequest projectRequest, @AuthenticationPrincipal User user) {
        Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .weight(projectRequest.getWeight())
                .maturityLevel(0)
                .company(user.getCompany())
                .build();

        projectService.addProject(project);
        user.getCompany().getProjects().add(project);
        return "redirect:/user/panel";
    }
}
