package com.example.demo.Service;

import com.example.demo.Models.Bug;
import com.example.demo.Repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService {

    @Autowired
    private BugRepository repository;

    public void createBug(Bug bug){

        repository.createBug(bug);
    }

    public List<Bug> getProjectBugs(Integer projectId){

        return repository.getBugsByProject(projectId);
    }

    public void updateBugStatus(Integer bugId,String status){

        repository.updateStatus(bugId,status);
    }
}
