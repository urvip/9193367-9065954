package asgn2Tests;

//manually added
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Restaurant.LogHandler;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Urvi Patel
 */
public class LogHandlerCustomerTests {
	// TO DO
	ArrayList<Customer> loglist;
	ArrayList<Customer> test;

	@Before @Test
	public void SetUp() throws LogHandlerException, CustomerException {
		loglist = LogHandler.populateCustomerDataset("logs/20170101.txt");
	}

	@Test (expected = LogHandlerException.class)
	public void noSuchFileOrDirectoryErrorCheck() throws CustomerException, LogHandlerException {
		test = LogHandler.populateCustomerDataset("logs/20170104.txt");
	}

	@Test
	public void testIfLogListHasDataOrNot() throws CustomerException {
		ArrayList<Customer> testloglist = new ArrayList<Customer>();
		testloglist.add(CustomerFactory.getCustomer("DVC", "Casey Jones","0123456789", 5, 5));
		testloglist.add(CustomerFactory.getCustomer("DNC", "April O'Neal","0987654321", 3, 4));
		testloglist.add(CustomerFactory.getCustomer("PUC", "Oroku Saki","0111222333", 0, 0));

		assertEquals(testloglist, loglist);
	}

	@Test
	public void testCreateCustomer() throws CustomerException, LogHandlerException {
		String line1 = "12:00:00,12:20:00,David Smith,0123456789,DVC,5,5,PZV,1";
		String line2 = "15:00:00,15:20:00,Casey McDonald,0123456789,DNC,3,4,PZM,2";

		Customer newCustomer1 = LogHandler.createCustomer(line1);
		Customer newCustomer2 = LogHandler.createCustomer(line2);

		assertEquals("David Smith", newCustomer1.getName());
		assertEquals("Casey McDonald", newCustomer2.getName());
	}

	@Test (expected = CustomerException.class)
	public void testCreateCustomerWhenInvalidInputs() throws CustomerException, LogHandlerException {
		String line1 = "12:00:00,12:20:00,David Smith,123456789,DVC,0,0,PZV,1";
		Customer newCustomer = LogHandler.createCustomer(line1);
		assertEquals(null, newCustomer.getName());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreateCustomerIfLineIsNull() throws CustomerException, LogHandlerException {
		String lineNull = null;
		Customer newCustomer = LogHandler.createCustomer(lineNull);
		assertEquals(null, newCustomer.getName());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreateCustomerIfLineIsEmpty() throws CustomerException, LogHandlerException {
		String lineEmpty = " ";
		Customer newCustomer = LogHandler.createCustomer(lineEmpty);
		assertEquals(null, newCustomer.getName());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreateCustomerIfLineIsEmptyWithCommas() throws CustomerException, LogHandlerException {
		String lineEmptyWithCommas = " , , , , , , , , ";
		Customer newCustomer = LogHandler.createCustomer(lineEmptyWithCommas);
		assertEquals(null, newCustomer.getName());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreateCustomerIfLineSeparatorIsInCorrect() throws CustomerException, LogHandlerException {
		String line1 = "12:00:00.12:20:00.David Smith.123456789.DVC.0.0.PZV,1";
		String line2 = "12:00:00/12:20:00/David Smith.123456789-DVC-0-0/PZV/1";

		Customer newCustomer1 = LogHandler.createCustomer(line1);
		Customer newCustomer2 = LogHandler.createCustomer(line2);

		assertEquals(null, newCustomer1.getName());
		assertEquals(null, newCustomer2.getName());
	}

}
