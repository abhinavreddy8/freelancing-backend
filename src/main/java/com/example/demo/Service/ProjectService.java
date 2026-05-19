package com.example.demo.Service;

import com.example.demo.Models.Project;
import com.example.demo.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void createProject(Project project) {
        projectRepository.createProject(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public Project getProjectById(Integer id){

        return projectRepository.getProjectById(id);
    }

    public List<Project> searchProjects(String keyword,
                                        Double minBudget,
                                        Double maxBudget){

        return projectRepository.searchProjects(
                keyword,
                minBudget,
                maxBudget
        );
    }

    public List<Project> getProjectsByClient(Integer clientId){

        return projectRepository.getProjectsByClient(clientId);
    }
    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteProject(id);
    }
}
