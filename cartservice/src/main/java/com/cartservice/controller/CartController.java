package com.cartservice.controller;

import com.cartservice.model.CartItem;
import com.cartservice.model.Product;
import com.cartservice.model.User;
import com.cartservice.service.CartService;
import com.cartservice.service.ProductService;
import com.cartservice.model.Session;
import com.cartservice.repository.SessionRepository;
import com.cartservice.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SessionRepository sessionRepo;

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity, HttpSession session) {
		
		 //String email = (String) session.getAttribute("email"); 
    	//String email ="ssubash2651@gmail.com";
    	 Optional<Session> ses = sessionRepo.findById(1L);
		 String email = ses.get().getEmail(); int value = ses.get().getValue();
		 if(value == 0) { 
			 return "redirect:http://localhost:9090/buyer/login"; 
			 }
		 
       
      
        if (email == null) {
            return "redirect:/buyer/login";  // Redirect to login if not logged in
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Product product = productService.getProductById(productId);
            cartService.addProductToCart(user.get(), product, quantity);
        }

        return "redirect:http://localhost:9090/cart/viewCart";
    }

    @GetMapping("/viewCart")
    public String showCart(HttpSession session, Model model) {
//        String email = (String) session.getAttribute("email");
//        if (email == null) {
//            return "redirect:/login";
//        }
    	String email ="ssubash2651@gmail.com";
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            List<CartItem> cartItems = cartService.getCartItemsByUser(user.get());
            List<CartItem> savedItems = cartService.getSavedItemsByUser(user.get());
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("savedItems", savedItems);

            // Calculate price details
            model.addAttribute("totalPrice", cartService.calculateTotalPrice(cartItems));
            model.addAttribute("discount", cartService.calculateDiscount(cartItems));
            model.addAttribute("deliveryCharges", cartService.calculateDeliveryCharges(cartItems));
            model.addAttribute("totalAmount", cartService.calculateFinalAmount(cartItems));
            return "cart";
        }

        return "redirect:/buyer/login";
    }

    @PostMapping("/update-quantity")
    public String updateCartItemQuantity(@RequestParam("productId") Long productId,
                                         @RequestParam("increase") boolean increase, HttpSession session) {
        //String email = (String) session.getAttribute("email");
    	String email = "ssubash2651@gmail.com";
        if (email == null) {
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            cartService.updateCartItemQuantity(user.get(), productId, increase);
        }
        
        return "redirect:http://localhost:9090/cart/viewCart";
    }


    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("productId") Long productId, HttpSession session) {
        //String email = (String) session.getAttribute("email");
    	String email = "ssubash2651@gmail.com";
        if (email == null) {
            return "redirect:/buyer/login";
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            cartService.removeCartItem(user.get(), productId);
        }
        
        return "redirect:http://localhost:9090/cart/viewCart";
    }


    @PostMapping("/save-for-later")
    public String saveForLater(@RequestParam("productId") Long productId, HttpSession session) {
        //String email = (String) session.getAttribute("email");
    	String email = "ssubash2651@gmail.com";
        if (email == null) {
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            cartService.saveForLater(user.get(), productId);
        }
        
        return "redirect:/cart/viewCart";
    }


    // Move item back to cart from saved items
    @GetMapping("/move-to-cart")
    public String moveToCart(@RequestParam("productId") Long productId, HttpSession session) {
        //String email = (String) session.getAttribute("email");
    	String email = "ssubash2651@gmail.com";
        if (email != null) {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                cartService.moveToCart(user.get(), productId);
            }
        }
        return "redirect:/cart/viewCart";
    }
}  