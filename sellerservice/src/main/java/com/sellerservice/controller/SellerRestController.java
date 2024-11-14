package com.sellerservice.controller;

import java.util.Optional;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellerservice.model.Seller;
import com.sellerservice.repository.SellerRepository;

@RestController
@RequestMapping("/seller")
public class SellerRestController {

	@Autowired
	private SellerRepository sellerRepo;
	@GetMapping("/getSeller")
	public ResponseEntity<Seller> getSeller()
	{
		String email="gravekrishna@gmail.com";
		Seller seller = sellerRepo.findByEmail(email);
		return ResponseEntity.ok(seller);
		
	}
	@GetMapping("/sellerId/{sellerId}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("sellerId") Long sellerId) {
        // Fetch the seller object using the sellerId
        Optional<Seller> seller = sellerRepo.findById(sellerId);  // Assume this method is implemented in your SellerService

        // Check if seller is present
        if (seller.isPresent()) {
            return ResponseEntity.ok(seller.get());  // Return the Seller object
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if seller not found
        }
    }
}
