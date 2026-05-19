package com.example.demo.Repository;

import com.example.demo.Models.Bug;
import com.example.demo.RowMapper.BugRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BugRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BugRowMapper rowMapper;

    public void createBug(Bug bug){

        String sql = """
        INSERT INTO bugs(project_id,freelancer_id,title,description,status)
        VALUES (?,?,?,?,?)
        """;

        jdbcTemplate.update(sql,
                bug.getProjectId(),
                bug.getFreelancerId(),
                bug.getTitle(),
                bug.getDescription(),
                "OPEN");
    }

    public List<Bug> getBugsByProject(Integer projectId){

        String sql = "SELECT * FROM bugs WHERE project_id=?";

        return jdbcTemplate.query(sql,rowMapper,projectId);
    }

    public void updateStatus(Integer bugId,String status){

        String sql = "UPDATE bugs SET status=? WHERE id=?";

        jdbcTemplate.update(sql,status,bugId);
    }
}