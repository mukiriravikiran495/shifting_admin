package com.shifting_admin.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "user_booking")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="user_id")
public class User_booking {
	
	@Id
	@Column( name = "user_id")
	private long user_id;
	
	@OneToMany( mappedBy = "user_booking")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Booking_details> booking_details;
	
	
	public Set<Booking_details> getBooking_details() {
		return booking_details;
	}

	public void setBooking_details(Set<Booking_details> booking_details) {
		this.booking_details = booking_details;
	}

	

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public User_booking(long user_id) {
		super();
		this.user_id = user_id;
		
	}

	public User_booking() {
		
	}

	
}
