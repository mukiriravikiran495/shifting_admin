package com.shifting_admin.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.shifting_admin.dao.Booking_details_dao;
import com.shifting_admin.model.Booking_details;
import com.shifting_admin.model.Merchant_booking;

@Service("merchant_order_service")
public class Booking_details_serviceImpl implements Booking_details_service{

	@Autowired
	Booking_details_dao dao;
	
	@Override
	public List<Merchant_booking> getallbookings() {
		return dao.getallbookings();
		
	}

	
	@Override
	public List<Booking_details> getbookingsbycity(String city) {
		return dao.getbookingsbycity(city);
	}


	@Override
	public List<Booking_details> getbookingsbasedontwodates(Date fromdate, Date todate) {
		return dao.getbookingsbasedontwodates(fromdate,todate);
	}


	@Override
	public List<Booking_details> getallbookingsbasedondateandcity(String city, Date date) {
		return dao.getallbookingsbasedondateandcity(city,date);
	}


	@Override
	public List<Booking_details> getbookingsbybookingid() {
		return dao.getbookingsbybookingid();
	}


	

	@Override
	public List<Booking_details> getallbookingsbasedonbetweendatesandlocation(Date date1, Date date2, String location) {
		return dao.getallbookingsbasedonbetweendatesandlocation(date1,date2,location);
	}


	@Override
	public List<Booking_details> getallconfirmedbookings() {
		return dao.getallconfirmedbookings();
	}


	@Override
	public List<Booking_details> getallcancelledbookings() {
		return dao.getallcancelledbookings();
	}

	@Override
	public List<Booking_details> getallcompletedbookings() {
		return dao.getallcompletedbookings();
	}


	@Override
	public void pdfbasedoncity(List<Booking_details> booking_list, String currentcity) {
		dao.pdfbasedoncity(booking_list,currentcity);
		
	}


	@Override
	public List<Booking_details> getallbookingsongivendate(Date date) {
		
		return dao.getallbookingsongivendate(date);
	}


	@Override
	public void generatepdfbookingsongivendate(List<Booking_details> list, String date) {
		dao.generatepdfbookingsongivendate(list,date);
	}


	@Override
	public void generatepdfbookingsbasedondateandcity(List<Booking_details> list, String city, Date date1) {
		dao.generatepdfbookingsbasedondateandcity(list,city,date1);
	}


	@Override
	public void generatepdfbookingsbasedontwodates(Date fromdate, Date todate, List<Booking_details> list) {
		dao.generatepdfbookingsbasedontwodates(list,fromdate,todate);
	}


	@Override
	public void generatebookingsbasedonbetweendatesandlocation(List<Booking_details> list, Date date1, Date date2,
			String location) {
		dao.generatebookingsbasedonbetweendatesandlocation(list,location,date1,date2);
	}


	@Override
	public List<Booking_details> getallbookingsmerchantandlocation(String location, String merchant_name) {
		
		return dao.getallbookingsmerchantandlocation(location,merchant_name);
	}


	@Override
	public List<Booking_details> getallbookingsmerchantandlocationbetweendates(String location, String merchant_name,
			Date date1, Date date2) {
		return dao.getallbookingsmerchantandlocationbetweendates(location,merchant_name,date1,date2);
	}


	@Override
	public void generatepdfallbookingsmerchantandlocation(String location, String merchant_name,
			List<Booking_details> list) {
		dao.generatepdfallbookingsmerchantandlocation(location,merchant_name,list);
	}


	@Override
	public void generatepdfbookingsmerchantandlocationbetweendates(String location, String merchant_name,
			Date fromdate, Date todate, List<Booking_details> list) {
		dao.generatepdfbookingsmerchantandlocationbetweendates(location,merchant_name,fromdate,todate,list);
	}


	
}
