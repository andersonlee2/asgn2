package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Anderson Lee 
 * 
 */
public class PizzaFactoryTests {
	
	
private Pizza pizza;
	
	@Test(expected = Exception.class)
	 public void invalidPizzaCode() throws Exception {
		 pizza = PizzaFactory.getPizza("chocolate flavor", 5, LocalTime.of(19,00,00), LocalTime.of(19,20,00));
	 }
	
	@Test
	 public void margherita() throws Exception {
		 pizza = PizzaFactory.getPizza("PZM", 2, LocalTime.of(19,00,00), LocalTime.of(19,20,00));
		 assertEquals(pizza.getPizzaType(),"Margherita");
	 }
	
	@Test
	 public void vegetarian() throws Exception {
		 pizza = PizzaFactory.getPizza("PZV", 2, LocalTime.of(19,00,00), LocalTime.of(19,20,00));
		 assertEquals(pizza.getPizzaType(),"Vegetarian");
	 }
	
	@Test
	 public void meatLovers() throws Exception {
		pizza = PizzaFactory.getPizza("PZL", 2, LocalTime.of(19,00,00), LocalTime.of(19,20,00));
		 assertEquals(pizza.getPizzaType(),"Meat Lovers");
	 }
	
}
