package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Anderson Lee
 *
 */
public class PizzaTests {
	
	
	private Pizza pizza;

	
	@Test(expected = Exception.class)
	 public void zeroForQuantity() throws Exception {
		 pizza = new MargheritaPizza(0, LocalTime.of(19,00,00), LocalTime.of(19,20,00) );
	 }
	
	@Test(expected = Exception.class)
	 public void outOfBoundForQuantity() throws Exception {
		 pizza = new MargheritaPizza(123, LocalTime.of(19,00,00), LocalTime.of(19,20,00) );
	 }
	 
	@Test(expected = Exception.class)
	 public void earlyOrder() throws Exception {
		 pizza = new MargheritaPizza(0, LocalTime.of(15,00,00), LocalTime.of(13,20,00) );
	 }
	
	@Test(expected = Exception.class)
	 public void lateOrder() throws Exception {
		 pizza = new MargheritaPizza(0, LocalTime.of(24,00,00), LocalTime.of(24,20,00) );
	 }
	 
	@Test(expected = Exception.class)
	 public void checkIfThrownOut() throws Exception {
		 pizza = new MargheritaPizza(0, LocalTime.of(19,00,00), LocalTime.of(22,20,00) );
	 }
	
	@Test(expected = Exception.class)
	 public void invalidTimeInput() throws Exception {
		 pizza = new MargheritaPizza(0, LocalTime.of(29,00,00), LocalTime.of(19,20,00) );
	 }
	
	
	@Test(expected = Exception.class)
	 public void invalidTimeInputVariation() throws Exception {
		 pizza = new MargheritaPizza(0, LocalTime.of(29,00,00), LocalTime.of(11,20,00) );
	 }
	
	 @Test
	 public void checkMargherPrice() throws Exception {
		 pizza = new MargheritaPizza(2, LocalTime.of(19,00,00), LocalTime.of(19,20,00) );
		 assertEquals(pizza.getOrderCost(), 16, 16);
	 }
	 
	 @Test
	 public void checkMeatLoverPrice() throws Exception {
		 pizza = new MeatLoversPizza(1, LocalTime.of(19,00,00), LocalTime.of(19,20,00) );
		 assertEquals(pizza.getOrderCost(), 12, 12);
	 }
	 
	 @Test
	 public void checkVegePrice() throws Exception {
		 pizza = new VegetarianPizza(3, LocalTime.of(19,00,00), LocalTime.of(19,20,00) );
		 assertEquals(pizza.getOrderCost(), 24, 24);
	 }

	
	
}
