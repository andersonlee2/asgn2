package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Anderson Lee
* 
*/
public class LogHandlerPizzaTests {
	@Test (expected = LogHandlerException.class)
	public void readInsufficientData() throws PizzaException, LogHandlerException{
		String faultyFile = ".//logs/InsufficientData.txt";
		LogHandler.populatePizzaDataset(faultyFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void populateEmptyFile() throws PizzaException, LogHandlerException{
		String faultyFile = ".//logs/EmptyFile.txt";
		LogHandler.populatePizzaDataset(faultyFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void readNonExistentFile() throws LogHandlerException, PizzaException{
		String nonExistentFile = ".//logs/GhostFile.txt";
		LogHandler.populatePizzaDataset(nonExistentFile);
	}
	
	@Test (expected = LogHandlerException.class)
	public void createPizzaWithInsufficientData() throws LogHandlerException, PizzaException{
		String insufficientLog = "21:00:00,21:35:00,Clueless Customer";
		LogHandler.createPizza(insufficientLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void quantityParsingErrors() throws PizzaException, LogHandlerException{
		String pizzaQuantityIsAStringLog = "21:00:00,21:35:00,Clueless Customer,0111222333,PUC,0,0,PZL,five";
		LogHandler.createPizza(pizzaQuantityIsAStringLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void orderTimeParseError() throws PizzaException, LogHandlerException{
		String orderTimeError = "14:00:0,21:35:00,Clueless Customer,0111222333,PUC,0,0,PZL,5";
		LogHandler.createPizza(orderTimeError);
	}
	
	@Test (expected = LogHandlerException.class)
	public void deliveryTimeParseError() throws PizzaException, LogHandlerException{
		String deliveryTimeError = "14:00:00,2:3:00,Clueless Customer,0111222333,PUC,0,0,PZL,5";
		LogHandler.createPizza(deliveryTimeError);
	}
	
	//Initialize a single valid log entry
		String validLog = "20:00:00,20:35:00,Clueless Customer,0800838383,DVC,4,-5,PZL,3";
	
	@Test
	public void testPizzaType() throws PizzaException, LogHandlerException{
		Pizza validPizza = LogHandler.createPizza(validLog);
		String pizzaType = validPizza.getPizzaType();
		assertEquals(pizzaType, "Meat Lovers");
	}
	
	@Test
	public void testPizzaQuantity() throws PizzaException, LogHandlerException{
		Pizza validPizza = LogHandler.createPizza(validLog);
		int pizzaQuantity = validPizza.getQuantity();
		assertEquals(pizzaQuantity, 3);
	}
	
}
