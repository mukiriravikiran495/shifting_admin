package com.shifting_admin.service;

import java.util.List;

import com.shifting_admin.model.Merchant_details;

public interface Merchant_list_service {

	List<Merchant_details> getallmerchantslist();

	List<Merchant_details> getallmerchantsbasedoncity(String city);

}
