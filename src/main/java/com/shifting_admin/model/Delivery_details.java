package com.shifting_admin.model;

import java.util.Date;

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
@Table( name = "delivery_details")
public class Delivery_details {

	@Id
	@Column( name = "delivery_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int delivery_id;
	
	@Column( name = "price")
	private int price;
	
	@Column( name ="delivery_date")
	private Date delivery_date;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "order_id")
	@JsonBackReference
	private Order_details order_details;

	public int getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(int delivery_id) {
		this.delivery_id = delivery_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}


	public Delivery_details(int delivery_id, int price, Date delivery_date, Order_details order_details) {
		super();
		this.delivery_id = delivery_id;
		this.price = price;
		this.delivery_date = delivery_date;
		this.order_details = order_details;
	}

	public Order_details getOrder_details() {
		return order_details;
	}

	public void setOrder_details(Order_details order_details) {
		this.order_details = order_details;
	}

	public Delivery_details() {
		
	}
}
