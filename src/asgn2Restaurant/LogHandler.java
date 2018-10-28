package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Yu Gen Yeap and Anderson Lee
 *
 */
public class LogHandler {
	
	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If any Customer exceptions are caught in this method. Exceptions are then re thrown to the GUI
	 * @throws LogHandlerException If the log file it is searching for doesn't exist, if there was a problem reading the lines or closing the file.
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws LogHandlerException, CustomerException{
		
        BufferedReader br = null;
		
			try {
				br = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
				throw new LogHandlerException();
			}
		        String line;

		ArrayList<Customer> customerList = new ArrayList<Customer>();
			try {
				while( (line = br.readLine()) != null ){
					Customer customerObject = createCustomer(line);
					customerList.add(customerObject);
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new LogHandlerException();
			}
			try {
				br.close();
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new LogHandlerException();
			}
			return customerList;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If any Pizza exceptions are caught in this method. Exceptions are then re thrown to the GUI
	 * @throws LogHandlerException If the log file it is searching for doesn't exist, if there was a problem reading the lines or closing the file.
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		
		BufferedReader br = null;
	
				try {
					br = new BufferedReader(new FileReader(filename));
				} catch (FileNotFoundException exception) {
					exception.printStackTrace();
					throw new LogHandlerException();
				}
			
		String line = null;
		ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
		
			try {
				while((line = br.readLine()) !=null ){
					Pizza pizzaObject = createPizza(line);
					pizzaList.add(pizzaObject);
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new LogHandlerException();
			}
			
			try {
				br.close();
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new LogHandlerException();
			}
			return pizzaList;
		}
			

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors caught by other methods. Exceptions are then rethrown into the GUI
	 * @throws LogHandlerException - If there was a problem parsing any customer objects from the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		
		String[] customerArr = line.split(",");
		int expectedArrayObjects = 9;
		if (customerArr.length != expectedArrayObjects) throw new LogHandlerException("Incomplete Log");
		
		String customerCode = customerArr[4];
		String name = customerArr[2];
		String mobileNumber = customerArr[3];
		
		int locationX;
		try {
			locationX = Integer.parseInt(customerArr[5]);
		} catch (NumberFormatException | IndexOutOfBoundsException exception) {
			exception.printStackTrace();
			throw new LogHandlerException();
		}
		
		
		int locationY;
		try {
			locationY = Integer.parseInt(customerArr[6]);
		} catch (NumberFormatException | IndexOutOfBoundsException exception) {
			exception.printStackTrace();
			throw new LogHandlerException();
		}

		Customer newCustomer = CustomerFactory.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
		
		return newCustomer;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors caught by other methods. Exceptions are then rethrown into the GUI
	 * @throws LogHandlerException - If there was a problem parsing any pizza objects from the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
				
		
		String[] pizzaArray = line.split(",");
		int expectedArrayObjects = 9;
		if (pizzaArray.length != expectedArrayObjects) throw new LogHandlerException("Incomplete Log");
		
		String pizzaCode = pizzaArray[7];
		
		int quantity;
		try {
			quantity = Integer.parseInt(pizzaArray[8]);
		} catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException exception) {
			exception.printStackTrace();
			throw new LogHandlerException();
		}
		
		LocalTime orderTime;
		try {
			orderTime = LocalTime.parse(pizzaArray[0]);
		} catch (DateTimeParseException | IndexOutOfBoundsException | NumberFormatException exception) {
			exception.printStackTrace();
			throw new LogHandlerException();
		}
		
		LocalTime deliveryTime;
		try {
			deliveryTime = LocalTime.parse(pizzaArray[1]);
		} catch (DateTimeParseException | IndexOutOfBoundsException | NumberFormatException exception) {
			exception.printStackTrace();
			throw new LogHandlerException();
		}
		
		Pizza newPizza = PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
		
		return newPizza;
	}

}
