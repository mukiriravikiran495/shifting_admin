package com.shifting_admin.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shifting_admin.model.Merchant_order;
import com.shifting_admin.model.Order_details;

@Repository("merchant_order_dao")
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Order_details_daoImpl implements Order_details_dao{

	@Autowired
	SessionFactory factory;
	
	
	@Override
	public List<Merchant_order> getallbookings() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Merchant_order");
		List<Merchant_order> list = query.list();
		return list;
	}

	
	
	@Override
	public List<Order_details> getbookingsbycity(String city) {
		Session session = factory.getCurrentSession();
		
		Query query = session.createQuery(" from Order_details where from_location = :from_location ");
		query.setParameter("from_location", city);
		System.out.println("Hello...");
		List<Order_details> list = query.list();
		return list;
	}



	@Override
	public List<Order_details> getbookingsbydropdate(Date date) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(" from Order_details o inner join o.from_house_details f where f.pickup_date = :pickup_date and o.from_location = :from_location");
		query.setParameter("pickup_date", date);
		query.setParameter("from_location", "hyderabad");
		List<Order_details> list = query.list();
		return list;
		
	}



	@Override
	public List<Order_details> getbookingsbydateandcity(String city, Date date) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(" from Order_details o inner join o.from_house_details f where f.pickup_date = :pickup_date and o.from_location = :from_location");
		query.setParameter("pickup_date", date);
		query.setParameter("from_location", city);
		List<Order_details> list = query.list();
		return list;
	}

}
