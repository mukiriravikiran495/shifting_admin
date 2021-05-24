package com.shifting_admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "house_items")
public class House_items {

	@Id
	@Column( name = "house_items_id")
	//@GeneratedValue( strategy = GenerationType.AUTO)
	private int house_items_id;
	
	@Column( name = "item")
	private String item;	
	@Column( name = "quantity")
	private int quantity;
	
	@Column( name = "item_type")
	private String item_type;
	
	
	@Override
	public String toString() {
		return "House_items [house_items_id=" + house_items_id + ", item=" + item + ", quantity=" + quantity
				+ ", item_type=" + item_type + "]";
	}


	public House_items(int house_items_id, String item, int quantity, String item_type) {
		super();
		this.house_items_id = house_items_id;
		this.item = item;
		this.quantity = quantity;
		this.item_type = item_type;
	}


	public int getHouse_items_id() {
		return house_items_id;
	}


	public void setHouse_items_id(int house_items_id) {
		this.house_items_id = house_items_id;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getItem_type() {
		return item_type;
	}


	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}


	public House_items() {
		
	}
	
	
}
