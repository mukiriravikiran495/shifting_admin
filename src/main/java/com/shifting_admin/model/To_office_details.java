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
@Table( name = "to_office_details")
public class To_office_details {

	@Id
	@Column( name = "to_office_details_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int to_office_details_id;
	
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
	@JoinColumn( name = "office_shifting_details_id")
	@JsonBackReference
	private Office_shifting_details office_shifting_details;

	public int getTo_office_details_id() {
		return to_office_details_id;
	}

	public void setTo_office_details_id(int to_office_details_id) {
		this.to_office_details_id = to_office_details_id;
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

	public String getDropname() {
		return dropname;
	}

	public void setDropname(String dropname) {
		this.dropname = dropname;
	}

	

	public Office_shifting_details getOffice_shifting_details() {
		return office_shifting_details;
	}

	public void setOffice_shifting_details(Office_shifting_details office_shifting_details) {
		this.office_shifting_details = office_shifting_details;
	}

	public To_office_details(int to_office_details_id, String floor, String service_elevator, Date drop_date,
			long drop_contact, String dropname, Office_shifting_details office_shifting_details) {
		super();
		this.to_office_details_id = to_office_details_id;
		this.floor = floor;
		this.service_elevator = service_elevator;
		this.drop_date = drop_date;
		this.drop_contact = drop_contact;
		this.dropname = dropname;
		this.office_shifting_details = office_shifting_details;
	}

	@Override
	public String toString() {
		return "To_office_details [to_office_details_id=" + to_office_details_id + ", floor=" + floor
				+ ", service_elevator=" + service_elevator + ", drop_date=" + drop_date + ", drop_contact="
				+ drop_contact + ", dropname=" + dropname + ", Office_shifting_details=" + office_shifting_details
				+ "]";
	}

	
	public To_office_details() {
		
	}
	
}
