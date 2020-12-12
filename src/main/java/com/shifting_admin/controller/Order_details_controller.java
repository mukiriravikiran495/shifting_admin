package com.shifting_admin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifting_admin.model.Merchant_order;
import com.shifting_admin.model.Order_details;
import com.shifting_admin.service.Order_details_service;

@RestController
@RequestMapping( path = "/bookings")
public class Order_details_controller {

	@Autowired
	Order_details_service service;
	
	@GetMapping( value = "/getallbookings")
	public List<Merchant_order> getallbookings(){
		List<Merchant_order> list = service.getallbookings();
		return list;
	}
	
	
	@GetMapping( value = "/getbookingbycity/{from_location}")
	public List<Order_details> getbookingsbycity(@PathVariable("from_location") String city){
		List<Order_details> list = service.getbookingsbycity(city);
		return list;
	}
	
	@GetMapping( value = "/dropdate/{date}")
	public List<Order_details> getbookingsbydropdate(@PathVariable("date") Date date){
		List<Order_details> list = service.getbookingsbydropdate(date);
		return list;
	}
	
	@GetMapping( value = "/get/{from_location}/{date}")
	public List<Order_details> getbookingsbydateandcity(@PathVariable("from_location") String city, @PathVariable("date") Date date){
		List<Order_details> list = service.getbookingsbydateandcity(city,date);
		return list;
	}
	
}





