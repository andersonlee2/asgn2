package asgn2Tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Yu Gen Yeap
 * 
 *
 */
public class CustomerTests {
	
	private Customer customer;
	private Customer droneCustomer;
	
	 @Test(expected = Exception.class)
	 public void emptyName() throws Exception {
		 customer = new DroneDeliveryCustomer("","123456789",5,5);
	 }
	 
	 @Test(expected = Exception.class)
	 public void longName() throws Exception {
		 customer = new DroneDeliveryCustomer("Mr Pizza guy with a long name","123456789",5,12);
	 }
	 
	 @Test(expected = Exception.class)
	 public void emptyPhoneNumber() throws Exception {
		 customer = new DroneDeliveryCustomer("Pizza Man","",5,5);
	 }
	 
	 @Test(expected = Exception.class)
	 public void numberDoesntStartWith0() throws Exception {
		 customer = new DroneDeliveryCustomer("Pizza Man","1234567890",5,5);
	 }
	 
	 @Test(expected = Exception.class)
	 public void shortPhoneNumber() throws Exception {
		 customer = new DroneDeliveryCustomer("Pizza Man","073",5,5);
	 }
	 
	 @Test(expected = Exception.class)
	 public void longPhoneNumber() throws Exception {
		 customer = new DroneDeliveryCustomer("Pizza Man","01234567899898",5,5);
	 }
	 
	 @Test(expected = Exception.class)
	 public void outOfBoundsXCoordiante() throws Exception {
		 customer = new DroneDeliveryCustomer("Pizza Man","0123456789",11,5);
	 }
	
	 @Test(expected = Exception.class)
	 public void outOfBoundsYCoordiante() throws Exception {
		 customer = new DroneDeliveryCustomer("Pizza Man","0123456789",5,12);
	 }
	 
	 @Test(expected = Exception.class)
	 public void pickUpNotAtStore() throws Exception {
		 customer = new PickUpCustomer("Pizza Man","0123456789",0,1);
	 }
	 
	 @Before
	 public void initializeCustomer() throws Exception {
		 customer = new DriverDeliveryCustomer("Pizza Man", "0888888888", 2,3);
	 }
	 
	 @Test
	 public void customerName() {
		 assertEquals(customer.getName(), "Pizza Man");
	 }
	 
	 @Test
	 public void customerNumber() {
		 assertEquals(customer.getMobileNumber(), "0888888888");
	 }
	 
	 @Test
	 public void customerType() {
		 assertEquals(customer.getCustomerType(), "Driver Delivery");
	 }
	 
	 @Test
	 public void customerXLocation(){
		 assertEquals(customer.getLocationX(), 2);
	 }
	 
	 @Test
	 public void customerYLocation(){
		 assertEquals(customer.getLocationY(), 3);
	 }
	 
	 @Test //Complete
	 public void deliveryDistance(){
		 assertTrue(customer.getDeliveryDistance() == 5);
	 }
	 
	 @Test
	 public void droneDistance() throws Exception {
		 droneCustomer = new DroneDeliveryCustomer("Pizza Man", "0888888888",4,3);
		 assertTrue(droneCustomer.getDeliveryDistance() == 5);
	 }
}
