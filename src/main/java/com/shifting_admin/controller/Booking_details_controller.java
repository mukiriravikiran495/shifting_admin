package com.shifting_admin.controller;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shifting_admin.model.Booking_details;
import com.shifting_admin.model.Merchant_booking;
import com.shifting_admin.service.Booking_details_service;

@RestController
@RequestMapping( path = "/bookings")
public class Booking_details_controller {

	@Autowired
	Booking_details_service service;
	
	//get bookings based on  merchants
	@GetMapping( value = "/getbookingsbymerchant")
	public List<Merchant_booking> getallbookings(){
		List<Merchant_booking> list = service.getallbookings();
		return list;
	}
	
	//get all bookings till date and all locations Done
	@GetMapping( value = "/getallbookingstilldate")
	public List<Booking_details> getbookings(){
		
		List<Booking_details> list = service.getbookingsbybookingid();
		return list;
	}
	
	
	//get all bookings based on city till date Done
	@GetMapping( value = "/getbookingbasedonbycity")
	public List<Booking_details> getbookingsbycity(@RequestParam("from_city") String city){
		List<Booking_details> list = service.getbookingsbycity(city);
		return list;
	}
	
	//generate pdf to all bookings based on city till date Done
	@GetMapping( value = "/generatepdfbasedoncity")
	public void generatepdfbasedoncity(@RequestParam("from_city") String city) {
		List<Booking_details> booking_list = service.getbookingsbycity(city);
		
		
		service.pdfbasedoncity(booking_list,city);
		System.out.println("PDF downloaded succesfully ..!!");
		
	}
	
	//get all bookings based on date and all locations
	@GetMapping( value = "/getallbookingsongivendate")
	public List<Booking_details> getbookingsongivendate(@RequestParam("date") String date) {
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		List<Booking_details> list = service.getallbookingsongivendate(date1);
		return list;
	}	
	
