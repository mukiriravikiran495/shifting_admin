package com.shifting_admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shifting_admin.model.Merchant_details;
import com.shifting_admin.service.Merchant_list_service;

@RestController
@RequestMapping("/merchant")
public class Merchant_list_controller {
	
	@Autowired
	Merchant_list_service service;

	// get all merchants list
	@GetMapping(value = "/merchantlist")
	public List<Merchant_details> getallmerchantslist() {
		List<Merchant_details> list = service.getallmerchantslist();
		return list;
	}
	
	@GetMapping("/getallmerchantsbasedoncity")
	public List<Merchant_details> getallmerchantsbasedoncity(@RequestParam("city") String city){
		List<Merchant_details> list = service.getallmerchantsbasedoncity(city);
		return list;
		
	}
}
