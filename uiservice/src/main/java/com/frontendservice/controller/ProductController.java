package com.frontendservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ProductController {

	

		 @GetMapping("/pro/home")
		    public String showHomePage(Model model) {
		        return "redirect:/pro/home";
		    }
		 
		 @GetMapping("/pro/product/details/{id}")
		    public String productDetails(@PathVariable Long id, Model model) {
		        
		        return "redirect:/pro/product/details/{id}";
		    }

		   

		    @GetMapping("/pro/add-product")
		    public String showAddProductForm(Model model) {
		        
		        return "redirect:/pro/add-product";
		    }

		   

		    @GetMapping("/pro/display-products")
		    public String getProductsBySellerId(@RequestParam("sellerId") Long sellerId, Model model,HttpSession session) {
//		    	
		        return "redirect:/pro/display-products";
		    }

		    @GetMapping("/pro/edit-product")
		    public String showEditProductForm(@RequestParam("id") Long productId, Model model) {
		    	
		   
		        return "redirect:/pro/edit-product";
		    }

		    
		    @DeleteMapping("/pro/delete-product")
		    public String deleteProduct(@RequestParam("id") Long productId) {
		       
		        return "redirect:/pro/delete-product";
		    }

		    @GetMapping("/pro/search-products")
		    public String searchProducts(@RequestParam("query") String query, @RequestParam("sellerId") Long sellerId,
		                                 Model model) {
		    	
		       
		        return "redirect:/pro/search-products";
		    }

		    @GetMapping("/pro/searchall")
		    public String getAllProducts(@RequestParam("query") String query, Model model) {
		       
		        return "redirect:/pro/searchall";
		    }
		    @GetMapping("/pro/getProductById/{productId}")
			public String productDetails(@PathVariable String productId, Model model) {
				
		        return "redirect:/pro/getProductById/{productId}";
		    }
		    
		  
}