package com.shifting_admin.dao;

import java.util.Date;
import java.util.List;

import com.shifting_admin.model.Booking_details;
import com.shifting_admin.model.Merchant_booking;

public interface Booking_details_dao {

	List<Merchant_booking> getallbookings();

	

	List<Booking_details> getbookingsbycity(String city);


	List<Booking_details> getallbookingsbasedondateandcity(String city, Date date);



	List<Booking_details> getbookingsbybookingid();



	List<Booking_details> getbookingsbasedontwodates(Date fromdate, Date todate);



	



	List<Booking_details> getallbookingsbasedonbetweendatesandlocation(Date date1, Date date2, String location);



	List<Booking_details> getallconfirmedbookings();



	List<Booking_details> getallcancelledbookings();



	List<Booking_details> getallcompletedbookings();



	void pdfbasedoncity(List<Booking_details> booking_list, String currentcity);



	List<Booking_details> getallbookingsongivendate(Date date);



	void generatepdfbookingsongivendate(List<Booking_details> list, String date);



	void generatepdfbookingsbasedondateandcity(List<Booking_details> list, String city, Date date1);



	void generatepdfbookingsbasedontwodates(List<Booking_details> list, Date fromdate, Date todate);



	void generatebookingsbasedonbetweendatesandlocation(List<Booking_details> list, String location, Date date1,
			Date date2);



	List<Booking_details> getallbookingsmerchantandlocation(String location, String merchant_name);



	List<Booking_details> getallbookingsmerchantandlocationbetweendates(String location, String merchant_name,
			Date date1, Date date2);



	void generatepdfallbookingsmerchantandlocation(String location, String merchant_name, List<Booking_details> list);



	void generatepdfbookingsmerchantandlocationbetweendates(String location, String merchant_name, Date fromdate,
			Date todate,List<Booking_details> list);

}
