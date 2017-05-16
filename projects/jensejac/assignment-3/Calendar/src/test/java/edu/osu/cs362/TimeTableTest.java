package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	 // basic functionality
	  public void test01()  throws Throwable  {
		TimeTable timeTable = new TimeTable();
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
 		GregorianCalendar today = new GregorianCalendar(2017,05,01);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH,1);
		//Create a linked list of calendar days to return
		LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		calDays=timeTable.getApptRange(listAppts,today,tomorrow);
		
		// make sure the first day must be before the last day
		boolean thrown = false;
		try {
			calDays=timeTable.getApptRange(listAppts,tomorrow,today);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
	 }
	 
	 
		 @Test
		 // populate with a bunch of stuff adapted from the main program,
		 // then delete things and stuff
	  public void test02() throws Throwable {
			/** Collection of all of the appointments for the calendar day */		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		
    	
		//get todays date
    	Calendar rightnow = Calendar.getInstance();
    	//current month/year/date is today
    	int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = 15;//rightnow.get(Calendar.DAY_OF_MONTH);
		
		
		 int startHour=15;
		 int startMinute=30;
		 int startDay=thisDay;
		 int startMonth=thisMonth;
		 int startYear=thisYear;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         listAppts.add(appt);
          
		 // create another appointment
         startHour=14;
		 startMinute=30;
		 startDay=thisDay;
		 startMonth=thisMonth;
		 startYear=thisYear;
		 title="Class";
		 description="Rescheduled class.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         listAppts.add(appt);
         // create another appointment
         startHour=13;
		 startMinute=30;
		 startDay=thisDay;
		 startMonth=thisMonth;
		 startYear=thisYear;
		 title="Meeting Today";
		 description="Meeting with the students.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         listAppts.add(appt);
     

		//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH,1);
		GregorianCalendar asatte = (GregorianCalendar)today.clone();
		asatte.add(Calendar.DAY_OF_MONTH,2);
		GregorianCalendar shiasatte = (GregorianCalendar)today.clone();
		shiasatte.add(Calendar.DAY_OF_MONTH,3);
		GregorianCalendar yesterday = (GregorianCalendar)today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH,-1);
		GregorianCalendar ereyesterday = (GregorianCalendar)today.clone();
		ereyesterday.add(Calendar.DAY_OF_MONTH,-2);
		
		TimeTable timeTable=new TimeTable();
        //Create a linked list of calendar days to return
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays=timeTable.getApptRange(listAppts,today,tomorrow);

		// we should have three appointments now
		assertEquals(3, calDays.get(0).getSizeAppts());
		
		//delete a particular appointment from the list
		LinkedList<Appt> listUpdatedAppts=timeTable.deleteAppt(listAppts, appt);
		assertNotNull(listUpdatedAppts);
		
		// we should only have two appointments now
		calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		assertEquals(2, calDays.get(0).getSizeAppts());
		
		// I don't think this matters but w/e
		for (int i = 0; i < calDays.size(); i++){
		}

		// add an invalid appointment
        startHour=25;
		startMinute=30;
		startDay=thisDay+1;
		startMonth=thisMonth;
		startYear=thisYear;
		title="Meeting Tomorrow";
		description="Meeting with the students.";
		//Construct a new Appointment object with the initial data	 
        appt = new Appt(startHour,
                startMinute ,
                startDay ,
                startMonth ,
                startYear ,
                title,
                description);
        listAppts.add(appt);
        
        calDays=timeTable.getApptRange(listAppts,today,tomorrow);
        
        assertEquals(1, calDays.size());
        
        // try to delete this invalid appointment
        LinkedList<Appt> listUpdatedAppts2=timeTable.deleteAppt(listAppts, appt);
        // it shouldn't have deleted it, and it should have returned null
		assertNull(listUpdatedAppts2);
		
		
		// make that appointment valid, and then try to delete it
		appt = new Appt(startHour,
                startMinute ,
                startDay ,
                startMonth ,
                startYear ,
                title,
                description);
		appt.setStartHour(5);
		assertTrue(appt.getValid());
        LinkedList<Appt> listUpdatedAppts3=timeTable.deleteAppt(listAppts, appt);
        // it shouldn't have deleted it, and it should have returned null
		assertNull(listUpdatedAppts3);
                 
        // look at different slices of time
        calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listUpdatedAppts, tomorrow, asatte);
        assertEquals(1, calDays.size());
        assertEquals(0, calDays.get(0).getSizeAppts());

        calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listUpdatedAppts, yesterday, asatte);
        assertEquals(3, calDays.size());
        assertEquals(0, calDays.get(0).getSizeAppts());
        assertEquals(2, calDays.get(1).getSizeAppts());
        assertEquals(0, calDays.get(2).getSizeAppts());
        
        calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listUpdatedAppts, ereyesterday, yesterday);
        assertEquals(1, calDays.size());
        assertEquals(0, calDays.get(0).getSizeAppts());
        
        // test a bit of null handling
        LinkedList<Appt> mr_null_appts = null;
        Appt mr_null_appt = null;
        assertNull(timeTable.deleteAppt(mr_null_appts, mr_null_appt));
        
	  }
	  
	  
	  
	  
	  
