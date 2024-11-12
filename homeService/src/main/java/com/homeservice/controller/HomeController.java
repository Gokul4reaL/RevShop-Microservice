package com.homeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.homeservice.model.Product;
import com.homeservice.model.Review;
import com.homeservice.service.ProductService;
import com.homeservice.service.ReviewService;

@Controller
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
    private ProductService productService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private RestTemplate restTemplate;
	@GetMapping("/home")
    public String showHomePage(Model model) {
        // Add data to the model for dynamic content (like featured products)
        model.addAttribute("featuredProducts", productService.getAllProducts());
        
        return "home-page";  
    }
	@GetMapping("/buyerhome")
    public String showBuyerHomePage(Model model) {
        // Add data to the model for dynamic content (like featured products)
        model.addAttribute("featuredProducts", productService.getAllProducts());
        
        return "buyer-home-page";  
    }
	
	

	    @GetMapping("product/details/{id}")
	    public String productDetails(@PathVariable Long id, Model model) {
	        
	        Product product = productService.getProductById(id);
	        List<Review> reviews = reviewService.getReviewsByProductId(id);
	        model.addAttribute("product", product);
	        model.addAttribute("reviews", reviews);
	        return "product"; // This will render product.html
	   
	}

	    @GetMapping("/searchall")
	    public String getAllProducts(@RequestParam("query") String query, Model model) {
	        List<Product> products = productService.searchProductsByNameOrCategory(query);
	        model.addAttribute("products", products);
	        model.addAttribute("message", products.isEmpty() ? "No products found." : null);
	        return "display-all-products";
	    }

}
