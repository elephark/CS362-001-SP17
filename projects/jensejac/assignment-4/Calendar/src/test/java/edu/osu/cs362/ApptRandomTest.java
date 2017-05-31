package edu.osu.cs362;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
    	// The list of the of methods to be tested in the Appt class
        String[] methodArray = new String[] {
        	"setTitle",
        	"setDescription",
        	"setStartHour",
        	"setStartMinute",
        	"setStartDay",
        	"setStartMonth"
        };

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	
	
	
    /**
     * Generate Random Tests that tests Appt Class.
     */
	 @Test
	  public void randomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		
		
		System.out.println("Start testing...");
		
		
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis();
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				
				// start with everything valid, in the middle of the valid range
				int startHour=11;
				int startMinute=30;
				int startDay=15;
				int startMonth=6;
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
				for (int i = 0; i < NUM_TESTS; i++) {
					int mrRand;
					String methodName = ApptRandomTest.RandomSelectMethod(random);
						if (methodName.equals("setTitle")){
							String newTitle=(String) ValuesGenerator.getString(random);
							appt.setTitle(newTitle);			
							// todo: check if it's null			   
						}
						else if (methodName.equals("setDescription")){
							String newDescription=(String) ValuesGenerator.getString(random);
							appt.setDescription(newDescription);
							// check if it's null
							//boolean descIsNull = (newDescription == null);
							if (newDescription == null) {
								// todo: strcmp or whatever
								assertTrue(appt.getDescription().equals(""));
							}		   
						}
						else if (methodName.equals("setStartHour")) {
							// fuzzing range generates both valid and invalid values
							mrRand = ValuesGenerator.getRandomIntBetween(random, -16, 16);
							// add the random value to the expected value
							startHour += mrRand;
							// set it
							appt.setStartHour(startHour);
							// make sure it's set
							assertEquals(startHour, appt.getStartHour());
						}
						else if (methodName.equals("setStartMinute")) {
							// fuzzing range generates both valid and invalid values
							mrRand = ValuesGenerator.getRandomIntBetween(random, -45, 45);
							// add the random value to the expected value
							startMinute += mrRand;
							// set it
							appt.setStartMinute(startMinute);
							// make sure it's set
							assertEquals(startMinute, appt.getStartMinute());
						}
						else if (methodName.equals("setStartDay")) {
							// fuzzing range generates both valid and invalid values
							mrRand = ValuesGenerator.getRandomIntBetween(random, -20, 20);
							// add the random value to the expected value
							startDay += mrRand;
							// set it
							appt.setStartDay(startDay);
							// make sure it's set
							assertEquals(startDay, appt.getStartDay());
						}
						else if (methodName.equals("setStartMonth")) {
							// fuzzing range generates both valid and invalid values
							mrRand = ValuesGenerator.getRandomIntBetween(random, -10, 10);
							// add the random value to the expected value
							startMonth += mrRand;
							// set it
							appt.setStartMonth(startMonth);
							// make sure it's set
							assertEquals(startMonth, appt.getStartMonth());
						}
						
						
						
						// check to see if we're valid
						boolean expectedValid = (
							startHour >= 0 &&
							startHour <= 23 &&
							startMinute >= 0 &&
							startMinute <= 59 &&
							startDay >= 1 &&
							startDay <= 31 &&
							startMonth >= 1 &&
							startMonth <= 12
						);
						assertEquals(expectedValid, appt.getValid());
					
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%30000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
	 
	 
		 System.out.println("Done testing...");
	 }
}