	//get all bookings based on date and all locations default
		@SuppressWarnings("unused")
		@GetMapping( value = "/generatepdfallbookingsongivendate")
		public void generatepdfbookingsongivendate(@RequestParam("date") String date) {
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			//List<Booking_details> list = service.getallbookingsongivendate(date1);
			//service.generatepdfbookingsongivendate(list,date);
		}
		
	
	//get all bookings based on today pickup date and all city Done
	@GetMapping( value = "/getallbookingsbasedontwodates")
	public List<Booking_details> getbookingsbasedontwodates(@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate) throws ParseException{
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);  
		Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(todate); 
		System.out.println("Controller class :"+fromdate);
		System.out.println("Controller class :"+todate);
		List<Booking_details> list = service.getbookingsbasedontwodates(date1,date2);
		return list;
	}
	
	//get all bookings based on today pickup date and all city Done
		@GetMapping( value = "/generatepdfbookingsbasedontwodates")
		public void generatepdfbookingsbasedontwodates(@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate) throws ParseException{
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);  
			Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(todate); 
			System.out.println("Controller class :"+fromdate);
			System.out.println("Controller class :"+todate);
			List<Booking_details> list = service.getbookingsbasedontwodates(date1,date2);
			service.generatepdfbookingsbasedontwodates(date1,date2,list);
		}
	
	//get all bookings based on given date and city Done
	@GetMapping( value = "/getallbookingsbasedongivendateandcity")
	public List<Booking_details> getallbookingsbasedondateandcity(@RequestParam("from_location") String city, @RequestParam("date") String date){
		System.out.println("Entered ..");
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		List<Booking_details> list = service.getallbookingsbasedondateandcity(city,date1);
		return list;
	}
	
	//generate pdf to all bookings based on given date and city Done
		@GetMapping( value = "/generatepdfbookingsbasedondateandcity")
		public void generatepdfbookingsbasedondateandcity(@RequestParam("from_location") String city, @RequestParam("date") String date){
			System.out.println("Entered ..");
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			List<Booking_details> list = service.getallbookingsbasedondateandcity(city,date1);
			service.generatepdfbookingsbasedondateandcity(list,city,date1);
		}
	
	
	//get all booking records based on dates and location Done
	@GetMapping( value = "/getallbookingsbasedonbetweendatesandlocation")
	public List<Booking_details> getallbookingsbasedondateandlocation(@RequestParam("fromdate") String fromdate,@RequestParam
			("todate") String todate,@RequestParam("from_location") String location) {
		Date date1 = null,date2 = null;
		try {
			 date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
			 date2=new SimpleDateFormat("yyyy-MM-dd").parse(todate); 
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
		System.out.println("fromdate : "+fromdate);
		System.out.println("tdate : "+todate);
		System.out.println("location : "+ location);
		
		List<Booking_details> list = service.getallbookingsbasedonbetweendatesandlocation(date1,date2,location);
		return list;
	}
	
	//get all booking records based on dates and location Done
		@GetMapping( value = "/generatepdfbookingsbasedonbetweendatesandlocation")
		public void generatebookingsbasedonbetweendatesandlocation(@RequestParam("fromdate") String fromdate,@RequestParam
				("todate") String todate,@RequestParam("from_location") String location) {
			Date date1 = null,date2 = null;
			try {
				 date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
				 date2=new SimpleDateFormat("yyyy-MM-dd").parse(todate); 
			} catch (ParseException e) {
				
				e.printStackTrace();
			}  
			System.out.println("fromdate : "+fromdate);
			System.out.println("tdate : "+todate);
			System.out.println("location : "+ location);
			
			List<Booking_details> list = service.getallbookingsbasedonbetweendatesandlocation(date1,date2,location);
			service.generatebookingsbasedonbetweendatesandlocation(list,date1,date2,location);
		}
	
	//get all confirmed bookings
	@GetMapping( value = "/getallconfirmedbookings")
	public List<Booking_details> getallconfirmedbookings(){
		List<Booking_details> list = service.getallconfirmedbookings();
		return list;
	}
	
	@GetMapping( value = "/getallcancelledbookings")
	public List<Booking_details> getallcancelledbookings(){
		List<Booking_details> list = service.getallcancelledbookings();
		return list;
	}
	
	@GetMapping( value = "/getallcompletedbookings")
	public List<Booking_details> getallcompletedbookings(){
		List<Booking_details> list = service.getallcompletedbookings();
		return list;
	}
	
	@GetMapping( value = "/getallbookingsmerchantandlocation")
	public List<Booking_details> getallbookingsmerchantandlocation(@RequestParam("location") String location, @RequestParam("merchant_name") String merchant_name){
		List<Booking_details> list = service.getallbookingsmerchantandlocation(location,merchant_name);
		return list;
	}
	
	@GetMapping( value = "/generatepdfallbookingsmerchantandlocation")
	public void generatepdfallbookingsmerchantandlocation(@RequestParam("location") String location, @RequestParam("merchant_name") String merchant_name){
		List<Booking_details> list = service.getallbookingsmerchantandlocation(location,merchant_name);
		service.generatepdfallbookingsmerchantandlocation(location,merchant_name,list);
		System.out.println("PDF downloaded Succesfully..!!");
	}
	
	@GetMapping( value = "/getallbookingsmerchantandlocationbetweendates")
	public List<Booking_details> getallbookingsmerchantandlocationbetweendates(@RequestParam("location") String location, 
			@RequestParam("merchant_name") String merchant_name,
			@RequestParam("fromdate") String fromdate,@RequestParam("todate") String todate){
		Date date1= null,date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		List<Booking_details> list = service.getallbookingsmerchantandlocationbetweendates(location,merchant_name,date1,date2);
		return list;
	}
	
	@GetMapping( value = "/generatepdfbookingsmerchantandlocationbetweendates")
	public void generatepdfbookingsmerchantandlocationbetweendates(@RequestParam("location") String location, 
			@RequestParam("merchant_name") String merchant_name,
			@RequestParam("fromdate") String fromdate,@RequestParam("todate") String todate){
		Date date1= null,date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		List<Booking_details> list = service.getallbookingsmerchantandlocationbetweendates(location,merchant_name,date1,date2);
		service.generatepdfbookingsmerchantandlocationbetweendates(location,merchant_name,date2,date1,list);
		
		System.out.println("PDF downloaded Succesfully..!!");
	}
	
}





