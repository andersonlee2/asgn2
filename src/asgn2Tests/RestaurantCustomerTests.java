package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Yu Gen Yeap
 */
public class RestaurantCustomerTests {
	
	
	String validCustomerLog = "21:00:00,21:35:00,Clueless Customer,0800838383,DVC,4,-5,PZL,3";
	String validLogFile = ".//logs/20170103.txt";
	String faultyLogFile = ".//logs/FaultyLogsForTesting.txt";
	String blankFile = ".//logs/EmptyFile.txt";
	PizzaRestaurant restaurant;
	
	@Before
	public void populateDataset() throws CustomerException, LogHandlerException, PizzaException{
		restaurant = new PizzaRestaurant();
		restaurant.processLog(validLogFile);
	}
	
	@Test
	public void trueProcessLog() throws CustomerException, LogHandlerException, PizzaException{
		assertTrue(restaurant.processLog(validLogFile));
	}
	
	@Test (expected = LogHandlerException.class)
	public void faultyProcessLog() throws CustomerException, LogHandlerException, PizzaException{
		restaurant.processLog(faultyLogFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void processBlankDocument() throws CustomerException, PizzaException, LogHandlerException{
		restaurant.processLog(blankFile);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidCustomerIndex() throws CustomerException{
		restaurant.getCustomerByIndex(-1);
	}
	
	@Test
	public void testCustomerOrder(){
		assertTrue(restaurant.getNumCustomerOrders() == 100);
	}
	
	@Test
	public void testCustomerIndex() throws CustomerException{
		restaurant.getCustomerByIndex(0);
	}
	
	@Test
	public void testDeliveryDistance() throws CustomerException{
		assertTrue(Math.round(restaurant.getTotalDeliveryDistance()) == 519);
	}
	
	@Test
	public void testResetDetails(){
		restaurant.resetDetails();
		assertTrue(restaurant.getTotalProfit() == 0);
		assertTrue(restaurant.getTotalDeliveryDistance() == 0);
	}
}
