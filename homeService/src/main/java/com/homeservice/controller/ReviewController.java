package com.homeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.homeservice.model.Review;
import com.homeservice.service.ReviewService;
@Controller
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
   

    @GetMapping("/writeReview")
    public String showReviewForm(@RequestParam("productId") Long productId, Model model) {
        System.out.println(productId);
        Review review = new Review();  // Create a new Review object
        review.setProductId(productId);  // Set productId in the Review object
        model.addAttribute("review", review);  // Add this review object to the model
        return "review";  // The name of your HTML form
    }

    @PostMapping("/submitReview")
    public String submitReview(@ModelAttribute Review review, RedirectAttributes redirectAttributes) {
        System.out.println("Product ID: " + review.getProductId());
        
        reviewService.saveReview(review);  // Save the review to the database
        
        // Add success message to redirect attributes
        redirectAttributes.addFlashAttribute("message", "Review added successfully!");
        
        // Redirect back to the product details page
        return "redirect:/api/buyerhome";
    }

}
