package asgn2Tests;

//manually added
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

import static org.junit.Assert.*;

import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 *
 * @author Urvi Patel
 */
public class RestaurantCustomerTests {
	// TO DO
	PizzaRestaurant res;
	PizzaRestaurant test;

	@Before @Test
	public void SetUp() {
		res = new PizzaRestaurant();
		test = new PizzaRestaurant();
	}

	@Test
	public void testProcessLogsFunctionality() throws CustomerException, PizzaException, LogHandlerException {
		assertEquals(true, res.processLog("logs/20170101.txt"));
		assertEquals(3, res.getNumCustomerOrders());
	}

	@Test
	public void testProcessLogsIfInputFileIsWrong() throws CustomerException, PizzaException, LogHandlerException {
		assertEquals(false, test.processLog("logs/2017.txt"));
	}

	@Test
	public void testGetCustomerByIndex() throws CustomerException, PizzaException, LogHandlerException {
		res.processLog("logs/20170101.txt");

		assertEquals("Casey Jones", res.getCustomerByIndex(1).getName());
		assertEquals("April O'Neal", res.getCustomerByIndex(2).getName());
		assertEquals("Oroku Saki", res.getCustomerByIndex(3).getName());
	}

	@Test (expected = CustomerException.class)
	public void testGetCustomerByIndexWhenInvalidIndex() throws CustomerException, PizzaException, LogHandlerException {
		res.processLog("logs/20170101.txt");
		assertEquals(null, res.getCustomerByIndex(0).getName());
	}

	@Test (expected = CustomerException.class)
	public void testGetCustomerByIndexWhenNegativeIndex() throws CustomerException, PizzaException, LogHandlerException {
		res.processLog("logs/20170101.txt");
		assertEquals(null, res.getCustomerByIndex(-2).getName());
	}

	@Test
	public void testGetNumCustomerOrders() throws CustomerException, PizzaException, LogHandlerException {
		res.processLog("logs/20170101.txt");
		assertEquals(3, res.getNumCustomerOrders());
	}

	@Test
	public void testGetNumCustomerOrdersWhenNoCustomers() throws CustomerException, PizzaException, LogHandlerException {
		assertEquals(0, test.getNumCustomerOrders());
	}

	@Test
	public void testGetTotalDeliveryDistance() throws CustomerException, PizzaException, LogHandlerException {
		res.processLog("logs/20170101.txt");
		assertEquals(15, res.getTotalDeliveryDistance(), 0.00);
	}

	@Test
	public void testGetTotalDeliveryDistanceWhenNoCustomers() {
		assertEquals(0, test.getTotalDeliveryDistance(), 0.00);
	}

	@Test
	public void testResetDetails() throws CustomerException, PizzaException, LogHandlerException {
		res.processLog("logs/20170101.txt");
		assertEquals(3, res.getNumCustomerOrders()); //before reset

		res.resetDetails();
		assertEquals(0, res.getNumCustomerOrders()); //after reset
	}
}
