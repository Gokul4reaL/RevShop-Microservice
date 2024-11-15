package com.sellerservice.controller;

import com.sellerservice.model.Seller;
import com.sellerservice.model.SellerSession;
import com.sellerservice.repository.SellerSessionRepository;
import com.sellerservice.service.SellerService;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    
    @Autowired
	private SellerSessionRepository sessionRepo;

    // -----------------------------------
    // 1. Seller Registration Endpoints
    // -----------------------------------
    
    @GetMapping("/sellerRegistration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "seller-registration";  // Renders seller-registration.html
    }

    @PostMapping("/sellerRegistration")
    public String registerSeller(@ModelAttribute("seller") Seller seller, Model model) {
        if (sellerService.isSellerDataValid(seller)) {
            sellerService.registerSeller(seller);
            return "redirect:/seller/sellerLogin";  // Redirect to seller login page
        } else {
            model.addAttribute("errorMessage", "All fields are required");
            return "seller-registration";  // Reload registration page with error
        }
    }

    // -----------------------------------
    // 2. Seller Login Endpoints
    // -----------------------------------

    @GetMapping("/sellerLogin")
    public String showLoginForm(Model model) {
        model.addAttribute("seller", new Seller());
        Optional<SellerSession> ses = sessionRepo.findById(1L);
        if(ses.isPresent())
        {
        	SellerSession sess = ses.get();
        	sess.setValue(0);
        	sess.setEmail("nil");
            sessionRepo.save(sess);
        }
        return "seller-login";  // Renders seller-login.html
    }

    @PostMapping("/sellerLogin")
    public String loginSeller(@ModelAttribute("seller") Seller seller, Model model, HttpSession session) {
        if (sellerService.isSellerRegistered(seller.getEmail(), seller.getPassword())) {
            Seller loggedInSeller = sellerService.findByemail(seller.getEmail());
            model.addAttribute("message", "Login successful!");
            session.setAttribute("loggedInSeller", loggedInSeller);  // Store seller in session
            Optional<SellerSession> ses = sessionRepo.findById(1L);
            if(ses.isPresent()) {
                SellerSession sess = ses.get();
                sess.setValue(1);
                sess.setEmail(seller.getEmail());
                System.out.println("Saving session to the database: " + sess);
                sessionRepo.save(sess);
            } else {
                // If session record doesn't exist, create a new one
                SellerSession newSession = new SellerSession();
                newSession.setValue(1);
                newSession.setEmail(seller.getEmail());
                sessionRepo.save(newSession);
            }
            return "seller-dashboard";  // Redirect to seller dashboard
        } else {
            model.addAttribute("errorMessage", "Invalid Email or Password");
            return "seller-login";  // Reload login page with error message
        }
    }

    // -----------------------------------
    // 3. Seller Dashboard Endpoint
    // -----------------------------------

    @GetMapping("/seller-dashboard")
    public String sellerDashboard(Model model,HttpSession session) {
        // Add additional attributes to the model if needed
    	String email="ssubash2651@gmail.com";
    	
    	
    	Seller loggedInSeller = sellerService.findByemail(email);
        
        session.setAttribute("loggedInSeller", loggedInSeller);
    	//session.setAttribute("loggedInSeller", loggedInSeller);
        return "seller-dashboard";  // Renders seller-dashboard.html
    }
}
