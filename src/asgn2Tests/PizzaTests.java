package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes.
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the
 * asgn2Pizzas.Pizza abstract class.
 *
 * @author Hieu Vuong
 *
 */
public class PizzaTests {
	// TO DO
	Pizza pizza;
	Pizza pizza1;
	Pizza pizza2;
	Pizza pizza3;
	LocalTime orderTime = LocalTime.parse("20:00:00");
	LocalTime deliveryTime = LocalTime.parse("20:15:00");

	@Test
	public void setup () throws PizzaException {
		pizza1 = new MargheritaPizza(2, orderTime, deliveryTime);
		pizza2 = new VegetarianPizza(2, orderTime, deliveryTime);
		pizza3 = new MeatLoversPizza(2, orderTime, deliveryTime);
	}

	@Test
	public void calculateCostPerPizza () throws PizzaException{
		pizza1 = new MargheritaPizza(2, orderTime, deliveryTime);
		pizza1.calculateCostPerPizza();
		assertEquals(1.5, pizza1.getCostPerPizza(), 0.00);
	}

	@Test
	public void calculatePricePerPizza () throws PizzaException {
		pizza1 = new MargheritaPizza(3, orderTime, deliveryTime);
		assertEquals(8, pizza1.getPricePerPizza(), 0.00);
	}

	@Test
	public void calculateOrderCost () throws PizzaException {
		pizza1 = new MargheritaPizza(9, orderTime, deliveryTime);
		pizza1.calculateCostPerPizza();
		assertEquals(13.5, pizza1.getOrderCost(), 0.00);
	}

	@Test
	public void calculateOrderPrice () throws PizzaException {
		pizza1 = new MargheritaPizza (5, orderTime, deliveryTime);
		assertEquals(40, pizza1.getOrderPrice(), 0.00);
	}

	@Test
	public void calculateorderProfit () throws PizzaException {
		pizza1 = new MargheritaPizza (9, orderTime, deliveryTime);
		pizza1.calculateCostPerPizza();
		assertEquals(58.5, pizza1.getOrderProfit(), 0.00);
	}

	@Test
	public void checkingToppings () throws PizzaException {
		pizza1 = new MargheritaPizza (1, orderTime, deliveryTime);
		assertEquals(true, pizza1.containsTopping(PizzaTopping.CHEESE));
	}

	@Test
	public void checkingToppingsOne () throws PizzaException {
		pizza1 = new MargheritaPizza (1, orderTime, deliveryTime);
		assertEquals(false, pizza1.containsTopping(PizzaTopping.BACON));
	}


	@Test (expected = PizzaException.class)
	public void checkingQuantity1 () throws PizzaException {
		pizza1 = new MargheritaPizza (15, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void checkingQuantity2 () throws PizzaException {
		pizza1 = new MargheritaPizza (0, orderTime, deliveryTime);
	}


	@Test (expected = PizzaException.class)
	public void checkOrderTime () throws PizzaException {
		pizza1 = new MargheritaPizza (1, LocalTime.parse("06:00:00"), LocalTime.parse("20:00:00"));
	}

	@Test (expected = PizzaException.class)
	public void checkDeliveryTime () throws PizzaException {
		pizza1 = new MargheritaPizza (1, LocalTime.parse("19:10:00"), LocalTime.parse("00:10:00"));
	}


}
