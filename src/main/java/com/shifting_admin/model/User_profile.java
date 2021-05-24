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
	private long user_id;
	
	@Column( name = "email")
	private String email;
	
	@Column( name = "mobilenumber")
	private long mobilenumber;

	@Column( name = "name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "booking_id")
	@JsonBackReference
	private Booking_details booking_details;
	

	
	
	


	public void setUser_profile_id(int user_profile_id) {
		this.user_profile_id = user_profile_id;
	}


	

	public long getUser_id() {
		return user_id;
	}


	public void setBooking_details(Booking_details booking_details) {
		this.booking_details = booking_details;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public User_profile(long user_id, String email, long mobilenumber, String name) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.mobilenumber = mobilenumber;
		this.name = name;
	}


	public void setUser_id(long user_id) {
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
