package com.example.demo.Controllerr;

import com.example.demo.Models.Project;
import com.example.demo.Models.Submission;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Service.NotificationService;
import com.example.demo.Service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ProjectRepository projectRepository;
    @PostMapping
    public String submitWork(@RequestBody Submission request){


        System.out.println("REQUEST: " + request); // 🔥 DEBUG

        Submission submission = new Submission();
        submission.setProjectId(request.getProjectId());
        submission.setFreelancerId(request.getFreelancerId());
        submission.setFileUrl(request.getFileUrl());
        submission.setReferenceLink(request.getReferenceLink());
        submission.setStatus("SUBMITTED");

        submissionService.submitWork(submission);

        Project project = projectRepository.getProjectById(request.getProjectId());

        notificationService.createNotification(
                project.getClientId(),
                "Work Submitted 📦",
                "Freelancer submitted work for project: " + project.getTitle(),
                "SUBMISSION_DONE",
                project.getId()
        );

        return "Work submitted";
    }

    @GetMapping("/project/{projectId}")
    public List<Submission> getSubmissions(@PathVariable Integer projectId){

        return submissionService.getSubmissions(projectId);
    }
    @PutMapping("/{id}/accept")
    public String acceptWork(@PathVariable Integer id){

        submissionService.updateStatus(id, "ACCEPTED");

        Submission submission = submissionService.getById(id);

        Project project = projectRepository.getProjectById(submission.getProjectId());

        notificationService.createNotification(
                submission.getFreelancerId(),
                "Work Approved ✅",
                "Your work for project '" + project.getTitle() + "' was accepted.",
                "SUBMISSION_ACCEPTED",
                project.getId()
        );

        return "Work accepted";
    }
    @PutMapping("/{id}/bug")
    public String markBug(@PathVariable Integer id){

        submissionService.updateStatus(id, "BUG");

        return "Marked as bug";
    }
    @GetMapping("/latest/{projectId}")
    public Submission getLatestSubmission(
            @PathVariable Integer projectId
    ){

        return submissionService
                .getSubmissionByProjectId(projectId);

    }

}
