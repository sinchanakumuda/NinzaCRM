package advanceSelenium_Assertion_Implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


public class DateCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date d=new Date();
		System.out.println(d);
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String currentdate=sim.format(d);
		System.out.println(currentdate);//18-02-2026
		//after 15
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,15);
		String expectedate=sim.format(cal.getTime());
		System.out.println(expectedate);//18-02-2026
		
		
		
		
		

	}

}
