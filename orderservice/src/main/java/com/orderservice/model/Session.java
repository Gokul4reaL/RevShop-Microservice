package com.orderservice.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "session")
public class Session {

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
