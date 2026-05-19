package com.example.demo.Controllerr;

import com.example.demo.Models.Project;
import com.example.demo.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public String createProject(@RequestBody Project project) {
        System.out.println("Received clientId: " + project.getClientId()); // 🔥
        projectService.createProject(project);
        return "Project Created";
    }
    @GetMapping
    public List<Project> getAllProjects(){

        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Integer id){

        return projectService.getProjectById(id);
    }

    @GetMapping("/search")
    public List<Project> searchProjects(

            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) Double minBudget,

            @RequestParam(required = false) Double maxBudget
    ){

        return projectService.searchProjects(
                keyword,
                minBudget,
                maxBudget
        );
    }

    @GetMapping("/client/{clientId}")
    public List<Project> getClientProjects(@PathVariable Integer clientId){

        return projectService.getProjectsByClient(clientId);
    }
    @PutMapping("/{id}")
    public String updateProject(@PathVariable Integer id, @RequestBody Project project) {
        project.setId(id);
        projectService.updateProject(project);
        return "Project Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return "Project Deleted";
    }
}
