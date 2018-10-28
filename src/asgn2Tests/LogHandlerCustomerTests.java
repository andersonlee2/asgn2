package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Yu Gen Yeap
 */
public class LogHandlerCustomerTests {
	@Test (expected = LogHandlerException.class)
	public void readInsufficientData() throws CustomerException, LogHandlerException{
		String faultyFile = ".//logs/InsufficientData.txt";
		LogHandler.populateCustomerDataset(faultyFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void populateEmptyFile() throws CustomerException, LogHandlerException{
		String faultyFile = ".//logs/EmptyFile.txt";
		LogHandler.populateCustomerDataset(faultyFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void readNonExistentFile() throws LogHandlerException, CustomerException{
		String nonExistentFile = ".//logs/GhostFile.txt";
		LogHandler.populateCustomerDataset(nonExistentFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void createCustomerWithInsufficientData() throws LogHandlerException, CustomerException{
		String insufficientCustomerLog = "21:00:00,21:35:00,Clueless Customer";
		LogHandler.createCustomer(insufficientCustomerLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void coordinateParsingErrors() throws CustomerException, LogHandlerException{
		String coordinatesAreStringsCustomerLog = "21:00:00,21:35:00,Clueless Customer,0111222333,PUC,zero,zero,PZL,3";
		LogHandler.createCustomer(coordinatesAreStringsCustomerLog);
	}
	
	//Initialize a single valid log entry
		String validCustomerLog = "21:00:00,21:35:00,Clueless Customer,0800838383,DVC,4,-5,PZL,3";
	
	@Test
	public void testCustomerName() throws CustomerException, LogHandlerException{
		Customer validCustomer = LogHandler.createCustomer(validCustomerLog);
		String customerName = validCustomer.getName();
		assertEquals(customerName, "Clueless Customer");
	}
	
	@Test
	public void testCustomerNumber() throws CustomerException, LogHandlerException{
		Customer validCustomer = LogHandler.createCustomer(validCustomerLog);
		String customerNumber = validCustomer.getMobileNumber();
		assertEquals(customerNumber, "0800838383");
	}
	
	@Test
	public void testCustomerType() throws CustomerException, LogHandlerException{
		Customer validCustomer = LogHandler.createCustomer(validCustomerLog);
		String customerType = validCustomer.getCustomerType();
		assertEquals(customerType, "Driver Delivery");
	}
	
	@Test
	public void testXCoordinate() throws CustomerException, LogHandlerException{
		Customer validCustomer = LogHandler.createCustomer(validCustomerLog);
		int customerXLocation = validCustomer.getLocationX();
		assertEquals(Integer.toString(customerXLocation), "4");
	}
	
	@Test
	public void testYCoordinate() throws CustomerException, LogHandlerException{
		Customer validCustomer = LogHandler.createCustomer(validCustomerLog);
		int customerYLocation = validCustomer.getLocationY();
		assertEquals(Integer.toString(customerYLocation), "-5");
	}
}
