package com.example.demo.RowMapper;

import com.example.demo.Models.Submission;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubmissionRowMapper implements RowMapper<Submission> {

    @Override
    public Submission mapRow(ResultSet rs, int rowNum) throws SQLException {

        Submission submission = new Submission();

        submission.setId(rs.getInt("id"));
        submission.setProjectId(rs.getInt("project_id"));
        submission.setFreelancerId(rs.getInt("freelancer_id"));
        submission.setFileUrl(rs.getString("file_url"));
        submission.setReferenceLink(rs.getString("reference_link"));
        submission.setStatus(rs.getString("status"));
        submission.setClientFeedback(rs.getString("client_feedback"));

        return submission;
    }
}
