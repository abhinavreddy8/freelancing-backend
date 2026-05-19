package com.example.demo.Controllerr;

import com.example.demo.Models.Bug;
import com.example.demo.Models.Project;
import com.example.demo.Models.Submission;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.SubmissionRepository;
import com.example.demo.Service.BugService;
import com.example.demo.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bugs")
public class BugController {

    @Autowired
    private BugService bugService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SubmissionRepository submissionRepository;


    @PostMapping
    public String createBug(@RequestBody Bug bug) {

        bugService.createBug(bug);

        // ✅ GET PROJECT
        Project project = projectRepository.getProjectById(bug.getProjectId());

        // ✅ GET SUBMISSION (latest or accepted one)
        Submission submission = submissionRepository
                .getSubmissionByProjectId(bug.getProjectId());

        // 🔔 NOTIFY FREELANCER
        notificationService.createNotification(
                submission.getFreelancerId(),
                "Bug Reported 🐞",
                "Client reported a bug for project: " + project.getTitle(),
                "BUG_REPORTED",
                project.getId()
        );

        return "Bug created";
    }

    @GetMapping("/project/{projectId}")
    public List<Bug> getBugs(@PathVariable Integer projectId) {
        return bugService.getProjectBugs(projectId);
    }

    @PutMapping("/{bugId}")
    public String updateStatus(@PathVariable Integer bugId,
                               @RequestParam String status) {
        bugService.updateBugStatus(bugId, status);
        return "Status updated";
    }
}