package com.productservice.controller;

import java.util.List;

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

import com.productservice.model.Product;
import com.productservice.model.Review;
import com.productservice.model.Seller;
import com.productservice.service.ProductService;
import com.productservice.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pro")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RestTemplate restTemplate;

   // private static final String SELLER_SERVICE_URL = "http://localhost:8082/seller/current";
    private static final String SELLER_SERVICE_URL = "http://localhost:9090/seller";
    // -----------------------------------
    // Home Page Endpoints (For User Side)
    // -----------------------------------

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("featuredProducts", productService.getAllProducts());
        return "home-page";
    }

    @GetMapping("/product/details/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Review> reviews = reviewService.getReviewsByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "product";
    }

    // -----------------------------------
    // Product Management Endpoints (For Seller Side)
    // -----------------------------------

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product, HttpSession session, Model model) {
       // Seller loggedInSeller = getLoggedInSeller(session);
    	String url = SELLER_SERVICE_URL+"/getSeller";
        Seller loggedInSeller = restTemplate.getForObject(url, Seller.class);
        if (loggedInSeller != null) {
            product.setSeller(loggedInSeller);
            productService.addProduct(product);

            model.addAttribute("message", "Product added successfully!");
//            return "redirect:/pro/display-products?sellerId=" + loggedInSeller.getSellerId();
            return "redirect:http://localhost:9090/pro/display-products?sellerId=" + loggedInSeller.getSellerId();
        }

        model.addAttribute("loginError", "You must log in to add a product.");
        return "redirect:/seller-login";
    }

    @GetMapping("/display-products")
    public String getProductsBySellerId(@RequestParam("sellerId") Long sellerId, Model model,HttpSession session) {
//    	 String url = SELLER_SERVICE_URL + "/sellerId/" + sellerId;
//    	    
//    	    // Use RestTemplate to get the seller based on sellerId
//    	    Seller loggedInSeller = restTemplate.getForObject(url, Seller.class);
    	String url = SELLER_SERVICE_URL+"/getSeller";
        Seller loggedInSeller = restTemplate.getForObject(url, Seller.class);
    	    session.setAttribute("loggedInSeller", loggedInSeller);
        List<Product> productList = productService.getProductsBySellerId(sellerId);
        model.addAttribute("products", productList);
        return "display-products";
    }

    @GetMapping("/edit-product")
    public String showEditProductForm(@RequestParam("id") Long productId, Model model) {
    	
        
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/edit-product")
    public String editProduct(@ModelAttribute("product") Product product, HttpSession session, Model model) {
//        Seller loggedInSeller = getLoggedInSeller(session);
    	String url = SELLER_SERVICE_URL+"/getSeller";
        Seller loggedInSeller = restTemplate.getForObject(url, Seller.class);

        if (loggedInSeller != null) {
            product.setSeller(loggedInSeller);
            productService.updateProduct(product);

            model.addAttribute("message", "Product updated successfully!");
            return "redirect:http://localhost:9090/pro/display-products?sellerId=" + loggedInSeller.getSellerId();
        }

        model.addAttribute("loginError", "You must log in to edit a product.");
        return "redirect:/seller/loginSeller";
    }

    @DeleteMapping("/delete-product")
    public String deleteProduct(@RequestParam("id") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:http://localhost:9090/pro/display-products";
    }

    @GetMapping("/search-products")
    public String searchProducts(@RequestParam("query") String query, @RequestParam("sellerId") Long sellerId,
                                 Model model) {
    	
        List<Product> products = productService.searchProducts(query, sellerId);
        model.addAttribute("products", products);
        model.addAttribute("message", products.isEmpty() ? "No products found." : null);
        return "display-products";
    }

    @GetMapping("/searchall")
    public String getAllProducts(@RequestParam("query") String query, Model model) {
        List<Product> products = productService.searchProductsByNameOrCategory(query);
        model.addAttribute("products", products);
        model.addAttribute("message", products.isEmpty() ? "No products found." : null);
        return "display-all-products";
    }

    // -----------------------------------
    // Helper Method for Interservice Communication
    // -----------------------------------

    private Seller getLoggedInSeller(HttpSession session) {
        Seller loggedInSeller = (Seller) session.getAttribute("loggedInSeller");

        if (loggedInSeller == null) {
            ResponseEntity<Seller> response = restTemplate.getForEntity(SELLER_SERVICE_URL, Seller.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                loggedInSeller = response.getBody();
                session.setAttribute("loggedInSeller", loggedInSeller);
            }
        }
        return loggedInSeller;
    }
}
