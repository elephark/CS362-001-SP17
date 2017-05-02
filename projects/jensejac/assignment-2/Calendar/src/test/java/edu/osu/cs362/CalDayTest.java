package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	 // empty, invalid object
	  public void test01()  throws Throwable  {
		// default constructor; should create an invalid object
		CalDay calday = new CalDay();
		// make sure it acts like it's not valid
		assertFalse(calday.isValid());
		assertEquals(null, calday.iterator());
		assertEquals("", calday.toString());
	 }
	 
	 
 	 @Test
 	 // test out accessors and the regular constructor, then add a single appointment
	  public void test02()  throws Throwable  {
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
		
		// let's add an appointment and see what happens
		Appt appt = new Appt(13, 30, thisDay, thisMonth, thisYear, "Birthday Party",
			"This is my birthday party.");
		
		calday.addAppt(appt);
		
		assertEquals(1, calday.getSizeAppts());
	 }
	 
	 
  	 @Test
  	 // add more than one appointment
	  public void test03()  throws Throwable  {
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
		
		// let's add an appointment and see what happens
		Appt appt = new Appt(13, 30, thisDay, thisMonth, thisYear, "Birthday Party",
			"This is my birthday party.");
		
		calday.addAppt(appt);
		assertEquals(1, calday.getSizeAppts());
		
		// let's add an identical appointment and see what happens
		Appt appt2 = new Appt(13, 30, thisDay, thisMonth, thisYear, "Birthday Party",
			"This is my birthday party.");
		
		calday.addAppt(appt2);
		assertEquals(2, calday.getSizeAppts());
		
		// okay, how about an earlier appointment
		Appt appt3 = new Appt(12, 30, thisDay, thisMonth, thisYear, "Fire Drill",
			"Everybody is going to panic.");
		
		calday.addAppt(appt3);
		assertEquals(3, calday.getSizeAppts());
		
		// AND ANOTHER, but later
		Appt appt4 = new Appt(14, 30, thisDay, thisMonth, thisYear, "Easter Egg Hunt",
			"Yep.");
		
		calday.addAppt(appt4);
		assertEquals(4, calday.getSizeAppts());
	 }
	 
	 
	   	 @Test
  	 // check output
	  public void test04()  throws Throwable  {
	 	//get todays date
		GregorianCalendar today = new GregorianCalendar(2017,5,1);
		CalDay calday = new CalDay(today);
		// make sure it acts like it's valid
		assertTrue(calday.isValid());
		assertEquals(1, calday.getDay());
		assertEquals(5, calday.getMonth());
		assertEquals(2017, calday.getYear());
		assertEquals(0, calday.getSizeAppts());
		
		// let's add an appointment and see what happens
		Appt appt = new Appt(13, 30, 1, 5, 2017, "Meeting Today",
			"Meeting with the students.");
		calday.addAppt(appt);
		assertEquals(1, calday.getSizeAppts());
		// let's add an identical appointment and see what happens
		Appt appt2 = new Appt(14, 30, 1, 5, 2017, "Class",
			"Rescheduled class.");
		calday.addAppt(appt2);
		assertEquals(2, calday.getSizeAppts());
		// okay, how about an earlier appointment
		Appt appt3 = new Appt(15, 30, 1, 5, 2017, "Birthday Party",
			"This is my birthday party.");
		calday.addAppt(appt3);
		assertEquals(3, calday.getSizeAppts());
		
		assertEquals("	 --- 5/1/2017 --- \n" +
		" --- -------- Appointments ------------ --- \n" +
		"	5/1/2017 at 1:30pm ,Meeting Today, Meeting with the students.\n" +
		" 	5/1/2017 at 2:30pm ,Class, Rescheduled class.\n" +
		" 	5/1/2017 at 3:30pm ,Birthday Party, This is my birthday party.\n \n",
				calday.toString());
	 }
	 	
}

