package com.example.demo.RowMapper;

import com.example.demo.Models.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {

        Review review = new Review();

        review.setId(rs.getInt("id"));
        review.setProjectId(rs.getInt("project_id"));
        review.setReviewerId(rs.getInt("reviewer_id"));
        review.setRevieweeId(rs.getInt("reviewee_id"));
        review.setRating(rs.getInt("rating"));
        review.setComment(rs.getString("comment"));

        // ✅ Was missing — now mapped correctly
        Timestamp ts = rs.getTimestamp("created_at");
        review.setCreatedAt(ts != null ? LocalDateTime.parse(ts.toString()) : null);

        return review;
    }
}