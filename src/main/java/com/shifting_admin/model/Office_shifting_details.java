package com.shifting_admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "office_shifting_details")
public class Office_shifting_details {

	@Id
	@Column( name = "office_shifting_details_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int office_shifting_details_id;
	
	@Column( name = "fromlocation")
	private String fromlocation;
	
	@Column( name = "tolocation")
	private String tolocation;
	
	@OneToOne( mappedBy = "office_shifting_details", fetch=FetchType.EAGER)
	private From_office_details from_office_details;
	
	@OneToOne( mappedBy = "office_shifting_details", fetch=FetchType.EAGER)
	private To_office_details to_office_details;

	public Office_shifting_details(int office_shifting_details_id, String fromlocation, String tolocation) {
		super();
		this.office_shifting_details_id = office_shifting_details_id;
		this.fromlocation = fromlocation;
		this.tolocation = tolocation;
	}

	public int getOffice_shifting_details_id() {
		return office_shifting_details_id;
	}

	public void setOffice_shifting_details_id(int office_shifting_details_id) {
		this.office_shifting_details_id = office_shifting_details_id;
	}

	public String getFromlocation() {
		return fromlocation;
	}

	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}

	public String getTolocation() {
		return tolocation;
	}

	public void setTolocation(String tolocation) {
		this.tolocation = tolocation;
	}

	

	public From_office_details getFrom_office_details() {
		return from_office_details;
	}

	public void setFrom_office_details(From_office_details from_office_details) {
		this.from_office_details = from_office_details;
	}

	public To_office_details getTo_office_details() {
		return to_office_details;
	}

	public void setTo_office_details(To_office_details to_office_details) {
		this.to_office_details = to_office_details;
	}

	

	@Override
	public String toString() {
		return "Office_shifting_details [office_shifting_details_id=" + office_shifting_details_id + ", fromlocation="
				+ fromlocation + ", tolocation=" + tolocation + ", from_office_details=" + from_office_details
				+ ", to_office_details=" + to_office_details + "]";
	}
	
	public Office_shifting_details() {
		
	}
}
