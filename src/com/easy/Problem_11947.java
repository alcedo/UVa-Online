package com.easy;

import static java.lang.System.out;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Must make use of gregorian calendar ! 
 * C++ folks would find this harder to accomplish. 
 * 
 * Mistakes: none. just be careful in copying date ranges over for the
 * zodiac signs.
 * 
 * Calendar Tutorial: 
 * http://www.ntu.edu.sg/home/ehchua/programming/java/DateTimeCalendar.html
 * Time taken: 1 hour 20 mins
 * @author Victor
 */
public class Problem_11947 {

    public static void main(String[] args) throws ParseException {
   	 
    	Scanner in = new Scanner(System.in);
        int datasets = in.nextInt();
        int GESTATION_WEEKS = 40;
        
        for(int i=0; i<datasets; i++){
       	 
	       	//starting of last menstrual date 
	       	String dateInput = in.next();
			DateFormat df = new SimpleDateFormat("MMddyyyy", Locale.ENGLISH);
			Date firstDate =  df.parse(dateInput);  
			    
			// Add in gestation period 
			Calendar cal = Calendar.getInstance();   // GregorianCalendar
			cal.setTime(firstDate);
			cal.add(Calendar.WEEK_OF_YEAR, GESTATION_WEEKS );
			
			// print out results
			df = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			String zodiacSign = getZodiac(cal);
			String zodiacDate = df.format(cal.getTime());
			
			out.println((i+1) + " " + zodiacDate + " " + zodiacSign);
			
//			out.println("Year  : " + cal.get(Calendar.YEAR));
//		    out.println("Month : " + cal.get(Calendar.MONTH));
//		    out.println("Day of Month : " + cal.get(Calendar.DAY_OF_MONTH));
//			out.println("date Input: " + dateInput + " " + firstDate);
        }
        
   }
   
   
  public static String getZodiac(Calendar date) {
	   	int month = date.get(Calendar.MONTH);
	   	int day = date.get(Calendar.DAY_OF_MONTH);
	   	
	   	//aquarius
	   	if( (month == 0 && day >= 21 )  || (month == 1 && day <= 19) ) {
	   		return "aquarius";
	   	}
	   	
	   	//pisces
	   	if( (month == 1 && day >= 20 )  || (month == 2 && day <= 20) ) {
	   		return "pisces";
	   	}
	   	
	   	//aries
	   	if( (month == 2 && day >= 21 )  || (month == 3 && day <= 20) ) {
	   		return "aries";
	   	}
	   	
	   	//taurus
	   	if( (month == 3 && day >= 21 )  || (month == 4 && day <= 21) ) {
	   		return "taurus";
	   	}
	   	//gemini
	   	if( (month == 4 && day >= 22 )  || (month == 5 && day <= 21) ) {
	   		return "gemini";
	   	}
	   	//cancer
	   	if( (month == 5 && day >= 22 )  || (month == 6 && day <= 22) ) {
	   		return "cancer";
	   	}
	   	//leo
	   	if( (month == 6 && day >= 23 )  || (month == 7 && day <= 21) ) {
	   		return "leo";
	   	}
	   	//virgo
	   	if( (month == 7 && day >= 22 )  || (month == 8 && day <= 23) ) {
	   		return "virgo";
	   	}
	   	//libra
	   	if( (month == 8 && day >= 24 )  || (month == 9 && day <= 23) ) {
	   		return "libra";
	   	}
	   	//scorpio
	   	if( (month == 9 && day >= 24 )  || (month == 10 && day <= 22) ) {
	   		return "scorpio";
	   	}
	   	//sagittarius
	   	if( (month == 10 && day >= 23 )  || (month == 11 && day <= 22) ) {
	   		return "sagittarius";
	   	}
	   	//capricorn
	   	if( (month == 11 && day >= 23 )  || (month == 0 && day <= 20) ) {
	   		return "capricorn";
	   	}

	   	return "NULL ZODIAC";
   }

}
