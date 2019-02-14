package com.wlailton.firewarningapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wlailton.firewarningapi.enums.UserType;

@Entity
@JsonInclude(Include.NON_NULL)
public class User {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	 
	@Column(nullable = false)
	@NotNull
	private String name;
	
	@Column(nullable = false)
	@NotNull
	@Email
	private String email;
	
	@Column(name = "user_type", nullable = false)
	@NotNull
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
