package com.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.model.Product;
import com.productservice.model.Review;
import com.productservice.service.ProductService;

@RestController
@RequestMapping("/pro")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<Product> productDetails(@PathVariable String productId, Model model) {
		Long id = Long.parseLong(productId);
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
