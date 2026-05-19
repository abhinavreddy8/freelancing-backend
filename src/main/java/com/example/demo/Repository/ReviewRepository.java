package com.example.demo.Repository;

import com.example.demo.Models.Review;
import com.example.demo.RowMapper.ReviewRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private ReviewRowMapper rowMapper;

    public void addReview(Review review){

        String sql="""
        INSERT INTO reviews
        (project_id,reviewer_id,reviewee_id,rating,comment)
        VALUES (?,?,?,?,?)
        """;

        jdbcTemplate.update(sql,
                review.getProjectId(),
                review.getReviewerId(),
                review.getRevieweeId(),
                review.getRating(),
                review.getComment());
    }

    public List<Review> getReviews(Integer userId) {

        String sql = """
        SELECT * FROM reviews
        WHERE reviewee_id=?
        ORDER BY id DESC
        """;

        return jdbcTemplate.query(
                sql,
                rowMapper,
                userId
        );
    }
}