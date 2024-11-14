package com.cartservice.controller;

import java.util.List;
import java.util.Optional;

//import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartservice.model.CartItem;
import com.cartservice.model.User;
import com.cartservice.repository.UserRepository;
import com.cartservice.service.CartService;
import com.cartservice.service.ProductService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	@Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;
	@GetMapping("/getCartItems")
    public ResponseEntity<List<CartItem>> showCart(HttpSession session, Model model) {
       // String email = (String) session.getAttribute("email");
//        if (email == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        
        Optional<User> user = userRepository.findByEmail("gravekrishna@gmail.com");
        if (user.isPresent()) {
            List<CartItem> cartItems = cartService.getCartItemsByUser(user.get());
            
            //List<CartItem> savedItems = cartService.getSavedItemsByUser(user.get());
            return ResponseEntity.ok(cartItems);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
