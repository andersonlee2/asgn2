package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Yu Gen Yeap
 *
 */
public class CustomerFactoryTests {
	
	private Customer customer;
	
	@Test(expected = Exception.class)
	 public void invalidCustomerCode() throws Exception {
		 customer = CustomerFactory.getCustomer("DRONE","Ben","0123456789",5,5);
	 }
	
	@Test
	 public void DroneCustomer() throws Exception {
		 customer = CustomerFactory.getCustomer("DNC","Ben","0123456789",5,5);
		 assertEquals(customer.getCustomerType(),"Drone Delivery");
	 }
	
	@Test
	 public void DeliveryCustomer() throws Exception {
		 customer = CustomerFactory.getCustomer("DVC","Ben","0123456789",5,5);
		 assertEquals(customer.getCustomerType(),"Driver Delivery");
	 }
	
	@Test
	 public void PickUpCustomer() throws Exception {
		 customer = CustomerFactory.getCustomer("PUC","Ben","0123456789",0,0);
		 assertEquals(customer.getCustomerType(),"Pick Up");
	 }
}
