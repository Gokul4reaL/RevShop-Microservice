package com.homeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservice.model.Review;
import com.homeservice.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

	public List<Review> getReviewsByProductId(Long productId) {
		return reviewRepository.findByProductId(productId);
		
	}
}