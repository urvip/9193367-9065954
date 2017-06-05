package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;

/**
 * A class that instantiates the subclasses of asgn2Pizzas.Pizza using the Factory Method pattern.
 * The classes are instantiated from one of the three valid pizza codes outlined in
 * Section 5.3 of the Assignment Specification. Any other code will throw a PizzaException.
 *
 * @author Urvi Patel
 *
 */

public class PizzaFactory {
	private static Pizza A;



	/**
	 * A method that uses the Factory Method pattern to produce an instance of one of the asgn2Pizzas.Pizza subclasses.
	 * Subclasses are created using the pizzaCode. All valid pizza codes are listed in Section 5.3 of the Assignment Specification.
	 * A PizzaException should be thrown if an invalid pizza code is used as a parameter.
	 *
	 * @param pizzaCode - A code indicating the subclass of asgn2Pizzas.Pizza to instantiate. The valid codes are listed in Section 5.3 of the Assignment Specification.
	 * @param quantity - The number of pizzas ordered
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if the pizzaCode is not one of the three valid codes listed in Section 5.3 of the Assignment Specification.
	 * @return A valid Pizza object using the specified parameters
	 * */
	public static Pizza getPizza(String pizzaCode, int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException{
		// TO DO
		if (pizzaCode.equals("PZM") == true) {
			A = new MargheritaPizza(quantity,orderTime,deliveryTime);
		}

		else if (pizzaCode.equals("PZV") == true) {
			A = new VegetarianPizza(quantity,orderTime,deliveryTime);
		}

		else if (pizzaCode.equals("PZL") == true) {
			A = new MeatLoversPizza(quantity,orderTime,deliveryTime);
		}
		else throw new PizzaException("ERROR: Please provide valid description.");

		return A;
	}


}
