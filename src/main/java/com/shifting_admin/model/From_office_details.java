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
@Table( name = "from_office_details")
public class From_office_details {

	@Id
	@Column( name = "from_office_details_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int from_office_details_id;
	
	@Column( name = "floor")
	private String floor;
	
	@Column( name = "service_elevator")
	private String service_elevator;
	
	@Column( name = "pickup_date")
	private Date pickup_date;
	
	@Column( name = "appointment_date")
	private Date appointment_date;
	
	@Column( name = "pickup_contact")
	private long pickup_contact;
	
	@Column( name = "pickupname")
	private String pickupname;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "office_shifting_details_id")
	@JsonBackReference
	private Office_shifting_details office_shifting_details;

	
	@Override
	public String toString() {
		return "From_office_details [from_office_details_id=" + from_office_details_id + ", floor=" + floor
				+ ", service_elevator=" + service_elevator + ", pickup_date=" + pickup_date + ", appointment_date="
				+ appointment_date + ", pickup_contact=" + pickup_contact + ", pickupname=" + pickupname
				+ ", office_shifting_details=" + office_shifting_details + "]";
	}


	public From_office_details(int from_office_details_id, String floor, String service_elevator, Date pickup_date,
			Date appointment_date, long pickup_contact, String pickupname,
			Office_shifting_details office_shifting_details) {
		super();
		this.from_office_details_id = from_office_details_id;
		this.floor = floor;
		this.service_elevator = service_elevator;
		this.pickup_date = pickup_date;
		this.appointment_date = appointment_date;
		this.pickup_contact = pickup_contact;
		this.pickupname = pickupname;
		this.office_shifting_details = office_shifting_details;
	}


	public int getFrom_office_details_id() {
		return from_office_details_id;
	}


	public void setFrom_office_details_id(int from_office_details_id) {
		this.from_office_details_id = from_office_details_id;
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


	public Office_shifting_details getOffice_shifting_details() {
		return office_shifting_details;
	}


	public void setOffice_shifting_details(Office_shifting_details office_shifting_details) {
		this.office_shifting_details = office_shifting_details;
	}


	public From_office_details() {
		
	}
}
