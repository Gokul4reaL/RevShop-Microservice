package com.wishlistservice.repository;

//public interface WishlistItemRepository {
//
//}

//package com.wishlistservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wishlistservice.model.WishlistItem;

import java.util.List;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUserUserId(Long userId);
}

