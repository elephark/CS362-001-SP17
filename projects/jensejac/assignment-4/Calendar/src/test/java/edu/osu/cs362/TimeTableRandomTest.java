package edu.osu.cs362;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.LinkedList;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	@Test
	public void randomtest()  throws Throwable  {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		
		System.out.println("Start testing...");
		
		for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			long randomseed =System.currentTimeMillis();
			Random random = new Random(randomseed);
			TimeTable timeTable = new TimeTable();
			
			// special case: if listAppts is null, we can't really do anything to it,
			// so we'll test that one case separately with a 1% probability
			if (ValuesGenerator.getBoolean(0.01f, random)) {
				LinkedList<Appt> listAppts = null;
				Appt mrNullAppt = null;
				assertNull(timeTable.deleteAppt(listAppts, mrNullAppt));
				continue;
			}
			
			// most cases: listAppts is not null
			LinkedList<Appt> listAppts = new LinkedList<Appt>();
			
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
			
			// add a random qty of appointments at random times, probably valid but maybe not
			int apptsToAdd = ValuesGenerator.getRandomIntBetween(random, 0, 20);
			for (int i=0; i<apptsToAdd; i++) {
				int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 26);
				int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 62);
				int startDay=thisDay;//ValuesGenerator.getRandomIntBetween(random, -1, 33);
				int startMonth=thisMonth; // doesn't matter what month, really
				int startYear=thisYear; // doesn't matter what year, really
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
				//calday.addAppt(appt);
				// add it to our linked list
				listAppts.add(appt);
				
			}
			// let's delete some appointments randomly
			// there are four cases we need to worry about:
			// 1. the appt is null
			// 2. the appt is not null, and is not in the list
			// 3. the appt is not null, and is in the list
			// we'll try to divide the test cases among these
			
			int apptsToDel = ValuesGenerator.getRandomIntBetween(random, 0, 20);
			for (int i=0; i<apptsToDel; i++) {
				Appt testAppt;
				// low probability: appt is null
				if (ValuesGenerator.getBoolean(0.01f, random)) {
					testAppt = null;
				}
				// the remaining probability is split evenly: appt is (probably) not in the list
				else if (ValuesGenerator.getBoolean(0.5f, random)) {
					testAppt = new Appt(
						ValuesGenerator.getRandomIntBetween(random, -1, 26),
						ValuesGenerator.getRandomIntBetween(random, -1, 62),
						thisDay,
						thisMonth,
						thisYear,
						ValuesGenerator.getString(random),
						ValuesGenerator.getString(random)
					);
				}
				// appt is in the list
				else {
					// only try this if the list isn't empty, though
					if (listAppts.size() == 0) {continue;}
					int testIndex = ValuesGenerator.getRandomIntBetween(random, 0, (listAppts.size() - 1));
					testAppt = listAppts.get(testIndex);
				}
				
				// try to delete the chosen appt
				LinkedList<Appt> listUpdatedAppts = timeTable.deleteAppt(listAppts, testAppt);
				if (listUpdatedAppts != null) {
					listAppts = listUpdatedAppts;
				}
			}
			
			
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%60000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		}
		System.out.println("Done testing...");
	}
}
