package com.shifting_admin.model;


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
@Table( name = "order_details")
public class Order_details {

	@Id
	@Column( name = "order_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int order_id;
	
	@Column( name = "from_location")
	private String from_location;
	
	@Column( name = "to_location")
	private String to_location;
	
	@Column( name = "shift_type")
	private String shift_type;;
	
	private Status status = Status.Requested;
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToOne( mappedBy = "order_details")
	private From_house_details from_house_details;
	
	@OneToOne( mappedBy = "order_details")
	private To_house_details to_house_details;
	
	@OneToOne( mappedBy = "order_details")
	private Delivery_details delivery_details;
	
	@OneToOne( mappedBy = "order_details")
	private User_profile user_profile;
	
	@OneToOne( mappedBy = "order_details")
	private Merchant_profile merchant_profile;
	
	@OneToMany( mappedBy = "order_details",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Selected_items> selected_items;
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn( name = "merchant_id")
	@JsonBackReference
	private Merchant_order merchant_order;

	

	public void setMerchant_profile(Merchant_profile merchant_profile) {
		this.merchant_profile = merchant_profile;
	}

	public User_profile getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(User_profile user_profile) {
		this.user_profile = user_profile;
	}



	public String getShift_type() {
		return shift_type;
	}

	public void setShift_type(String shift_type) {
		this.shift_type = shift_type;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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

	public From_house_details getFrom_house_details() {
		return from_house_details;
	}

	public void setFrom_house_details(From_house_details from_house_details) {
		this.from_house_details = from_house_details;
	}

	public To_house_details getTo_house_details() {
		return to_house_details;
	}

	public void setTo_house_details(To_house_details to_house_details) {
		this.to_house_details = to_house_details;
	}

	public Set<Selected_items> getSelected_items() {
		return selected_items;
	}

	public void setSelected_items(Set<Selected_items> selected_items) {
		this.selected_items = selected_items;
	}

	

	
	public Order_details(int order_id, String from_location, String to_location, String shift_type, Status status,
			From_house_details from_house_details, To_house_details to_house_details, Delivery_details delivery_details,
			User_profile user_profile, Merchant_profile merchant_profile, Set<Selected_items> selected_items,
			Merchant_order merchant_order) {
		super();
		this.order_id = order_id;
		this.from_location = from_location;
		this.to_location = to_location;
		this.shift_type = shift_type;
		this.status = status;
		this.from_house_details = from_house_details;
		this.to_house_details = to_house_details;
		this.delivery_details = delivery_details;
		this.user_profile = user_profile;
		this.merchant_profile = merchant_profile;
		this.selected_items = selected_items;
		this.merchant_order = merchant_order;
	}

	public Delivery_details getDelivery_details() {
		return delivery_details;
	}

	public void setDelivery_details(Delivery_details delivery_details) {
		this.delivery_details = delivery_details;
	}

	public Merchant_order getMerchant_order() {
		return merchant_order;
	}

	public void setMerchant_order(Merchant_order merchant_order) {
		this.merchant_order = merchant_order;
	}

	public Merchant_profile getMerchant_profile() {
		return merchant_profile;
	}

	public Order_details() {
		
	}
	
}
