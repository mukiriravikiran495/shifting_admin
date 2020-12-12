package com.shifting_admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table( name = "user_profile")
public class User_profile {
	
	@Id
	@Column( name = "user_profile_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_profile_id;
	
	@Column( name = "user_id")
	private int user_id;
	
	@Column( name = "email")
	private String email;
	
	@Column( name = "mobilenumber")
	private long mobilenumber;
	
	@Column( name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "order_id")
	@JsonBackReference
	private Order_details order_details;
	
	
	public int getUser_id() {
		return user_id;
	}



	public Order_details getOrder_details() {
		return order_details;
	}



	public void setOrder_details(Order_details order_details) {
		this.order_details = order_details;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getMobilenumber() {
		return mobilenumber;
	}



	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}



	public User_profile() {
		
	}

}
