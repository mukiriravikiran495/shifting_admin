package com.shifting_admin.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shifting_admin.model.Booking_details;
import com.shifting_admin.model.Merchant_booking;
import com.shifting_admin.model.Status;

@Repository("merchant_order_dao")
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Booking_details_daoImpl implements Booking_details_dao{

	@Autowired
	SessionFactory factory;
	
	
	@Override
	public List<Merchant_booking> getallbookings() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Merchant_booking order by pickup_date desc");
		List<Merchant_booking> list = query.list();
		return list;
	}

	
	
	@Override
	public List<Booking_details> getbookingsbycity(String city) {
		Session session = factory.getCurrentSession();
		
		Query query = session.createQuery(" from Booking_details where from_location = :from_location order by pickup_date desc");
		query.setParameter("from_location", city);
		System.out.println("Hello...");
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public List<Booking_details> getbookingsbasedontwodates(Date fromdate, Date todate) {
		Session session = factory.getCurrentSession();
		System.out.println(fromdate.equals(todate));
		if(fromdate.equals(todate)) {
			Query query = session.createQuery(" from Booking_details where pickup_date =:fromdate order by pickup_date desc");
			query.setParameter("fromdate", fromdate);
			System.out.println("fromdate"+fromdate);
			List<Booking_details> list = query.list();
			return list;
		}
		else {
			Query query = session.createQuery(" from Booking_details where pickup_date between :fromdate and :todate order by pickup_date desc");
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			System.out.println("dao "+fromdate);
			System.out.println("dao "+todate);
			List<Booking_details> list = query.list();
			return list;
		}
		
		
	}

	@Override
	public List<Booking_details> getallbookingsbasedonbetweendatesandlocation(Date fromdate, Date todate, String location) {
		Session session = factory.getCurrentSession();
		if(fromdate.equals(todate)) {
			Query query = session.createQuery(" from Booking_details  where from_location = :from_location and pickup_date = :fromdate order by pickup_date desc");
			query.setParameter("fromdate", fromdate);
			System.out.println("fromdate"+fromdate);
			query.setParameter("from_location", location);
			List<Booking_details> list = query.list();
			return list;
		}
		else {
			Query query = session.createQuery(" from Booking_details  where from_location = :from_location and pickup_date between :fromdate and :todate order by pickup_date desc");
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("from_location", location);
			System.out.println("fromdate : "+fromdate);
			System.out.println("tdate : "+todate);
			System.out.println("location : "+ location);
			List<Booking_details> list = query.list();
			return list;
		}
		
	}
	
	


	@Override
	public List<Booking_details> getallbookingsbasedondateandcity(String city, Date date) {
		Session session = factory.getCurrentSession();
		System.out.println(" Entered ..");
		
		Query query = session.createQuery(" from Booking_details where pickup_date = :pickup_date and from_location = : from_location order by pickup_date desc");
		query.setParameter("pickup_date", date);
		query.setParameter("from_location", city);
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public List<Booking_details> getbookingsbybookingid() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Booking_details order by pickup_date desc");
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public List<Booking_details> getallconfirmedbookings() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Booking_details where status = :booking_status order by pickup_date desc");
		query.setParameter("booking_status", Status.Confirm);
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public List<Booking_details> getallcancelledbookings() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Booking_details where status = :booking_status order by pickup_date desc");
		query.setParameter("booking_status", Status.Cancelled);
		List<Booking_details> list = query.list();
		return list;
	}

	@Override
	public List<Booking_details> getallcompletedbookings() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Booking_details where status = :booking_status order by pickup_date desc");
		query.setParameter("booking_status", Status.Completed);
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public void pdfbasedoncity(List<Booking_details> booking_list, String city) {
		System.out.println("City : "+city);
		Document doc = new Document(PageSize.A4.rotate());
		  
		PdfWriter docWriter = null;
		try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream("F:\\"+city+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		DecimalFormat df = new DecimalFormat("0.00");
		
		 
		try {
			//special font sizes
			Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bfBold14 = new Font(FontFamily.COURIER, 14, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bf10 = new Font(FontFamily.COURIER, 10); 
			 
			   
			    
			   //document header attributes
			   doc.addAuthor("betterThanZero");
			   doc.addCreationDate();
			   doc.addProducer();
			   doc.addCreator("Shiftyng.in");
			   
			   //open document
			   doc.open();
		 
		   
			   //open document
			   
			   Date date = new Date();
			   SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
			     	   
			   Image image1 = Image.getInstance("C:\\Users\\Ravikiran Mukiri\\Desktop\\logo.png");
			    
			  image1.scaleAbsolute(80,40);
			  
			//specify column widths
			   float[] columnWidths = {1.1f, 1.3f,1.3f,1.4f,1.2f,1.1f,1.2f,1f,0.8f};
			   //create PDF table with the given widths
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(103); //Width 100%
		        table.setSpacingBefore(20f); //Space before table
		        table.setSpacingAfter(15f); //Space after table
		        Iterator itr = booking_list.iterator();
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        insertCell(table, image1, Element.ALIGN_LEFT, 5, bfBold14);
		        insertCell(table, "Date : "+formatter.format(date), Element.ALIGN_RIGHT, 9, bf10);
		        insertCell(table, "InVoice Report", Element.ALIGN_CENTER, 9, bfBold14);
		        
		        insertCell(table, city+" Bookings", Element.ALIGN_CENTER, 9, bf10);
		        insertCell(table, "Booking Date : ALL", Element.ALIGN_LEFT, 3, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "Location : "+city, Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Company : All", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        
		        insertCell(table, "___________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			      
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//insert column headings
				
				
				insertCell(table, "Booking Id", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Vendor", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "From Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "To Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Shift type", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Pickup date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Drop date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Amount", Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
				table.setPaddingTop(10);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//create section heading by cell merging
				long grandtotal = 0,total = 0;
				//just some random data to fill 
				while(itr.hasNext()) {
					Booking_details booking = (Booking_details) itr.next();
					System.out.println(booking.getBooking_id());
					 insertCell(table, booking.getBooking_id(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getUser_profile().getName(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getMerchant_details().getMerchant_name(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getFrom_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getTo_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getShift_type(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, formatter.format(booking.getPickup_date()), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "22 May 2021", Element.ALIGN_CENTER, 1, bf10);
					 
					 total =    booking.getFinal_price_details().getFinal_amount();
					 insertCell(table, total, Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "", Element.ALIGN_LEFT, 9, bf10);
					 grandtotal = grandtotal+total;
					    
				}
				
				insertCell(table, "____________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			     
				//merge the cells to create a footer for that section
				insertCell(table, " Total ", Element.ALIGN_RIGHT, 7, bf10);
				insertCell(table, df.format(grandtotal), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, " Shiftyng -2% ", Element.ALIGN_RIGHT, 7, bf10);
				long percentage = (grandtotal/100)*2;
				insertCell(table, df.format(percentage), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, "Grand Total ", Element.ALIGN_RIGHT, 7, bf10);
				long finalprice = grandtotal - percentage;
				insertCell(table, df.format(finalprice), Element.ALIGN_RIGHT, 2, bf10);
				
				//repeat the same as above to display another location
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, "***    Total Income of  : "+city+" is Rs :"+finalprice+"    ***" , Element.ALIGN_CENTER, 9, bfBold12);
			    
				//doc.add(image1);
				  
				doc.add(table);
		}
		catch (DocumentException dex)
		  {
		   dex.printStackTrace();
		  }
		  catch (Exception ex)
		  {
		   ex.printStackTrace();
		  }
		  finally
		  {
		   if (doc != null){
		    //close the document
		    doc.close();
		   }
		   if (docWriter != null){
		    //close the writer
		    docWriter.close();
		   }
		  }
	}

	private void insertCell(PdfPTable table, Image image1, int align, int colspan, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase());
		  //set the cell alignment
		  
		  cell.setBorder(Rectangle.NO_BORDER);
		  cell.setPaddingLeft(5);
	      cell.setHorizontalAlignment(align);
	      cell.setColspan(3);
	       cell.addElement(image1); 
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  //add the call to the table
		  table.addCell(cell);
	}



	private void insertCell(PdfPTable table, long booking_id, int align, int colspan, Font font) {
		String str  = booking_id+"";
		PdfPCell cell = new PdfPCell(new Phrase(str,font));
		  //set the cell alignment
		  
		  cell.setBorder(Rectangle.NO_BORDER);
		  cell.setPaddingLeft(5);
	      cell.setHorizontalAlignment(align);
	      cell.setColspan(3);
	        
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(str.equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
	}

	private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		   
		  //create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		  //set the cell alignment
		  
		  cell.setBorder(Rectangle.NO_BORDER);
		  cell.setPaddingLeft(5);
	      cell.setHorizontalAlignment(align);
	      cell.setColspan(3);
	        
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
		   
		 }



	@Override
	public List<Booking_details> getallbookingsongivendate(Date date) {
		
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Booking_details where pickup_date = :todaydate ");
		query.setParameter("todaydate",date);
		List<Booking_details> list = query.list();
		return list;
	}

	@Override
	public void generatepdfbookingsbasedondateandcity(List<Booking_details> booking_list, String city, Date date1) {
		System.out.println("City : "+city);
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		   
		Document doc = new Document(PageSize.A4.rotate());
		Date date = new Date();
		PdfWriter docWriter = null;
		try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream("F:\\"+city+"-"+formatter.format(date1)+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		DecimalFormat df = new DecimalFormat("0.00");
		
		 
		try {
			//special font sizes
			Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bfBold14 = new Font(FontFamily.COURIER, 14, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bf10 = new Font(FontFamily.COURIER, 10); 
			 
			   
			    
			   //document header attributes
			   doc.addAuthor("betterThanZero");
			   doc.addCreationDate();
			   doc.addProducer();
			   doc.addCreator("Shiftyng.in");
			   
			   //open document
			   doc.open();
		 
		   
			   //open document
			   
			  
			     	   
			   Image image1 = Image.getInstance("C:\\Users\\Ravikiran Mukiri\\Desktop\\logo.png");
			    
			  image1.scaleAbsolute(80,40);
			  
			//specify column widths
			   float[] columnWidths = {1.1f, 1.3f,1.3f,1.4f,1.2f,1.1f,1.2f,1f,0.8f};
			   //create PDF table with the given widths
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(103); //Width 100%
		        table.setSpacingBefore(20f); //Space before table
		        table.setSpacingAfter(15f); //Space after table
		        Iterator itr = booking_list.iterator();
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        insertCell(table, image1, Element.ALIGN_LEFT, 5, bfBold14);
		        insertCell(table, "Date : "+formatter.format(date), Element.ALIGN_RIGHT, 9, bf10);
		        insertCell(table, "InVoice", Element.ALIGN_CENTER, 9, bfBold14);
		        
		        insertCell(table, city+" Bookings", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, "Booking Date : "+formatter.format(date1), Element.ALIGN_LEFT, 3, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Location : "+city, Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Company : All", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        
		        insertCell(table, "___________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			      
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//insert column headings
				
				
				insertCell(table, "Booking Id", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "Vendor", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "From Location", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "To Location", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "Shift type", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "Pickup date", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "Drop date", Element.ALIGN_CENTER, 1, bf10);
				insertCell(table, "Amount", Element.ALIGN_CENTER, 1, bf10);
				table.setHeaderRows(1);
				table.setPaddingTop(10);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bf10);
				//create section heading by cell merging
				long grandtotal = 0,total = 0;
				//just some random data to fill 
				while(itr.hasNext()) {
					Booking_details booking = (Booking_details) itr.next();
					System.out.println(booking.getBooking_id());
					 insertCell(table, booking.getBooking_id(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getUser_profile().getName(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getMerchant_details().getMerchant_name(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getFrom_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getTo_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getShift_type(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, formatter.format(booking.getPickup_date()), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "22 May 2021", Element.ALIGN_CENTER, 1, bf10);
					 
					 total =    booking.getFinal_price_details().getFinal_amount();
					 insertCell(table, total, Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
					 grandtotal = grandtotal+total;
					    
				}
				
				insertCell(table, "____________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			     
				//merge the cells to create a footer for that section
				insertCell(table, " Total ", Element.ALIGN_RIGHT, 7, bf10);
				insertCell(table, df.format(grandtotal), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, " Shiftyng -2% ", Element.ALIGN_RIGHT, 7, bf10);
				long percentage = (grandtotal/100)*2;
				insertCell(table, df.format(percentage), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, "Grand Total ", Element.ALIGN_RIGHT, 7, bf10);
				long finalprice = grandtotal - percentage;
				insertCell(table, df.format(finalprice), Element.ALIGN_RIGHT, 2, bf10);
				
				//repeat the same as above to display another location
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, "***    Total Income of date : "+formatter.format(date1)+" is Rs :"+finalprice+"    ***" , Element.ALIGN_CENTER, 9, bfBold12);
			    
				//doc.add(image1);
				  
				doc.add(table);
		}
		catch (DocumentException dex){
			dex.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (doc != null){
			    //close the document
			    doc.close();
			}
			if (docWriter != null){
			    //close the writer
			    docWriter.close();
			}
		}
		   
	}


	@Override
	public void generatepdfbookingsongivendate(List<Booking_details> list, String date) {
		
	}



	@Override
	public void generatepdfbookingsbasedontwodates(List<Booking_details> booking_list, Date fromdate, Date todate) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		   
		Document doc = new Document(PageSize.A4.rotate());
		Date date = new Date();
		PdfWriter docWriter = null;
		try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream("F:\\Bookings - "+formatter.format(fromdate)+" - "+formatter.format(todate)+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		DecimalFormat df = new DecimalFormat("0.00");
		
		 
		try {
			//special font sizes
			Font bfBold12 = new Font(FontFamily.COURIER, 10, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bfBold14 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			   
			   Font bf10 = new Font(FontFamily.COURIER, 8); 
			 
			   
			    
			   //document header attributes
			   doc.addAuthor("betterThanZero");
			   doc.addCreationDate();
			   doc.addProducer();
			   doc.addCreator("Shiftyng.in");
			   
			   //open document
			   doc.open();
		 
		   
			   //open document
			   
			  
			     	   
			   Image image1 = Image.getInstance("C:\\Users\\Ravikiran Mukiri\\Desktop\\logo.png");
			    
			  image1.scaleAbsolute(80,40);
			  
			//specify column widths
			   float[] columnWidths = {1.1f, 1.3f,1.3f,1.4f,1.2f,1.1f,1.2f,1f,0.8f};
			   //create PDF table with the given widths
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(103); //Width 100%
		        table.setSpacingBefore(20f); //Space before table
		        table.setSpacingAfter(15f); //Space after table
		        Iterator itr = booking_list.iterator();
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        String date2 = null;
		        insertCell(table, image1, Element.ALIGN_LEFT, 5, bfBold14);
		        insertCell(table, "Date : "+formatter.format(date), Element.ALIGN_RIGHT, 9, bf10);
		        if(fromdate.equals(todate)) {
		        	date2 = formatter.format(fromdate);
		        }else {
		        	date2 = formatter.format(fromdate)+" - "+formatter.format(todate);
		        }
		        insertCell(table, "InVoice", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, "Completed Bookings", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "Booking Date : "+date2, Element.ALIGN_LEFT, 9, bf10);
		        
		        insertCell(table, "Location : ALL", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Company : All", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        
		        
		        insertCell(table, "___________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			      
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//insert column headings
				
				
				insertCell(table, "Booking Id", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Vendor", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "From Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "To Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Shift type", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Pickup date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Drop date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Amount", Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
				table.setPaddingTop(10);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//create section heading by cell merging
				long grandtotal = 0,total = 0;
				//just some random data to fill 
				while(itr.hasNext()) {
					Booking_details booking = (Booking_details) itr.next();
					System.out.println(booking.getBooking_id());
					 insertCell(table, booking.getBooking_id(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getUser_profile().getName(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getMerchant_details().getMerchant_name(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getFrom_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getTo_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getShift_type(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, formatter.format(booking.getPickup_date()), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "22 May 2021", Element.ALIGN_CENTER, 1, bf10);
					 
					 total =    booking.getFinal_price_details().getFinal_amount();
					 insertCell(table, total, Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
					 grandtotal = grandtotal+total;
					    
				}
				
				insertCell(table, "____________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			     
				//merge the cells to create a footer for that section
				insertCell(table, " Total ", Element.ALIGN_RIGHT, 7, bf10);
				insertCell(table, df.format(grandtotal), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, " Shiftyng -2% ", Element.ALIGN_RIGHT, 7, bf10);
				long percentage = (grandtotal/100)*2;
				insertCell(table, df.format(percentage), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, "Grand Total ", Element.ALIGN_RIGHT, 7, bf10);
				long finalprice = grandtotal - percentage;
				insertCell(table, df.format(finalprice), Element.ALIGN_RIGHT, 2, bf10);
				
				//repeat the same as above to display another location
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, "***    Total Income of dates between  "+date2+" is Rs :"+finalprice+"    ***" , Element.ALIGN_CENTER, 9, bfBold12);
			    
				//doc.add(image1);
				  
				doc.add(table);
		}
		catch (DocumentException dex){
			dex.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (doc != null){
			    //close the document
			    doc.close();
			}
			if (docWriter != null){
			    //close the writer
			    docWriter.close();
			}
		}
		   
	}



	@Override
	public void generatebookingsbasedonbetweendatesandlocation(List<Booking_details> booking_list, String location, Date fromdate,
			Date todate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		   
		Document doc = new Document(PageSize.A4.rotate());
		Date date = new Date();
		PdfWriter docWriter = null;
		try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream("F:\\"+location+" - "+formatter.format(fromdate)+" - "+formatter.format(todate)+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		DecimalFormat df = new DecimalFormat("0.00");
		
		 
		try {
			//special font sizes
			Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bfBold14 = new Font(FontFamily.COURIER, 14, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bf10 = new Font(FontFamily.COURIER, 10); 
			 
			   
			    
			   //document header attributes
			   doc.addAuthor("betterThanZero");
			   doc.addCreationDate();
			   doc.addProducer();
			   doc.addCreator("Shiftyng.in");
			   
			   //open document
			   doc.open();
		 
		   
			   //open document
			   
			  
			     	   
			   Image image1 = Image.getInstance("C:\\Users\\Ravikiran Mukiri\\Desktop\\logo.png");
			    
			  image1.scaleAbsolute(80,40);
			  
			//specify column widths
			   float[] columnWidths = {1.1f, 1.3f,1.3f,1.4f,1.2f,1.1f,1.2f,1f,0.8f};
			   //create PDF table with the given widths
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(103); //Width 100%
		        table.setSpacingBefore(20f); //Space before table
		        table.setSpacingAfter(15f); //Space after table
		        Iterator itr = booking_list.iterator();
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        String date2 = null;
		        insertCell(table, image1, Element.ALIGN_LEFT, 5, bfBold14);
		        insertCell(table, "Date : "+formatter.format(date), Element.ALIGN_RIGHT, 9, bf10);
		        
		        if(fromdate.equals(todate)) {
		        	date2 = formatter.format(fromdate);
		        }
		        else {
		        	date2  = formatter.format(fromdate)+" - "+formatter.format(todate);
		        }
		        insertCell(table, "InVoice", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, location+" Completed Bookings", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "Booking Date : "+date2, Element.ALIGN_LEFT, 9, bf10);
		        
		        insertCell(table, "Location : "+location, Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Company : All", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        
		        insertCell(table, "___________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			      
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//insert column headings
				
				
				insertCell(table, "Booking Id", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Vendor", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "From Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "To Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Shift type", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Pickup date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Drop date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Amount", Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
				table.setPaddingTop(10);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//create section heading by cell merging
				long grandtotal = 0,total = 0;
				//just some random data to fill 
				while(itr.hasNext()) {
					Booking_details booking = (Booking_details) itr.next();
					System.out.println(booking.getBooking_id());
					 insertCell(table, booking.getBooking_id(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getUser_profile().getName(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getMerchant_details().getMerchant_name(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getFrom_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getTo_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getShift_type(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, formatter.format(booking.getPickup_date()), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "22 May 2021", Element.ALIGN_CENTER, 1, bf10);
					 
					 total =    booking.getFinal_price_details().getFinal_amount();
					 insertCell(table, total, Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
					 grandtotal = grandtotal+total;
					    
				}
				
				insertCell(table, "____________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			     
				//merge the cells to create a footer for that section
				insertCell(table, "  ", Element.ALIGN_RIGHT, 9, bf10);
				insertCell(table, " Total ", Element.ALIGN_RIGHT, 7, bf10);
				insertCell(table, df.format(grandtotal), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, " Shiftyng -2% ", Element.ALIGN_RIGHT, 7, bf10);
				long percentage = (grandtotal/100)*2;
				insertCell(table, df.format(percentage), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, "Grand Total ", Element.ALIGN_RIGHT, 7, bf10);
				long finalprice = grandtotal - percentage;
				insertCell(table, df.format(finalprice), Element.ALIGN_RIGHT, 2, bf10);
				
				//repeat the same as above to display another location
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, "***    Total Income of "+location+" between  "+date2+" is Rs :"+finalprice+"    ***" , Element.ALIGN_CENTER, 9, bfBold12);
			    
				//doc.add(image1);
				  
				doc.add(table);
		}
		catch (DocumentException dex){
			dex.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (doc != null){
			    //close the document
			    doc.close();
			}
			if (docWriter != null){
			    //close the writer
			    docWriter.close();
			}
		}
		   
	}



	@Override
	public List<Booking_details> getallbookingsmerchantandlocation(String location, String name) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(" from Booking_details b  join fetch b.merchant_details m where  b.from_location = :from_location and m.merchant_name = : merchant_name order by pickup_date desc ");
		query.setParameter("from_location", location);
		query.setParameter("merchant_name", name);
		System.out.println("Query Executed...");
		
		
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public List<Booking_details> getallbookingsmerchantandlocationbetweendates(String location, String name,
			Date date1, Date date2) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Booking_details b  join fetch b.merchant_details m where b.from_location = :from_location "
				+ "and m.merchant_name = :merchant_name and b.pickup_date between :fromdate and :todate order by pickup_date desc" );
		query.setParameter("from_location", location);
		query.setParameter("merchant_name", name);
		query.setParameter("fromdate", date1);
		query.setParameter("todate", date2);
		List<Booking_details> list = query.list();
		return list;
	}



	@Override
	public void generatepdfallbookingsmerchantandlocation(String location, String merchant_name,
			List<Booking_details> booking_list) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		   
		Document doc = new Document(PageSize.A4.rotate());
		Date date = new Date();
		PdfWriter docWriter = null;
		try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream("F:\\"+location+" - "+merchant_name+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		DecimalFormat df = new DecimalFormat("0.00");
		
		 
		try {
			//special font sizes
			Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bfBold14 = new Font(FontFamily.COURIER, 14, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bf10 = new Font(FontFamily.COURIER, 10); 
			 
			   
			    
			   //document header attributes
			   doc.addAuthor("betterThanZero");
			   doc.addCreationDate();
			   doc.addProducer();
			   doc.addCreator("Shiftyng.in");
			   
			   //open document
			   doc.open();
		 
		   
			   //open document
			   
			  
			     	   
			   Image image1 = Image.getInstance("C:\\Users\\Ravikiran Mukiri\\Desktop\\logo.png");
			    
			  image1.scaleAbsolute(80,40);
			  
			//specify column widths
			   float[] columnWidths = {1.1f, 1.3f,1.3f,1.4f,1.2f,1.1f,1.2f,1f,0.8f};
			   //create PDF table with the given widths
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(103); //Width 100%
		        table.setSpacingBefore(20f); //Space before table
		        table.setSpacingAfter(15f); //Space after table
		        Iterator itr = booking_list.iterator();
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        insertCell(table, image1, Element.ALIGN_LEFT, 5, bfBold14);
		        insertCell(table, "Date : "+formatter.format(date), Element.ALIGN_RIGHT, 9, bf10);
		        
		        
		        insertCell(table, "InVoice", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, " Completed Bookings", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "Booking Date : ALL", Element.ALIGN_LEFT, 9, bf10);
		        
		        insertCell(table, "Location : "+location, Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Company : "+merchant_name, Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        
		        insertCell(table, "___________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			      
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//insert column headings
				
				
				insertCell(table, "Booking Id", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Vendor", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "From Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "To Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Shift type", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Pickup date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Drop date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Amount", Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
				table.setPaddingTop(10);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//create section heading by cell merging
				long grandtotal = 0,total = 0;
				//just some random data to fill 
				while(itr.hasNext()) {
					Booking_details booking = (Booking_details) itr.next();
					System.out.println(booking.getBooking_id());
					 insertCell(table, booking.getBooking_id(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getUser_profile().getName(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getMerchant_details().getMerchant_name(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getFrom_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getTo_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getShift_type(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, formatter.format(booking.getPickup_date()), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "22 May 2021", Element.ALIGN_CENTER, 1, bf10);
					 
					 total =    booking.getFinal_price_details().getFinal_amount();
					 insertCell(table, total, Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
					 grandtotal = grandtotal+total;
					    
				}
				
				insertCell(table, "____________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			     
				//merge the cells to create a footer for that section
				insertCell(table, "  ", Element.ALIGN_RIGHT, 9, bf10);
				insertCell(table, " Total ", Element.ALIGN_RIGHT, 7, bf10);
				insertCell(table, df.format(grandtotal), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, " Shiftyng -2% ", Element.ALIGN_RIGHT, 7, bf10);
				long percentage = (grandtotal/100)*2;
				insertCell(table, df.format(percentage), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, "Grand Total ", Element.ALIGN_RIGHT, 7, bf10);
				long finalprice = grandtotal - percentage;
				insertCell(table, df.format(finalprice), Element.ALIGN_RIGHT, 2, bf10);
				
				//repeat the same as above to display another location
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, "***    Total Income of "+merchant_name+" of  "+location+" is Rs :"+finalprice+"    ***" , Element.ALIGN_CENTER, 9, bfBold12);
			    
				//doc.add(image1);
				  
				doc.add(table);
		}
		catch (DocumentException dex){
			dex.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (doc != null){
			    //close the document
			    doc.close();
			}
			if (docWriter != null){
			    //close the writer
			    docWriter.close();
			}
		}
		   
	}



	@Override
	public void generatepdfbookingsmerchantandlocationbetweendates(String location, String merchant_name,
			Date fromdate, Date todate, List<Booking_details> booking_list) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		   
		Document doc = new Document(PageSize.A4.rotate());
		Date date = new Date();
		PdfWriter docWriter = null;
		try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream("F:\\"+location+" - "+merchant_name+" - "+formatter.format(fromdate)+" - "+formatter.format(todate)+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		DecimalFormat df = new DecimalFormat("0.00");
		
		 
		try {
			//special font sizes
			Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bfBold14 = new Font(FontFamily.COURIER, 14, Font.BOLD, new BaseColor(0, 0, 0)); 
			   Font bf10 = new Font(FontFamily.COURIER, 10); 
			 
			   
			    
			   //document header attributes
			   doc.addAuthor("betterThanZero");
			   doc.addCreationDate();
			   doc.addProducer();
			   doc.addCreator("Shiftyng.in");
			   
			   //open document
			   doc.open();
		 
		   
			   //open document
			   
			  
			     	   
			   Image image1 = Image.getInstance("C:\\Users\\Ravikiran Mukiri\\Desktop\\logo.png");
			    
			  image1.scaleAbsolute(80,40);
			  
			//specify column widths
			   float[] columnWidths = {1.1f, 1.3f,1.3f,1.4f,1.2f,1.1f,1.2f,1f,0.8f};
			   //create PDF table with the given widths
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(103); //Width 100%
		        table.setSpacingBefore(20f); //Space before table
		        table.setSpacingAfter(15f); //Space after table
		        Iterator itr = booking_list.iterator();
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        String date2 = null;
		        insertCell(table, image1, Element.ALIGN_LEFT, 5, bfBold14);
		        insertCell(table, "Date : "+formatter.format(date), Element.ALIGN_RIGHT, 9, bf10);
		        
		        if(fromdate.equals(todate)) {
		        	date2 = formatter.format(fromdate);
		        }
		        else {
		        	date2  = formatter.format(fromdate)+" - "+formatter.format(todate);
		        }
		        insertCell(table, "InVoice", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, location+" Completed Bookings", Element.ALIGN_CENTER, 9, bfBold14);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "Booking Date : "+date2, Element.ALIGN_LEFT, 9, bf10);
		        
		        insertCell(table, "Location : "+location, Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "Company : All", Element.ALIGN_LEFT, 9, bf10);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
		        
		        
		        insertCell(table, "___________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			      
		        insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//insert column headings
				
				
				insertCell(table, "Booking Id", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Customer", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Vendor", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "From Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "To Location", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Shift type", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Pickup date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Drop date", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Amount", Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
				table.setPaddingTop(10);
				insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
				//create section heading by cell merging
				long grandtotal = 0,total = 0;
				//just some random data to fill 
				while(itr.hasNext()) {
					Booking_details booking = (Booking_details) itr.next();
					System.out.println(booking.getBooking_id());
					 insertCell(table, booking.getBooking_id(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getUser_profile().getName(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getMerchant_details().getMerchant_name(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getFrom_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getTo_location(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, booking.getShift_type(), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, formatter.format(booking.getPickup_date()), Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "22 May 2021", Element.ALIGN_CENTER, 1, bf10);
					 
					 total =    booking.getFinal_price_details().getFinal_amount();
					 insertCell(table, total, Element.ALIGN_CENTER, 1, bf10);
					 insertCell(table, "", Element.ALIGN_LEFT, 9, bfBold12);
					 grandtotal = grandtotal+total;
					    
				}
				
				insertCell(table, "____________________________________________________________________________________________________________", Element.ALIGN_LEFT, 9, bfBold12);
			     
				//merge the cells to create a footer for that section
				insertCell(table, "  ", Element.ALIGN_RIGHT, 9, bf10);
				insertCell(table, " Total ", Element.ALIGN_RIGHT, 7, bf10);
				insertCell(table, df.format(grandtotal), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, " Shiftyng -2% ", Element.ALIGN_RIGHT, 7, bf10);
				long percentage = (grandtotal/100)*2;
				insertCell(table, df.format(percentage), Element.ALIGN_RIGHT, 2, bf10);
				insertCell(table, "Grand Total ", Element.ALIGN_RIGHT, 7, bf10);
				long finalprice = grandtotal - percentage;
				insertCell(table, df.format(finalprice), Element.ALIGN_RIGHT, 2, bf10);
				
				//repeat the same as above to display another location
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, " ", Element.ALIGN_CENTER, 9, bfBold12);
				insertCell(table, "***    Total Income of "+location+" between  "+date2+" is Rs :"+finalprice+"    ***" , Element.ALIGN_CENTER, 9, bfBold12);
			    
				//doc.add(image1);
				  
				doc.add(table);
		}
		catch (DocumentException dex){
			dex.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			if (doc != null){
			    //close the document
			    doc.close();
			}
			if (docWriter != null){
			    //close the writer
			    docWriter.close();
			}
		}
	}



	

	

	

}
