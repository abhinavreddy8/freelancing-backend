package com.example.demo.RowMapper;

import com.example.demo.Models.Bug;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class BugRowMapper implements RowMapper<Bug> {

    @Override
    public Bug mapRow(ResultSet rs, int rowNum) throws SQLException {

        Bug bug = new Bug();

        bug.setId(rs.getInt("id"));
        bug.setProjectId(rs.getInt("project_id"));
        bug.setFreelancerId(rs.getInt("freelancer_id"));
        bug.setTitle(rs.getString("title"));
        bug.setDescription(rs.getString("description"));
        bug.setStatus(rs.getString("status"));

        // ✅ NULL CHECK — this was causing your 500
        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            bug.setCreatedAt(createdAt.toLocalDateTime());
        }

        return bug;
    }
}