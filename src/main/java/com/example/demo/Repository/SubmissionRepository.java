package com.example.demo.Repository;

import com.example.demo.Models.Submission;
import com.example.demo.RowMapper.SubmissionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubmissionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private SubmissionRowMapper rowMapper;

    public void submitWork(Submission submission){

        try {

            String sql = """
        INSERT INTO submissions(project_id,freelancer_id,file_url,reference_link,status)
        VALUES (?,?,?,?,?)
        """;

            int rows = jdbcTemplate.update(sql,
                    submission.getProjectId(),
                    submission.getFreelancerId(),
                    submission.getFileUrl(),
                    submission.getReferenceLink(),
                    submission.getStatus()
            );

            System.out.println("ROWS INSERTED: " + rows);

        } catch (Exception e) {
            System.out.println("DB ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Submission> getSubmissions(Integer projectId) {

        String sql = "SELECT * FROM submissions WHERE project_id=?";

        return jdbcTemplate.query(sql, rowMapper, projectId);
    }
    public void updateStatus(Integer id, String status){

        String sql = "UPDATE submissions SET status=? WHERE id=?";

        jdbcTemplate.update(sql, status, id);
    }
    public Submission getById(Integer id){
        String sql = "SELECT * FROM submissions WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new SubmissionRowMapper(), id);
    }
    public Submission getSubmissionByProjectId(Integer projectId){
        String sql = "SELECT * FROM submissions WHERE project_id=? ORDER BY created_at DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new SubmissionRowMapper(), projectId);
    }
}
