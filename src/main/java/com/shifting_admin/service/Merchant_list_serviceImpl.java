package com.shifting_admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifting_admin.dao.Merchant_list_dao;
import com.shifting_admin.model.Merchant_details;

@Service("merchant_list_service")
public class Merchant_list_serviceImpl implements Merchant_list_service{

	@Autowired
	Merchant_list_dao dao;
	
	@Override
	public List<Merchant_details> getallmerchantslist() {
		return dao.getallmerchantslist();
	}

	@Override
	public List<Merchant_details> getallmerchantsbasedoncity(String city) {
		return dao.getallmerchantsbasedoncity(city);
	}

}
