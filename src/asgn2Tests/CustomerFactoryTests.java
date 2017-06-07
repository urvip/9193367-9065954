package asgn2Tests;

//manually imported
import asgn2Exceptions.CustomerException;
import asgn2Customers.*;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 *
 * @author Urvi Patel
 *
 */
public class CustomerFactoryTests {
	// TO DO
	Customer cPickup;
	Customer cDriver;
	Customer cDrone;
	Customer customer;

	final static String pickup = "PUC";
	final static String drone = "DNC";
	final static String driver = "DVC";

	@Before @Test
	public void SetUp() throws CustomerException {
		cPickup = CustomerFactory.getCustomer(pickup, " Bob ", "0412345678", 0, 0);
		cDriver = CustomerFactory.getCustomer(driver, "Billy", " 0487654321 ", 5, 6);
		cDrone = CustomerFactory.getCustomer(drone, "Ben", "0412348765", 3, 4);
	}

	@Test
	public void pickUpCustomerCheck() throws CustomerException {
		assertEquals(0, cPickup.getDeliveryDistance(), 0.01);
		assertEquals(pickup, cPickup.getCustomerType());
		assertEquals("Bob", cPickup.getName());
		assertEquals("0412345678", cPickup.getMobileNumber());
	}

	@Test
	public void driverCustomerCheck() throws CustomerException {
		assertEquals(11, cDriver.getDeliveryDistance(), 0.01);
		assertEquals(driver, cDriver.getCustomerType());
		assertEquals("Billy", cDriver.getName());
		assertEquals("0487654321", cDriver.getMobileNumber());
	}

	@Test
	public void droneCustomerCheck() throws CustomerException {
		assertEquals(5, cDrone.getDeliveryDistance(), 0.01);
		assertEquals(drone, cDrone.getCustomerType());
		assertEquals("Ben", cDrone.getName());
		assertEquals("0412348765", cDrone.getMobileNumber());
	}

	@Test (expected = CustomerException.class)
	public void invalidCustomerCode() throws CustomerException {
		customer = CustomerFactory.getCustomer("DMC", "John", "0412345678", 0, 0);
	}

	@Test (expected = CustomerException.class)
	public void unmatchingCustomerCodeAndCoordinates1() throws CustomerException {
		customer = CustomerFactory.getCustomer(pickup, "John", "0412345678", 1, 0);
	}

	@Test (expected = CustomerException.class)
	public void unmatchingCustomerCodeAndCoordinates2() throws CustomerException {
		customer = CustomerFactory.getCustomer(driver, "John", "0412345678", 0, 0);
	}

	@Test (expected = CustomerException.class)
	public void unmatchingCustomerCodeAndCoordinates3() throws CustomerException {
		customer = CustomerFactory.getCustomer(drone, "John", "0412345678", 0, 0);
	}

	@Test (expected = CustomerException.class)
	public void invalidName() throws CustomerException {
		customer = CustomerFactory.getCustomer(drone, "", "0412345678", 3, 4);
	}

	@Test (expected = CustomerException.class)
	public void invalidPhoneNumber_NoZeroAtTheStart() throws CustomerException {
		customer = CustomerFactory.getCustomer(drone, "John", "1412345678", 3, 4);
	}

	@Test (expected = CustomerException.class)
	public void invalidPhoneNumber_NotEnoughDigits() throws CustomerException {
		customer = CustomerFactory.getCustomer(drone, "John", "041234", 3, 4);
	}

	@Test (expected = CustomerException.class)
	public void invalidPhoneNumber_TooManyDigits() throws CustomerException {
		customer = CustomerFactory.getCustomer(drone, "John", "041234567890", 3, 4);
	}
}
