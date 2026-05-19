package com.example.demo.Controllerr;

import com.example.demo.Models.Review;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public String addReview(@RequestBody Review review){

        reviewService.addReview(review);

        return "Review added";
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviews(@PathVariable Integer userId){

        return reviewService.getReviews(userId);
    }
}
