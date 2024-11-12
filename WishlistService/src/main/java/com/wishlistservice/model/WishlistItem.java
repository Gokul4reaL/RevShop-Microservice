//package com.wishlistservice.model;
//
////public class WishlistItem {
////	
////}
//
////package com.wishlistservice.model;
//
////import com.Revshop.revshop.model.Product;
////import com.Revshop.revshop.model.User;
//
////public class WishlistItem {
////
////}
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "wishlist_items")
//public class WishlistItem {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//
//  @ManyToOne
//  @JoinColumn(name = "user_id", nullable = false)
//  private User user;
//
//  @ManyToOne
//  @JoinColumn(name = "product_id", nullable = false)
//  private Product product;
//
//  // Getters and Setters
//
//  public Long getId() {
//      return id;
//  }
//
//  public void setId(Long id) {
//      this.id = id;
//  }
//
//  public User getUser() {
//      return user;
//  }
//
//  public void setUser(User user) {
//      this.user = user;
//  }
//
//  public Product getProduct() {
//      return product;
//  }
//
//  public void setProduct(Product product) {
//      this.product = product;
//  }
//}
package com.wishlistservice.model;
import jakarta.persistence.*;
import java.util.*; 

@Entity
@Table(name = "wishlist_items")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    
}
