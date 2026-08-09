package genericUtility_Methods;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;

import java.text.SimpleDateFormat;

public class JavaUtility {
	
	//getting Randomnumber
	public int togetRandomCount()
	{
		Random ran=new Random();
		int randomcount=ran.nextInt(500);
		return randomcount;
	}
	
	public String togetRandomAlpha()
	{
		Random rand=new Random();
		char upper=(char)('A'+rand.nextInt(26)); //upper is a character
		String RandomAlphabet=""+upper; //the return type of method is String to convert it to String we are adding this
		/*rand.nextInt(26)

		Generates a random integer between 0 and 25 (inclusive).

		Example outputs: 0, 5, 12, 25.

		'A' + rand.nextInt(26)

		'A' is the ASCII value for uppercase A (65).

		Adding a number between 0 and 25 shifts it to another uppercase letter.

		If random = 0 → 'A' + 0 → 'A'

		If random = 1 → 'A' + 1 → 'B'

		…

		If random = 25 → 'A' + 25 → 'Z'*/
		return RandomAlphabet;
	}
	
	public String CurrentDate()
	{
		//Get todays date
		Date date=new Date();
		//Convert it into this format
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String currentDate=sim.format(date);
		return currentDate;
		
	}
	
	//Code to get Required Date or expected Date
	public String togetRequiredDate(int days)
	{
		//Get todays date
		Date d=new Date();
		//Convert it into this format
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		//Todays or current Date with the dd-MM-yyyy format
	    sim.format(d);
		
		//To get 15 days or to get required date
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);

/*void java.util.Calendar.add(int field, int amount)


Adds or subtracts the specified amount of time to the given calendar field,based on the calendar's rules. 
For example, to subtract 5 days from the current time of the calendar, you can achieve it by calling: 

add(Calendar.DAY_OF_MONTH, -5).
Parameters:field the calendar field.amount the amount of date or time to be added to the field.
Throws:IllegalArgumentException - if this Calendar is non-lenientand any of the calendar fields have invalid values or if field is ZONE_OFFSET, DST_OFFSET, or unknown.See Also:roll(int, int)set(int, int)*/
		
		
		
		String expecteddate = sim.format(cal.getTime());
		/*Returns a Date object representing this Calendar's time value (millisecond offset from the Epoch").
Returns:a Date representing the time value.See Also:setTime(Date)getTimeInMillis()*/
		
		return expecteddate;
		}
}
