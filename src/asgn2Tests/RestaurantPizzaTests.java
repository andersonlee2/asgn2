package asgn2Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Anderson Lee
 *
 */
public class RestaurantPizzaTests {
	
	String validPizzaLog = "21:00:00,21:35:00,Clueless Customer,0800838383,DVC,4,-5,PZL,3";
	String validLogFile = ".//logs/20170103.txt";
	String faultyLogFile = ".//logs/FaultyLogsForTesting.txt";
	String blankFile = ".//logs/EmptyFile.txt";
	PizzaRestaurant restaurant;
	
	@Before
	public void populateDataset() throws  LogHandlerException, PizzaException, CustomerException{
		restaurant = new PizzaRestaurant();
		restaurant.processLog(validLogFile);
	}
	
	@Test
	public void trueProcessLog() throws  LogHandlerException, PizzaException, CustomerException{
		assertTrue(restaurant.processLog(validLogFile));
	}
	
	@Test (expected = LogHandlerException.class)
	public void faultyProcessLog() throws LogHandlerException, PizzaException, CustomerException{
		restaurant.processLog(faultyLogFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void processBlankDocument() throws PizzaException, CustomerException, LogHandlerException{
		restaurant.processLog(blankFile);
	}
	
	@Test (expected = PizzaException.class)
	public void invalidPizzaIndex() throws PizzaException{
		restaurant.getPizzaByIndex(-1);
	}
	
	@Test
	public void testPizzaOrder(){
		assertTrue(restaurant.getNumPizzaOrders() == 100);
	}
	
	@Test
	public void testPizzaIndex() throws PizzaException{
		restaurant.getPizzaByIndex(0);
	}
	
	@Test
	public void testTotalProfit() throws CustomerException{
		assertTrue(restaurant.getTotalProfit() == 2849.00);
	}
	
	@Test
	public void testResetDetails(){
		restaurant.resetDetails();
		assertTrue(restaurant.getTotalProfit() == 0);
		assertTrue(restaurant.getTotalDeliveryDistance() == 0);
	}
}
