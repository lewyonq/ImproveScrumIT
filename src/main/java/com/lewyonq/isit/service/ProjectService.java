package com.lewyonq.isit.service;

import com.lewyonq.isit.model.Project;
import com.lewyonq.isit.model.User;
import com.lewyonq.isit.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project findProjectById(Long id) throws Exception {
        return projectRepository.findById(id).orElseThrow(() -> new Exception("not found"));
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void addUserToProject(User user, Project project) {
        project.getUsers().add(user);
        projectRepository.save(project);
    }

    public void addUserWhoFullFilledSurvey(Project project) {
    }
}
