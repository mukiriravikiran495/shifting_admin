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
@Table( name = "final_price_details")
public class Final_price_details {

	@Id
	@Column( name = "final_price_details_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int frinal_price_details_id;
	
	@Column( name = "amount")
	private long amount;
	
	@Column( name = "offer")
	private int offer;
	
	@Column( name = "tax")
	private int tax;
	
	@Column( name = "labour_charges")
	private int labour_charges;
	
	@Column( name = "installanduninstall")
	private int installanduninstall;
	
	@Column( name = "final_amount")
	private long final_amount;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "booking_id")
	@JsonBackReference
	private Booking_details booking_details;


	@Override
	public String toString() {
		return "Final_price_details [frinal_price_details_id=" + frinal_price_details_id + ", amount=" + amount
				+ ", offer=" + offer + ", tax=" + tax + ", labour_charges=" + labour_charges + ", installanduninstall="
				+ installanduninstall + ", final_amount=" + final_amount + ", booking_details=" + booking_details + "]";
	}


	public int getFrinal_price_details_id() {
		return frinal_price_details_id;
	}


	public void setFrinal_price_details_id(int frinal_price_details_id) {
		this.frinal_price_details_id = frinal_price_details_id;
	}


	public long getAmount() {
		return amount;
	}


	public void setAmount(long amount) {
		this.amount = amount;
	}


	public int getOffer() {
		return offer;
	}


	public void setOffer(int offer) {
		this.offer = offer;
	}


	public int getTax() {
		return tax;
	}


	public void setTax(int tax) {
		this.tax = tax;
	}


	public int getLabour_charges() {
		return labour_charges;
	}


	public void setLabour_charges(int labour_charges) {
		this.labour_charges = labour_charges;
	}


	public int getInstallanduninstall() {
		return installanduninstall;
	}


	public void setInstallanduninstall(int installanduninstall) {
		this.installanduninstall = installanduninstall;
	}


	public long getFinal_amount() {
		return final_amount;
	}


	public void setFinal_amount(long final_amount) {
		this.final_amount = final_amount;
	}


	public Booking_details getBooking_details() {
		return booking_details;
	}


	public void setBooking_details(Booking_details booking_details) {
		this.booking_details = booking_details;
	}
	
	public Final_price_details() {
		
	}
	
}
