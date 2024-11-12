package com.homeservice.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservice.model.Product;
import com.homeservice.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	public List<Product> getAllProducts() {
		
	        return repo.findAll();  
	    
	}
	

	public Product getProductById(Long productId) {
	    return repo.findById(productId).orElse(null);  
	}

	
	public List<Product> searchProductsByNameOrCategory(String query) {
		if (query == null || query.trim().isEmpty()) {
            return List.of(); // Return an empty list if no query
        }
        return repo.findByNameOrCategory(query);
    }
	

    

	 
}
