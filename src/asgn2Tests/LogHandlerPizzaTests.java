package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
*
* @author Hieu Vuong
*
*/
public class LogHandlerPizzaTests {
	// TO DO
	ArrayList<Pizza> pizzaList;
	ArrayList<Pizza> test;

	@Before @Test
	public void setup () throws PizzaException, LogHandlerException {
		pizzaList = LogHandler.populatePizzaDataset("logs/20170101.txt");
	}

	@Test (expected = LogHandlerException.class)
	public void noSuchFileErrorCheck () throws PizzaException, LogHandlerException {
		pizzaList = LogHandler.populatePizzaDataset("logs/20170222.txt");
	}

	@Test
	public void testIfPizzaListHasDataOrNot() throws PizzaException {
		ArrayList<Pizza> testPizzaList = new ArrayList<Pizza>();
		testPizzaList.add(PizzaFactory.getPizza("PZV", 2, LocalTime.parse("19:00:00"), LocalTime.parse("19:20:00")));
		testPizzaList.add(PizzaFactory.getPizza("PZM", 1, LocalTime.parse("20:00:00"), LocalTime.parse("20:25:00")));
		testPizzaList.add(PizzaFactory.getPizza("PZL", 3, LocalTime.parse("21:00:00"), LocalTime.parse("21:35:00")));
		assertEquals(testPizzaList, pizzaList);
	}

	@Test
	public void testCreatePizza () throws PizzaException, LogHandlerException {
		String line1 = "19:10:00,19:30:00,Casey Jones,0123456789,DVC,5,3,PZV,2";
		String line2 = "22:10:00,22:40:00,Hieu Jones,0123455799,DNC,3,4,PZM,1";

		Pizza newPizza1 = LogHandler.createPizza(line1);
		Pizza newPizza2 = LogHandler.createPizza(line2);

		assertEquals("Vegetarian", newPizza1.getPizzaType());
		assertEquals("Margherita", newPizza2.getPizzaType());
	}

	@Test (expected = PizzaException.class)
	public void testInvalidInputInCreatePizza () throws PizzaException, LogHandlerException {
		String line1 = "20:10:00,20:40:00,Casey Jones,0122456789,DVC,5,3,PZZ,2";
		Pizza newPizza = LogHandler.createPizza(line1);
		assertEquals(null, newPizza.getPizzaType());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreatePizzaIfLineIsEmpty() throws PizzaException, LogHandlerException {
		String lineEmpty = " ";
		Pizza newPizza = LogHandler.createPizza(lineEmpty);
		assertEquals(null, newPizza.getPizzaType());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreatePizzaIfLineIsEmptyWithCommas() throws PizzaException, LogHandlerException {
		String lineEmptyWithCommas = " , , , , , , , , ";
		Pizza newPizza = LogHandler.createPizza(lineEmptyWithCommas);
		assertEquals(null, newPizza.getPizzaType());
	}

	@Test (expected = LogHandlerException.class)
	public void testCreatePizzaIfLineSeparatorIsInCorrect() throws PizzaException, LogHandlerException {
		String line1 = "20:10:00.20:40:00.Casey Jones,0122456789.DVC.5.3.PZZ,2";
		String line2 = "20:10:00.20:40:00/Casey Jones/0122456789.DVC.5.3.PZZ/2";

		Pizza newPizza1 = LogHandler.createPizza(line1);
		Pizza newPizza2 = LogHandler.createPizza(line2);

		assertEquals(null, newPizza1.getPizzaType());
		assertEquals(null, newPizza2.getPizzaType());
	}









}
