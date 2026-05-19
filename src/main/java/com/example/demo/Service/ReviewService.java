package com.example.demo.Service;

import com.example.demo.Models.Review;
import com.example.demo.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Review review){
        reviewRepository.addReview(review);
    }

    public List<Review> getReviews(Integer userId){
        return reviewRepository.getReviews(userId);
    }

}
