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
@Table( name = "merchant_details")
public class Merchant_details {

	@Id
	@Column( name = "merchant_details_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int merchant_details_id;
	
	@Column( name = "merchant_id")
	private long merchant_id;
	
	@Column( name = "mobilenumber")
	private long mobilenumber;
	
	@Column( name = "merchant_email")
	private String merchant_email;
	
	@Column( name = "merchant_name")
	private String merchant_name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "booking_id")
	@JsonBackReference
	private Booking_details booking_details;

	


	public long getMerchant_id() {
		return merchant_id;
	}



	public void setMerchant_id(long merchant_id) {
		this.merchant_id = merchant_id;
	}



	public long getMobilenumber() {
		return mobilenumber;
	}



	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}



	public String getMerchant_email() {
		return merchant_email;
	}



	public void setMerchant_email(String merchant_email) {
		this.merchant_email = merchant_email;
	}



	public String getMerchant_name() {
		return merchant_name;
	}



	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	
	
	public void setBooking_details(Booking_details booking_details) {
		this.booking_details = booking_details;
	}

	public Merchant_details() {
		
	}
}
