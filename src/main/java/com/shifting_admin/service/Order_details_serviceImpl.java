package com.shifting_admin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifting_admin.dao.Order_details_dao;
import com.shifting_admin.model.Merchant_order;
import com.shifting_admin.model.Order_details;

@Service("merchant_order_service")
public class Order_details_serviceImpl implements Order_details_service{

	@Autowired
	Order_details_dao dao;
	
	@Override
	public List<Merchant_order> getallbookings() {
		return dao.getallbookings();
		
	}

	
	@Override
	public List<Order_details> getbookingsbycity(String city) {
		return dao.getbookingsbycity(city);
	}


	@Override
	public List<Order_details> getbookingsbydropdate(Date date) {
		return dao.getbookingsbydropdate(date);
	}


	@Override
	public List<Order_details> getbookingsbydateandcity(String city, Date date) {
		return dao.getbookingsbydateandcity(city,date);
	}

}
