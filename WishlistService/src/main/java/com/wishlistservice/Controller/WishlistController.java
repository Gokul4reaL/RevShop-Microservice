package com.wishlistservice.Controller;

import com.wishlistservice.model.Product;
import com.wishlistservice.model.User;
import com.wishlistservice.model.WishlistItem;
import com.wishlistservice.service.WishlistService;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private static final String GATEWAY_BASE_URL = "http://localhost:9090";

    @PostMapping("/wishlist/add")
    public String addToWishlist(@RequestParam("productId") Long productId, HttpSession session) {
        String email = "ssubash2651@gmail.com";
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isPresent()) {
            String url = GATEWAY_BASE_URL + "/pro/getProductById/" + productId;
            Product product = restTemplate.getForObject(url, Product.class);
            wishlistService.addProductToWishlist(user.get(), product);
        }

        return "redirect:" + GATEWAY_BASE_URL + "/wish/wishlist";
    }

    @GetMapping("/wishlist")
    public String showWishlist(HttpSession session, Model model) {
        String email = "ssubash2651@gmail.com";
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            List<WishlistItem> wishlistItems = wishlistService.getWishlistByUser(user.get());
            model.addAttribute("wishlistItems", wishlistItems);
            return "wishlist";
        }

        return "redirect:" + GATEWAY_BASE_URL + "/login";
    }

    @PostMapping("/wishlist/remove")
    public String removeFromWishlist(@RequestParam("productId") Long productId, HttpSession session) {
        String email = "ssubash2651@gmail.com";
        
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            wishlistService.removeProductFromWishlist(user.get(), productId);
        }

        return "redirect:" + GATEWAY_BASE_URL + "/wish/wishlist";
    }
}
