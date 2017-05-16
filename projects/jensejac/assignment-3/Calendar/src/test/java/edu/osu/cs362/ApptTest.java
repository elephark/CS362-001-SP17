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
		 int startHour=11;
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
		 assertEquals(11, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(10, appt.getStartDay());
		 assertEquals(04, appt.getStartMonth());
		 assertEquals(2017, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());
		 // might as well test the printing while we're here
		 assertEquals("	4/10/2017 at 11:30am ,Birthday Party, This is my birthday party.\n", appt.toString());
	 }


    /**
     * Test that the set methods work as expected.
     */
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=12;
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
		 assertEquals("	7/18/2018 at 12:12pm ,Fire Drill, Everybody is going to panic.\n", appt.toString());
		 // test hour subtraction on display
		 appt.setStartHour(13);
		 assertEquals("	7/18/2018 at 1:12pm ,Fire Drill, Everybody is going to panic.\n", appt.toString());
	 }



    /**
     * Test that we can handle invalid dates/times and null title/desc.
     */
	 @Test
	  public void test03()  throws Throwable  {
		 int startHour=24;
		 int startMinute=60;
		 int startDay=32;
		 int startMonth=13;
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
		 appt.setStartHour(-1);			// because of the OR in the condition
		 assertFalse(appt.getValid());
		 appt.setStartHour(5);			// fix it and move on
		 assertFalse(appt.getValid());
		 appt.setStartMinute(-1); 	// and so forth
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
	 
	 
	     /**
     * Test that we can handle invalid dates/times and null title/desc.
     */
	 @Test
	  public void test04()  throws Throwable  {
		 int startHour=0;
		 int startMinute=0;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=2018;
		 String title="Fire Drill";
		 String description="Everybody is going to panic.";
		 // Construct a new Appointment object with all the junk values
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 assertTrue(appt.getValid());
		 //assertEquals(description, appt.toString());
		 // Change everything one at a time
		 appt.setStartHour(-1);	
		 assertFalse(appt.getValid());
		 appt.setStartHour(23);			// fix it and move on
		 assertTrue(appt.getValid());
		 appt.setStartMinute(-1); 	// and so forth
		 assertFalse(appt.getValid());
		 appt.setStartMinute(59);
		 assertTrue(appt.getValid());
		 appt.setStartDay(0);
		 assertFalse(appt.getValid());
		 appt.setStartDay(31);
		 assertTrue(appt.getValid());
		 appt.setStartMonth(0);
		 assertFalse(appt.getValid());
		 appt.setStartMonth(12);
		 // at this point everything should now be fixed and we're valid
		 assertTrue(appt.getValid());
	 }
	
}
