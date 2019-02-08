package com.wlailton.firewarningapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wlailton.firewarningapi.enums.UserType;

@Entity
public class User {
	
	@Id
	 @GeneratedValue
	private Long id;
	 
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name = "user_type", nullable = false)
	private UserType userType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserType getType() {
		return userType;
	}
	public void setType(UserType userType) {
		this.userType = userType;
	}
}
