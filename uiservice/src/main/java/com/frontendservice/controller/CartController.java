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
public class CartController {
	@GetMapping("/cart/viewCart")
    public String showCart(HttpSession session, Model model) {
        return "redirect:/cart/viewCart";	
    }


    @GetMapping("/cart/move-to-cart")
    public String moveToCart(@RequestParam("productId") Long productId, HttpSession session) {
       
        return "redirect:/cart/move-to-cart";
    }
  
}