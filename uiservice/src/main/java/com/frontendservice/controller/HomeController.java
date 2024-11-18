package com.frontendservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/api/home")
    public String showHomePage(Model model) {
        return "redirect:/api/home";  
    }

	@GetMapping("/api/buyerhome")
    public String showBuyerHomePage(Model model) {
        return "redirect:/api/buyerhome";  
    }
	
	
	 @GetMapping("/api/product/details/{id}")
	    public String productDetails(@PathVariable Long id, Model model) {
	        return "redirect:/api/product/details/{id}"; 
	   
	}

	    @GetMapping("/api/searchall")
	    public String getAllProducts(@RequestParam("query") String query, Model model) {
	        return "redirect:/api/searchall";
	    }
	    
	    @GetMapping("/api/writeReview")
	    public String showReviewForm(@RequestParam("productId") Long productId, Model model) {
	        return "redirect:/api/writeReview";  
	    }
	    
	    @GetMapping("/buyer/registration")
	    public String showRegistrationForm() {
	         return "redirect:/buyer/registration";  
	    }
	    
	    @GetMapping("/buyer/login")
	    public String showLoginForm() {
	    	
	        return "redirect:/buyer/login"; 
	    }
	    @GetMapping("/buyer/profile")
	    public String getUserProfile(HttpSession session, Model model) {
	        
	        return "redirect:/buyer/profile";  
	    }
	    


    
    @GetMapping("/seller/sellerRegistration")
    public String showRegistrationForm(Model model) {
       
        return "redirect:/seller/sellerRegistration"; 
    }

    @GetMapping("/seller/sellerLogin")
    public String showLoginForm(Model model) {
        
        return "redirect:/seller/sellerLogin";  
    }

    
    @GetMapping("/seller/seller-dashboard")
    public String sellerDashboard(Model model,HttpSession session) {
         return "redirect:/seller/seller-dashboard";  
    }
    

	@GetMapping("/seller/getSeller")
	public String getSeller()
	{
		return "redirect:/seller/getSeller";  
		
	}
	@GetMapping("/seller/sellerId/{sellerId}")
    public String getSellerById(@PathVariable("sellerId") Long sellerId) {
		return "redirect:/seller/sellerId/{sellerId}"; 
     
	}
	
	
	


}