package com.shifting_admin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shifting_admin.model.Merchant_details;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
@Repository("Merchant_list_dao")
public class Merchant_list_daoImpl implements Merchant_list_dao{

	@Autowired
	SessionFactory factory;
	
	
	@Override
	public List<Merchant_details> getallmerchantslist() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Merchant_profile");
		List<Merchant_details> list = query.list();
		return list;
	}


	@Override
	public List<Merchant_details> getallmerchantsbasedoncity(String city) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Merchant_profile where merchant_city = :location");
		query.setParameter("location", city);
		List<Merchant_details> list = query.list();
		return list;
	}

}
