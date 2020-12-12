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
@Table( name = "merchant_order")
public class Merchant_order {

	@Id
	@Column( name = "merchant_id")
	private int merchant_id;
	
	@OneToMany( mappedBy = "merchant_order")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Order_details> order_details;

	public int getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}

	public Merchant_order(int merchant_id) {
		super();
		this.merchant_id = merchant_id;
		
	}

	
	
	public Merchant_order(int merchant_id, Set<Order_details> order_details) {
		super();
		this.merchant_id = merchant_id;
		this.order_details = order_details;
	}

	public Set<Order_details> getOrder_details() {
		return order_details;
	}

	public void setOrder_details(Set<Order_details> order_details) {
		this.order_details = order_details;
	}

	public Merchant_order() {
		
	}
}
