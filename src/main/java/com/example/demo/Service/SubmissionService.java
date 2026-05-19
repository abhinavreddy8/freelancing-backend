package com.example.demo.Service;

import com.example.demo.Models.Submission;
import com.example.demo.Repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public void submitWork(Submission submission){
        submissionRepository.submitWork(submission);
    }

    public List<Submission> getSubmissions(Integer projectId){
        return submissionRepository.getSubmissions(projectId);
    }

    public void updateStatus(Integer id, String accepted) {
        submissionRepository.updateStatus(id,accepted);
    }
    public Submission getById(Integer id){
        return submissionRepository.getById(id);
    }
    public Submission getSubmissionByProjectId(
            Integer projectId
    ){

        return submissionRepository
                .getSubmissionByProjectId(projectId);

    }
}
