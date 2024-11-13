package com.wishlistservice.Controller;

import com.wishlistservice.config.RestTemplateConfig;
import com.wishlistservice.model.Product;
import com.wishlistservice.model.User;
import com.wishlistservice.model.WishlistItem;
import com.wishlistservice.service.WishlistService;
//import com.wishlistservice.service.ProductService;
import com.wishlistservice.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wish")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    //@Autowired
    //private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/wishlist/add")
    public String addToWishlist(@RequestParam("productId") Long productId, HttpSession session) {
//        String email = (String) session.getAttribute("email");
//        if (email == null) {
//            return "redirect:/login";  // Redirect to login if the user is not logged in
//        }
    	String email = "ssubash2651@gmail.com";
    	String url = "http://localhost:9090/pro/getProductById";
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
        	String url1 = url+"/"+productId;
            Product product = restTemplate.getForObject(url1, Product.class);
            wishlistService.addProductToWishlist(user.get(), product);
        }

        return "redirect:/wish/wishlist";
    }

    @GetMapping("/wishlist")
    public String showWishlist(HttpSession session, Model model) {
//        String email = (String) session.getAttribute("email");
//
//        if (email == null) {
//            return "redirect:/login";  // Redirect to login if user is not logged in
//        }
    	String email = "ssubash2651@gmail.com";
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            List<WishlistItem> wishlistItems = wishlistService.getWishlistByUser(user.get());
            model.addAttribute("wishlistItems", wishlistItems);
            return "wishlist";  // Refer to wishlist.html template
        }

        return "redirect:/login";
    }

    @PostMapping("/wishlist/remove")
    public String removeFromWishlist(@RequestParam("productId") Long productId, HttpSession session) {
//        String email = (String) session.getAttribute("email");
          String email="ssubash2651@gmail.com";
        if (email == null) {
            return "redirect:/login";  // Redirect to login if user is not logged in
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            wishlistService.removeProductFromWishlist(user.get(), productId);
        }

        return "redirect:/wish/wishlist";

    }
}