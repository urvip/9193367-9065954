package asgn2Restaurant;

import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Pizzas.Pizza;

//manually added
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

/**
 * This class acts as a �model� of a pizza restaurant. It contains an ArrayList of Pizza objects and an ArrayList of  Customer objects.
 *  It contains a method that can populate the ArrayLists,  several methods to retrieve information about the ArrayLists and
 *  a method to reset the array list. Information about the x and y location of the restaurant and the time that first and last
 *  orders are accepted are listed in Section 5 of the Assignment Specification.
 *
 *  Any exceptions raised by one of the methods called by this class should be passed to asgn2GUIs.PizzaGUI so that it can be shown to
 *  the user.
 *
 * @author Urvi Patel and Hieu Vuong
 *
 */
public class PizzaRestaurant {

	private ArrayList<Customer> customers;
	private ArrayList<Pizza> pizzas;


	/**
	 * Creates an instance of the PizzaRestaurant and sets the customers and pizzas fields to
	 * an appropriate initial empty state.
	 *
	 * <P> PRE: TRUE
	 * <P> POST: The customers and pizzas fields are initialized to an empty state
	 *
	 */
	public PizzaRestaurant() {
		// TO DO
		customers = new ArrayList<Customer>();
		pizzas = new ArrayList<Pizza>();
	}

	/**
	 * This method processes the information contained in the log file and populates the customers and pizzas fields.
	 * The other classes that the method interacts with are listed in Section 11 of the specification document.
     *
     * <P> PRE: TRUE
     * <P>POST: If no exception is thrown then the customers and pizzas fields are populated with the details in the log file ordered as they appear in the log file.
     * <P>      If an exception is thrown then the customers and pizzas fields should be empty.
     *
	 * @param filename The log's filename
	 * @return true if the file was processed correctly
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above (passed by another class).
     *
	 */
	public boolean processLog(String filename) throws CustomerException, PizzaException, LogHandlerException{
		// TO DO
		boolean haveOrdered = true;
		try {
			customers = LogHandler.populateCustomerDataset(filename);
			pizzas = LogHandler.populatePizzaDataset(filename);
		} catch (CustomerException a) {
			haveOrdered = false;
			System.out.println(a.getMessage());
			a.printStackTrace();
		} catch (PizzaException a) {
			haveOrdered = false;
			System.out.println(a.getMessage());
			a.printStackTrace();
		} catch (LogHandlerException a) {
			haveOrdered = false;
			System.out.println(a.getMessage());
			a.printStackTrace();
		}
		return haveOrdered;
	}

	/**
	 * Returns the Customer object contained at the specified index of the customers field. The index should be the same as the index in the log file.
	 * @param index - The index within the customers field to retrieve.
	 * @return The Customer object located at the specified index.
	 * @throws CustomerException if index is invalid.
	 */
	public Customer getCustomerByIndex(int index) throws CustomerException{
		// TO DO
		if (customers.size() < 0 || index <= 0)
			throw new CustomerException("Error: Invalid index number");
		return customers.get(index-1);
	}

	/**
	 * Returns the Pizza object contained at the specified index of the pizzas field. The index should be the same as the index in the log file.
	 * @param index - The index within the pizzas field to retrieve.
	 * @return The Pizza object located at the specified index.
	 * @throws PizzaException if index is invalid.
	 */
	public Pizza getPizzaByIndex(int index) throws PizzaException{
		// TO DO
		if (pizzas.size() < 0 || index <= 0)
			throw new PizzaException("Error: Invalid index number");
		return pizzas.get(index-1);
	}

	/**
	 * Returns the number of objects contained in the pizzas field. This value SHOULD be the same as
	 * the value returned by getNumCustomerOrders.
	 *
	 * @return the number of objects contained in the pizzas field.
	 */
	public int getNumPizzaOrders(){
		// TO DO
		try {
			if (pizzas.size() == 0)
				throw new CustomerException("Error: No pizzas ordered");
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return pizzas.size();
	}

	/**
	 * Returns the number of objects contained in the customers field. This value SHOULD be the same as
	 * the value returned by getNumPizzaOrders.
	 *
	 * @return the number of objects contained in the customers field.
	 */
	public int getNumCustomerOrders(){
		// TO DO
		try {
			if (customers.size() == 0)
				throw new CustomerException("Error: No customers");
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


		return customers.size();
	}


	/**
	 * Returns the total delivery distance for all of the customers.
	 *
	 * @return the total delivery distance for all Customers objects in the customers field.
	 */
	public double getTotalDeliveryDistance(){
		// TO DO
		double result = 0;
		try { //in case if there is no customers
			if (customers.size() == 0)
				throw new CustomerException("Error: No customers/orders");
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		for (Customer customer : customers) {
			if (customer.getCustomerType().equals("DNC") || customer.getCustomerType().equals("DVC"))
				result += customer.getDeliveryDistance();
		}
		return result;
	}

	/**
	 * Returns the total profit for all of the pizza orders.
	 *
	 * @return the total profit for all of the Pizza objects in the pizzas field.
	 */
	public double getTotalProfit(){
		// TO DO
		double profit = 0;
		try { //in case if there is no pizza orders
			if (pizzas.size() == 0)
				throw new PizzaException("Error: No pizzas ordered");
		} catch (PizzaException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		for (int i = 0; i < pizzas.size(); i++) {
			profit += pizzas.get(i).getOrderProfit();
		}
		return profit;
	}

	/**
	 * Resets the pizzas and customers fields to their initial empty states.
	 *
	 * <P> PRE: True
	 * <P> POST:  The pizzas and customers fields are set to their initial empty states
	 */
	public void resetDetails(){
		// TO DO
		pizzas.clear();
		customers.clear();
	}

}
