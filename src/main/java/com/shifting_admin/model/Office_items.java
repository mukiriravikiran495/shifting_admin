package com.shifting_admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "office_items")
public class Office_items {

	@Id
	@Column( name = "office_items_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int office_items_id;
	
	@Column( name = "item")
	private String item;
	
	@Column( name = "quantity")
	private int quantity;
	
	@Column( name = "type")
	private String type;

	public int getOffice_items_id() {
		return office_items_id;
	}

	public void setOffice_items_id(int office_items_id) {
		this.office_items_id = office_items_id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Office_items(int office_items_id, String item, int quantity, String type) {
		super();
		this.office_items_id = office_items_id;
		this.item = item;
		this.quantity = quantity;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Office_items [office_items_id=" + office_items_id + ", item=" + item + ", quantity=" + quantity
				+ ", type=" + type + "]";
	}
	
	public Office_items() {
		
	}
	
}
