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
@Table( name = "from_house_details")
public class From_house_details {

	
	
	@Id
	@Column( name = "from_house_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int from_house_id;
	
	@Column( name = "floor")
	private String floor;
	
	@Column( name = "service_elevator")
	private String service_elevator;
	
	@Column( name = "pickup_date")
	private Date pickup_date;
	
	@Column( name = "house_type")
	private String house_type;
	
	@Column( name = "appointment_date")
	private Date appointment_date;
	
	@Column( name = "pickup_contact")
	private long pickup_contact;

	@Column( name = "pickupname")
	private String pickupname;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "order_id")
	@JsonBackReference
	private Order_details order_details;

	
	
	public int getFrom_house_id() {
		return from_house_id;
	}

	public void setFrom_house_id(int from_house_id) {
		this.from_house_id = from_house_id;
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

	public Date getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(Date pickup_date) {
		this.pickup_date = pickup_date;
	}

	public Date getAppointment_date() {
		return appointment_date;
	}

	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}

	public long getPickup_contact() {
		return pickup_contact;
	}

	public void setPickup_contact(long pickup_contact) {
		this.pickup_contact = pickup_contact;
	}

	public String getPickupname() {
		return pickupname;
	}

	public void setPickupname(String pickupname) {
		this.pickupname = pickupname;
	}


	public Order_details getOrder_details() {
		return order_details;
	}

	

	public From_house_details(int from_house_id, String floor, String service_elevator, Date pickup_date,
			String house_type, Date appointment_date, long pickup_contact, String pickupname,
			Order_details order_details) {
		super();
		this.from_house_id = from_house_id;
		this.floor = floor;
		this.service_elevator = service_elevator;
		this.pickup_date = pickup_date;
		this.house_type = house_type;
		this.appointment_date = appointment_date;
		this.pickup_contact = pickup_contact;
		this.pickupname = pickupname;
		this.order_details = order_details;
	}

	public String getHouse_type() {
		return house_type;
	}

	public void setOrder_details(Order_details order_details) {
		this.order_details = order_details;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public From_house_details() {
		
	}
	
}
