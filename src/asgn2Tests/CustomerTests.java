package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 *
 * @author Hieu Vuong
 *
 */
public class RestaurantPizzaTests {
	// TO DO
	PizzaRestaurant restaurant;
	PizzaRestaurant test;

	@Before @Test
	public void setup () {
		restaurant = new PizzaRestaurant();
		test = new PizzaRestaurant();
	}

	@Test
	public void testProcessLogsFunctionality() throws CustomerException, PizzaException, LogHandlerException {
		assertEquals(true, restaurant.processLog("logs/20170101.txt"));
		assertEquals(3, restaurant.getNumPizzaOrders());
	}

	@Test
	public void testProcessLogsIfInputFileIsWrong() throws CustomerException, PizzaException, LogHandlerException {
		assertEquals(false, test.processLog("logs/20171112.txt"));
	}

	@Test
	public void testGetPizzaByIndex() throws CustomerException, PizzaException, LogHandlerException {
		restaurant.processLog("logs/20170101.txt");

		assertEquals("Vegetarian", restaurant.getPizzaByIndex(1).getPizzaType());
		assertEquals("Margherita", restaurant.getPizzaByIndex(2).getPizzaType());
		assertEquals("Meat Lovers", restaurant.getPizzaByIndex(3).getPizzaType());
	}

	@Test (expected = PizzaException.class)
	public void testGetPizzaByIndexWhenInvalidIndex() throws CustomerException, PizzaException, LogHandlerException {
		restaurant.processLog("logs/20170101.txt");
		assertEquals(null, restaurant.getPizzaByIndex(0).getPizzaType());
	}

	@Test (expected = PizzaException.class)
	public void testGetPizzaByIndexWhenNegativeIndex() throws CustomerException, PizzaException, LogHandlerException {
		restaurant.processLog("logs/20170101.txt");
		assertEquals(null, restaurant.getPizzaByIndex(-2).getPizzaType());
	}

	@Test
	public void testGetNumPizzaOrders() throws CustomerException, PizzaException, LogHandlerException {
		restaurant.processLog("logs/20170101.txt");
		assertEquals(3, restaurant.getNumPizzaOrders());
	}

	@Test
	public void testGetNumPizzaOrdersWhenNoCustomers() throws CustomerException, PizzaException, LogHandlerException {
		assertEquals(0, test.getNumPizzaOrders());
	}

	@Test
	public void testGetTotalProfit () throws CustomerException, PizzaException, LogHandlerException {
		restaurant.processLog("logs/20170101.txt");
		assertEquals(64,restaurant.getTotalProfit(), 0.00);
	}



	@Test
	public void testResetDetails() throws CustomerException, PizzaException, LogHandlerException {
		restaurant.processLog("logs/20170101.txt");
		assertEquals(3, restaurant.getNumPizzaOrders()); //before reset

		restaurant.resetDetails();
		assertEquals(0, restaurant.getNumPizzaOrders()); //after reset
	}



}
