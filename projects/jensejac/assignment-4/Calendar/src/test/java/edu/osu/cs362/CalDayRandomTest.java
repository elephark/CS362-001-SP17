package edu.osu.cs362;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */

	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	@Test
	public void randomtest()  throws Throwable  {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		
		
		System.out.println("Start testing...");
		
		for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			long randomseed =System.currentTimeMillis();
			Random random = new Random(randomseed);
		
			// create new CalDay object
			// note: this is copied directly from one of my tests in assn2
			// also note: since we only need to achieve branch coverage of addAppt(),
			// I'm not going to worry about the fact that this isn't terribly thorough
			// regarding testing the rest of the code.
			
			//get todays date
			Calendar rightnow = Calendar.getInstance();
			//current month/year/date is today
			int thisMonth = rightnow.get(Calendar.MONTH)+1;
			int thisYear = rightnow.get(Calendar.YEAR);
			int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
			// 
			GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
			CalDay calday = new CalDay(today);
			// make sure it acts like it's valid
			assertTrue(calday.isValid());
			assertEquals(thisDay, calday.getDay());
			assertEquals(thisMonth, calday.getMonth());
			assertEquals(thisYear, calday.getYear());
			assertEquals(0, calday.getSizeAppts());
			
			// add some appointments at random times, probably valid but maybe not
			int apptsToAdd = ValuesGenerator.getRandomIntBetween(random, 2, 20);
			for (int i=0; i<apptsToAdd; i++) {
				int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 26);
				int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 62);
				int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 33);
				int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 14);
				int startYear=thisYear; // let's not get too frisky here
				String title=(String) ValuesGenerator.getString(random);
				String description=(String) ValuesGenerator.getString(random);
				//Construct a new Appointment object with the initial data	 
				Appt appt = new Appt(startHour,
				         startMinute,
				         startDay,
				         startMonth,
				         startYear,
				         title,
				         description);
				// add it to the CalDay object
				calday.addAppt(appt);
			}
			
			
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%60000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		
		}
		System.out.println("Done testing...");
	}


}
