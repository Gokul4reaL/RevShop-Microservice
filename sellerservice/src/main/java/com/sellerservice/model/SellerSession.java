package com.sellerservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "seller_session")
public class SellerSession {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
	
	@Column(name = "value", nullable = false)
    private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	@Column(name = "email" , nullable = false)
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
