package asgn2Pizzas;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Yu Gen Yeap
 *
 */
public abstract class Pizza  {
	
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	private String type;
	private double price;
	private double totalCost;
	protected LinkedList<PizzaTopping> Toppings;
	protected int numOfToppings;

	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if there is less than one pizza ordered, more than ten pizzas ordered, 
	 * an order sent before 7pm, an order sent after 11pm or if the delivery time exceeds the order time by an hour
	 * 
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.type = type;
		this.price = price;
		
		Toppings = new LinkedList<PizzaTopping>();
		
		if (quantity < 1){
			throw new PizzaException("Must have more than one pizza");
		}
		
		if (quantity > 10){
			throw new PizzaException("Cannot have more than 10 pizzas");
		}
		
		if (orderTime.isBefore(LocalTime.of(18,59,59))){
			throw new PizzaException("Kitchen is not open yet");
		}
		
		if (orderTime.isAfter(LocalTime.of(23,00,01))){
			throw new PizzaException("Kitchen is closed");
		}
		
		if (Duration.between(orderTime,deliveryTime).toMinutes() >= 60){
			throw new PizzaException("1 hour exceeded, pizza thrown out");
		}
	}

	/**
	 * Calculates how much a pizza would cost to make, calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		totalCost = 0;
		for (int i=0; i <= numOfToppings -1; i++){
			totalCost += Toppings.get(i).getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		calculateCostPerPizza();
		return this.totalCost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		//No magic numbers
		double margheritaPrice = 8.0;
		double vegetarianPrice = 10.0;
		double meatLoversPrice = 12.0;
		
		switch(type) {
			case "Margherita" : price = margheritaPrice;
				break;
			case "Vegetarian" : price = vegetarianPrice;
				break;
			case "Meat Lovers": price = meatLoversPrice;
				break;
		}
		return price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		return quantity * this.getCostPerPizza();
		
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		return quantity * this.getPricePerPizza();
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		return getOrderPrice() - getOrderCost();
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
			if (this.Toppings.contains(topping)){
				return true;
			} else {
				return false;
			}
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return this.quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return this.type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}

