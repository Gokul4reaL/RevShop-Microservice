package com.sellerservice.service;

import java.time.LocalDate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerservice.model.Seller;
import com.sellerservice.repository.SellerRepository;
import com.sellerservice.util.PasswordUtil;

import jakarta.servlet.http.HttpSession;

@Service
public class SellerService {
	
	@Autowired
	private SellerRepository sellerRepo;
	
	@Autowired PasswordUtil pwd_hash;
	
	
	public Seller registerSeller(Seller seller)
	{
		seller.setRegistrationDate(LocalDate.now());
		seller.setPassword(pwd_hash.hashPassword(seller.getPassword()));
		return sellerRepo.save(seller);
	}
	
	public boolean isSellerDataValid(Seller seller)
	{
        return seller.getFirstName() != null && !seller.getFirstName().isEmpty() &&
                seller.getLastName() != null && !seller.getLastName().isEmpty() &&
                seller.getEmail() != null && !seller.getEmail().isEmpty() &&
                seller.getPassword() != null && !seller.getPassword().isEmpty() &&
                seller.getPhoneNumber() != null && !seller.getPhoneNumber().isEmpty() &&
                seller.getCompanyName() != null && !seller.getCompanyName().isEmpty() &&
                seller.getCompanyAddress()!= null && !seller.getCompanyAddress().isEmpty() &&
                seller.getCompanyDescription()!= null && !seller.getCompanyDescription().isEmpty() &&
                seller.getState()!= null && !seller.getState().isEmpty() &&
                seller.getZipcode()!= null && !seller.getZipcode().isEmpty();
     
	}
	
	
	public boolean isSellerRegistered(String email, String password)
	{
		Optional<Seller> seller = sellerRepo.findByEmailAndPassword(email, pwd_hash.hashPassword(password));
		return seller.isPresent();
	}

	public Seller findByemail(String email) {
		Seller seller=sellerRepo.findByEmail(email);
		return seller;
	}
	
	// -----------------------------------
    // Get Current Logged-In Seller (For Interservice Communication)
    // -----------------------------------
    public Seller getCurrentSeller(HttpSession session) {
        Seller loggedInSeller = (Seller) session.getAttribute("loggedInSeller");
        if (loggedInSeller == null) {
            throw new IllegalStateException("No seller is currently logged in.");
        }
        return loggedInSeller;
    }
    public Seller findById(long sellerId)
	{
		Optional<Seller> seller=sellerRepo.findById(sellerId);
		return seller.get();
	}
    
}