/*	  @Test
		 // I feel like killing some mutants
	  public void test03() throws Throwable {
			// Collection of all of the appointments for the calendar day		
		LinkedList<Appt> listAppts2 = new LinkedList<Appt>();
		
    	
		//get todays date
    	Calendar rightnow = Calendar.getInstance();
    	//current month/year/date is today
    	int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		
		
		 int startHour=15;
		 int startMinute=30;
		 int startDay=thisDay;
		 int startMonth=thisMonth;
		 int startYear=thisYear;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         listAppts2.add(appt);
          
		 // create another appointment
         startHour=14;
		 startMinute=30;
		 startDay=thisDay;
		 startMonth=thisMonth;
		 startYear=thisYear;
		 title="Class";
		 description="Rescheduled class.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         listAppts2.add(appt);
         // create another appointment
         startHour=13;
		 startMinute=30;
		 startDay=thisDay;
		 startMonth=thisMonth;
		 startYear=thisYear;
		 title="Meeting Today";
		 description="Meeting with the students.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         listAppts2.add(appt);
     

		//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH,1);
		GregorianCalendar asatte = (GregorianCalendar)today.clone();
		asatte.add(Calendar.DAY_OF_MONTH,2);
		GregorianCalendar shiasatte = (GregorianCalendar)today.clone();
		asatte.add(Calendar.DAY_OF_MONTH,3);
		GregorianCalendar yesterday = (GregorianCalendar)today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH,-1);
		
		TimeTable timeTable=new TimeTable();
        //Create a linked list of calendar days to return
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays=timeTable.getApptRange(listAppts2,today,tomorrow);



		//delete a particular appointment from the list
		LinkedList<Appt> listUpdatedAppts=timeTable.deleteAppt(listAppts2, appt);
		assertNotNull(listUpdatedAppts);

			calDays = new LinkedList<CalDay>();
			calDays = timeTable.getApptRange(listAppts2, today, tomorrow);
			for (int i = 0; i < calDays.size(); i++){
			}			

		// add an invalid appointment
        startHour=25;
		startMinute=30;
		startDay=thisDay+1;
		startMonth=thisMonth;
		startYear=thisYear;
		title="Meeting Tomorrow";
		description="Meeting with the students.";
		//Construct a new Appointment object with the initial data	 
        appt = new Appt(startHour,
                 startMinute ,
                 startDay ,
                 startMonth ,
                 startYear ,
                 title,
                description);
        listAppts2.add(appt);
        
        calDays=timeTable.getApptRange(listAppts2,today,tomorrow);
        
        assertEquals(1, calDays.size());
        
        // try to delete this invalid appointment
        LinkedList<Appt> listUpdatedAppts2=timeTable.deleteAppt(listAppts2, appt);
        // it shouldn't have deleted it, and it should have returned null
		assertNull(listUpdatedAppts2);
        
        
        
        // look at different slices of time
        calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listAppts2, tomorrow, asatte);
        assertEquals(1, calDays.size());
        assertEquals(4, calDays.get(0).getSizeAppts());
        
        calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listAppts2, yesterday, asatte);
        assertEquals(3, calDays.size());
        
        
        
        calDays = new LinkedList<CalDay>();
		calDays = timeTable.getApptRange(listAppts2, asatte, shiasatte);
        assertEquals(1, calDays.size());

		assertEquals(0, calDays.get(0).getSizeAppts());
	  }
*/

}


