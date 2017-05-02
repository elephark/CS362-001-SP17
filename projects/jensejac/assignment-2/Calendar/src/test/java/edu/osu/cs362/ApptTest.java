package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=13;
		 int startMinute=30;
		 int startDay=10;
		 int startMonth=4;
		 int startYear=2017;
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

		 // assertions
		 assertTrue(appt.getValid());
		 assertEquals(13, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(10, appt.getStartDay());
		 assertEquals(04, appt.getStartMonth());
		 assertEquals(2017, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());
		 // might as well test the printing while we're here
		 assertEquals("	4/10/2017 at 1:30pm ,Birthday Party, This is my birthday party.\n", appt.toString());
	 }


    /**
     * Test that the set methods work as expected.
     */
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=0;
		 int startMinute=12;
		 int startDay=18;
		 int startMonth=7;
		 int startYear=2018;
		 String title="Fire Drill";
		 String description="Everybody is going to panic.";
		 // Construct a new Appointment object with the initial data from before
		 // (can be anything, it doesn't matter)
		 Appt appt = new Appt(13, 30, 10, 4, 2017, "Birthday Party",
		         "This is my birthday party.");

		 // Change everything and make sure it sticks
		 appt.setStartHour(startHour);
		 appt.setStartMinute(startMinute);
		 appt.setStartDay(startDay);
		 appt.setStartMonth(startMonth);
		 appt.setStartYear(startYear);
		 appt.setTitle(title);
		 appt.setDescription(description);


		 // assertions
		 assertTrue(appt.getValid());
		 assertEquals(startHour, appt.getStartHour());
		 assertEquals(startMinute, appt.getStartMinute());
		 assertEquals(startDay, appt.getStartDay());
		 assertEquals(startMonth, appt.getStartMonth());
		 assertEquals(startYear, appt.getStartYear());
		 assertEquals(title, appt.getTitle());
		 assertEquals(description, appt.getDescription());
		 assertEquals("	7/18/2018 at 12:12am ,Fire Drill, Everybody is going to panic.\n", appt.toString());
	 }



    /**
     * Test that we can handle invalid dates/times and null title/desc.
     */
	 @Test
	  public void test03()  throws Throwable  {
		 int startHour=25;
		 int startMinute=99;
		 int startDay=150;
		 int startMonth=4444;
		 int startYear=2018;
		 String title=null;
		 String description=null;
		 // Construct a new Appointment object with all the junk values
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 assertEquals(null, appt.toString());
		 // Change everything and make sure it stays false each time
		 assertFalse(appt.getValid());
		 appt.setStartHour(-5);			// because of the OR in the condition
		 assertFalse(appt.getValid());
		 appt.setStartHour(5);			// fix it and move on
		 assertFalse(appt.getValid());
		 appt.setStartMinute(-123); 	// and so forth
		 assertFalse(appt.getValid());
		 appt.setStartMinute(55);
		 assertFalse(appt.getValid());
		 appt.setStartDay(0);
		 assertFalse(appt.getValid());
		 appt.setStartDay(13);
		 assertFalse(appt.getValid());
		 appt.setStartMonth(0);
		 assertFalse(appt.getValid());
		 appt.setStartMonth(4);
		 // at this point everything should now be fixed and we're valid
		 assertTrue(appt.getValid());
	 }
	
}
