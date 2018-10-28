package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;

/**
 * 
 *  A class that represents a meat lovers pizza made at the Pizza Palace restaurant. 
 *  The meat lovers pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author PersonA
 *
 */
public class MeatLoversPizza extends Pizza {

	/**
	 * 
	 *  This class represents a meat lovers pizza made at the  Pizza Palace restaurant. The meat lovers pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
 	 * <P> PRE: TRUE
 	 * <P> POST: All field values including the cost per pizza are set
     *
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid, exceptions will be rethrown into the GUI
	 *
	 */
	
	private static String type = "Meat Lovers";
	private static double price = 12.0;
	
	public MeatLoversPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		super(quantity, orderTime, deliveryTime, type, price);
		
		Toppings.removeAll(Toppings);
		Toppings.add(PizzaTopping.TOMATO);
		Toppings.add(PizzaTopping.CHEESE);
		Toppings.add(PizzaTopping.BACON);
		Toppings.add(PizzaTopping.PEPPERONI);
		Toppings.add(PizzaTopping.SALAMI);
		numOfToppings = 5;
	}

}

