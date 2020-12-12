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
@Table( name = "to_house_details")
public class To_house_details {

	@Id
	@Column( name = "to_house_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int to_house_id;
	
	@Column( name = "floor")
	private String floor;
	
	@Column( name = "service_elevator")
	private String service_elevator;
	
	@Column( name = "drop_date")
	private Date drop_date;
	
	@Column( name = "drop_contact")
	private long drop_contact;
	
	@Column( name = "dropname")
	private String dropname;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn( name = "order_id")
	@JsonBackReference
	private Order_details order_details;

	public int getTo_house_id() {
		return to_house_id;
	}

	public void setTo_house_id(int to_house_id) {
		this.to_house_id = to_house_id;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getService_elevator() {
		return service_elevator;
	}

	public void setService_elevator(String service_elevator) {
		this.service_elevator = service_elevator;
	}

	public Date getDrop_date() {
		return drop_date;
	}

	public void setDrop_date(Date drop_date) {
		this.drop_date = drop_date;
	}

	public long getDrop_contact() {
		return drop_contact;
	}

	public void setDrop_contact(long drop_contact) {
		this.drop_contact = drop_contact;
	}

	

	public To_house_details(int to_house_id, String floor, String service_elevator, Date drop_date, long drop_contact,
			String dropname, Order_details order_details) {
		super();
		this.to_house_id = to_house_id;
		this.floor = floor;
		this.service_elevator = service_elevator;
		this.drop_date = drop_date;
		this.drop_contact = drop_contact;
		this.dropname = dropname;
		this.order_details = order_details;
	}

	public Order_details getOrder_details() {
		return order_details;
	}

	public void setOrder_details(Order_details order_details) {
		this.order_details = order_details;
	}

	public String getDropname() {
		return dropname;
	}

	public void setDropname(String dropname) {
		this.dropname = dropname;
	}

	public To_house_details() {
		
	}
}
