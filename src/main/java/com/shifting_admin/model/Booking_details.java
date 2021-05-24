package com.shifting_admin.model;



import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table( name = "booking_details")
public class Booking_details {

	@Id
	@Column( name = "booking_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long booking_id;
	
	@Column( name = "from_location")
	private String from_location;
	
	@Column( name = "to_location")
	private String to_location;
	
	@Column( name = "shift_type")
	private String shift_type;
	
	@Column( name = "pickup_date")
	private Date pickup_date;
	
	@Column( name = "from_floor")
	private String from_floor;
	
	@Column( name = "to_floor")
	private String to_floor;
	
	@Column( name = "from_lift")
	private String from_lift;
	
	@Column( name = "to_lift")
	private String to_lift;
	
	@Column( name = "drop_date")
	private Date drop_date;
	
	private Status status = Status.Confirm;
	
	
	public Date getDrop_date() {
		return drop_date;
	}

	public void setDrop_date(Date drop_date) {
		this.drop_date = drop_date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	
	



	@OneToOne( mappedBy = "booking_details")
	private User_profile user_profile;
	
	@OneToOne( mappedBy = "booking_details")
	private Merchant_details merchant_details;
	
	@OneToOne( mappedBy = "booking_details")
	private Final_price_details final_price_details;
	
	
	@OneToMany( mappedBy = "booking_details",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Selected_items> selected_items;
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn( name = "user_id")
	private User_booking user_booking;

	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn( name = "merchant_id")
	@JsonBackReference
	private Merchant_booking merchant_booking;
	
	

	public Final_price_details getFinal_price_details() {
		return final_price_details;
	}

	public void setFinal_price_details(Final_price_details final_price_details) {
		this.final_price_details = final_price_details;
	}

	
	public Merchant_details getMerchant_details() {
		return merchant_details;
	}

	public void setMerchant_details(Merchant_details merchant_details) {
		this.merchant_details = merchant_details;
	}

	public Date getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(Date pickup_date) {
		this.pickup_date = pickup_date;
	}

	public String getFrom_floor() {
		return from_floor;
	}

	public void setFrom_floor(String from_floor) {
		this.from_floor = from_floor;
	}

	public String getTo_floor() {
		return to_floor;
	}

	public void setTo_floor(String to_floor) {
		this.to_floor = to_floor;
	}

	public String getFrom_lift() {
		return from_lift;
	}

	public void setFrom_lift(String from_lift) {
		this.from_lift = from_lift;
	}

	public String getTo_lift() {
		return to_lift;
	}

	public void setTo_lift(String to_lift) {
		this.to_lift = to_lift;
	}

	public User_profile getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(User_profile user_profile) {
		this.user_profile = user_profile;
	}

	

	

	public void setMerchant_booking(Merchant_booking merchant_booking) {
		this.merchant_booking = merchant_booking;
	}

	public String getShift_type() {
		return shift_type;
	}

	public void setShift_type(String shift_type) {
		this.shift_type = shift_type;
	}

	

	public long getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(long booking_id) {
		this.booking_id = booking_id;
	}

	public String getFrom_location() {
		return from_location;
	}

	public String setFrom_location(String from_location) {
		 return this.from_location = from_location;
	}

	public String getTo_location() {
		return to_location;
	}

	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}

	
	public Set<Selected_items> getSelected_items() {
		return selected_items;
	}

	public void setSelected_items(Set<Selected_items> selected_items) {
		this.selected_items = selected_items;
	}

	
	
	public void setUser_booking(User_booking user_booking) {
		this.user_booking = user_booking;
	}

	public Booking_details() {
		
	}
	
	
}
