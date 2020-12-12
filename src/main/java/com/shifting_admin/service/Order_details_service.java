package com.shifting_admin.service;

import java.util.Date;
import java.util.List;

import com.shifting_admin.model.Merchant_order;
import com.shifting_admin.model.Order_details;

public interface Order_details_service {

	List<Merchant_order> getallbookings();

	

	List<Order_details> getbookingsbycity(String city);



	List<Order_details> getbookingsbydropdate(Date date);



	List<Order_details> getbookingsbydateandcity(String city, Date date);

}
