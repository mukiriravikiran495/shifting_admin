
package com.shifting_admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "house_items")
public class House_items {

	@Id
	@Column( name = "house_items_id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int house_items_id;
	
	

	

	public House_items() {
		
	}
	
	
}
