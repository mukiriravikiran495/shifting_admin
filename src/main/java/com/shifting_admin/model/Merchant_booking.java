package com.shifting_admin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table( name = "merchant_booking")
public class Merchant_booking {

	@Id
	@Column( name = "merchant_id")
	private long merchant_id;
	
	@OneToMany( mappedBy = "merchant_booking")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Booking_details> booking_details;

	

	public long getMerchant_id() {
		return merchant_id;
	}



	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}



	
	

	public Set<Booking_details> getBooking_details() {
		return booking_details;
	}

	public void setBooking_details(Set<Booking_details> booking_details) {
		this.booking_details = booking_details;
	}

	
	
	public Merchant_booking(long merchant_id, Set<Booking_details> booking_details) {
		super();
		this.merchant_id = merchant_id;
		this.booking_details = booking_details;
	}



	public Merchant_booking() {
		
	}
}
