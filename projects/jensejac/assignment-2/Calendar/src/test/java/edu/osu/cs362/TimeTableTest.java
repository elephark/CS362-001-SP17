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
		GregorianCalendar yesterday = (GregorianCalendar)today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH,-1);
		
		TimeTable timeTable=new TimeTable();
        //Create a linked list of calendar days to return
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays=timeTable.getApptRange(listAppts,today,tomorrow);



		//delete a particular appointment from the list
		LinkedList<Appt> listUpdatedAppts=timeTable.deleteAppt(listAppts, appt);
		assertNotNull(listUpdatedAppts);

			calDays = new LinkedList<CalDay>();
			calDays = timeTable.getApptRange(listAppts, today, tomorrow);
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
         
         
         
         // look at different slices of time
         calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, tomorrow, asatte);
         assertEquals(1, calDays.size());

         calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, yesterday, asatte);
         assertEquals(3, calDays.size());
	  }

}


