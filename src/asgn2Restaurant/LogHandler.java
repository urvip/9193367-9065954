package asgn2Restaurant;


import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory; //manually added
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory; //manually added

//manually added
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 *
 * @author Urvi Patel and Hieu Vuong
 *
 */
public class LogHandler {
	private final static String COMMA = ",";


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @throws If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 *
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		// TO DO
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			BufferedReader info = new BufferedReader(new FileReader(filename));
			String line = info.readLine();
			while (line != null) {
				customerList.add(createCustomer(line));
				line = info.readLine();
			}
			info.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new LogHandlerException("Error: File not found");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new LogHandlerException("Error: Reader cannot parse the given log file");
		}

		return customerList;
	}


	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 *
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		// TO DO
		ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
		try {
			BufferedReader info = new BufferedReader(new FileReader(filename));
			String line = info.readLine();
			while (line != null) {
				pizzaList.add(createPizza(line));
				line = info.readLine();
			}
			info.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new LogHandlerException("Error: File not found");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new LogHandlerException("Error: Reader cannot parse the given log file");
		}

		return pizzaList;
	}


	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of
	 * each line is outlined in Section 5.3 of the Assignment Specification.
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
		// TO DO
		checkLine(line);
		String[] cArr = line.split(COMMA);
		String name = cArr[2];
		String mobileNumber = cArr[3];
		String customerCode = cArr[4];
		int x = Integer.parseInt(cArr[5]);
		int y = Integer.parseInt(cArr[6]);

		return CustomerFactory.getCustomer(customerCode, name, mobileNumber, x, y);
	}

	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of
	 * each line is outlined in Section 5.3 of the Assignment Specification.
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException {
		// TO DO
		checkLine(line);
		String[] cArr = line.split(COMMA);
		LocalTime orderTime = LocalTime.parse(cArr[0]);
		LocalTime deliveryTime = LocalTime.parse(cArr[1]);
		String pizzaCode = cArr[7];
		int quantity = Integer.parseInt(cArr[8]);

		return PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
	}

	/**
	 * This method checks if the line is valid. In order to be a legitimate line,
	 * that line has to always have 8 commas and meaningful contents between commas.
	 * Further exception on contents between commas is executed in
	 * populateCustomerDataset and populatePizzaDataset. This method is used in createCustomer and createPizza
	 *
	 * @param line A line to be inspected (String variable)
	 * @throws LogHandlerException throw LogHandlerException if a line is not correctly formatted
	 */
	private static void checkLine(String line) throws LogHandlerException {
		int counter = 0;

		if (line == null || line == " " || line == " , , , , , , , , ")
			throw new LogHandlerException("Error: File has no contents");
		for (int i = 0; i < line.length(); i++) { //check if line has comma as seperator
			if (line.charAt(i) == COMMA.charAt(0))
				counter++;
		}
		if (counter != 8) //check if a number of required commas in one line, if its not correct, throw exception
			throw new LogHandlerException("Error: Not appropriate file");
	}

}
