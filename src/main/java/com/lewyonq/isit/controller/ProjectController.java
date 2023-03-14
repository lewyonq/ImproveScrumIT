package com.lewyonq.isit.controller;

import com.lewyonq.isit.model.Project;
import com.lewyonq.isit.model.ProjectRequest;
import com.lewyonq.isit.model.ProjectUserRequest;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.repository.UserRepository;
import com.lewyonq.isit.service.ProjectService;
import com.lewyonq.isit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

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



//    @GetMapping("/{projectId}")
//    public String addUserToProjectPage(@PathVariable Long projectId, Model model) throws Exception {
//        Project project = projectService.findProjectById(projectId);
//
//        List<User> users = project.getUsers();
//
//        model.addAttribute("project", project);
//        model.addAttribute("users", users);
//        return "add-user-to-project";
//    }
//
    @PostMapping("/{projectId}/add-user")
    public String addUserToProject(@PathVariable Long projectId,
                                                   @RequestParam Long userId) throws Exception {
        Project project = projectService.findProjectById(projectId);
        User user = userService.findById(userId);

        if (!Objects.equals(project.getCompany(), user.getCompany())) {
            throw new IllegalArgumentException("Uzytkownik nie nalezy do tej samej firmy co projekt");
        }

        projectService.addUserToProject(user, project);

        return "redirect:/home";
    }
}
