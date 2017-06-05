package asgn2Tests;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 *
 * @author Hieu Vuong
 *
 */
public class PizzaFactoryTests {
	// TO DO
	Pizza pizza1;


	@Test
	public void functionalTestOne() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZM", 2, LocalTime.parse("20:00:00"), LocalTime.parse("20:15:00"));
	}

	@Test
	public void functionalTestTwo() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZV", 1, LocalTime.parse("19:50:34"), LocalTime.parse("20:10:00"));
	}

	@Test
	public void functionalTestThree() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZL", 1, LocalTime.parse("22:30:12"), LocalTime.parse("22:45:15"));
	}

	@Test (expected = PizzaException.class)
	public void exceptionTestOne() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZZ", 5, LocalTime.parse("20:00:00"), LocalTime.parse("20:15:00"));
	}

	@Test (expected = PizzaException.class)
	public void exceptionTestTwo() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZM", 122, LocalTime.parse("20:00:00"), LocalTime.parse("20:15:00"));
	}

	@Test (expected = PizzaException.class)
	public void exceptionTestThree() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZM", 5, LocalTime.parse("18:00:00"), LocalTime.parse("20:15:00"));
	}

	@Test (expected = PizzaException.class)
	public void exceptionTestFour() throws PizzaException {
		pizza1 = PizzaFactory.getPizza("PZM", 5, LocalTime.parse("23:50:00"), LocalTime.parse("00:15:00"));
	}

}
