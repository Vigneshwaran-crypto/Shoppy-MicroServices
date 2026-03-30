package com.example.shoppy.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class WebModal {
	
	private Integer id;
	
	@NotBlank(message =  "Email is Required")
	private String email;
	
	@NotBlank(message =  "Name is Required")
	private String name;
	
	@NotBlank(message =  "Phone is Required")
	private String phone;
	
	private String role;
	
	@NotBlank(message =  "Address is Required")
	private String address;
	
	@NotBlank(message =  "Pincode is Required")
	private String pincode;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
