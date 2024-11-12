package com.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productservice.model.Review;
import com.productservice.repository.ReviewRepository;



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
