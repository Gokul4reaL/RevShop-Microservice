package com.wishlistservice.service;

import com.wishlistservice.model.Product;
import com.wishlistservice.model.User;
import com.wishlistservice.model.WishlistItem;
import com.wishlistservice.repository.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    public void addProductToWishlist(User user, Product product) {
        WishlistItem wishlistItem = wishlistItemRepository.findByUserUserId(user.getUserId())
                .stream()
                .filter(item -> item.getProduct().getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(null);

        if (wishlistItem == null) {
            wishlistItem = new WishlistItem();
            wishlistItem.setUser(user);
            wishlistItem.setProduct(product);
            wishlistItemRepository.save(wishlistItem);
        }
    }

    public void removeProductFromWishlist(User user, Long productId) {
        WishlistItem wishlistItem = wishlistItemRepository.findByUserUserId(user.getUserId())
                .stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (wishlistItem != null) {
            wishlistItemRepository.delete(wishlistItem);
        }
    }

    public List<WishlistItem> getWishlistByUser(User user) {
    	System.out.println("wishlist service");
        return wishlistItemRepository.findByUserUserId(user.getUserId());
    }
}