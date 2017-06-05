package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant.
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza.
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification.
 *
 * @author Urvi Patel
 *
 */
public abstract class Pizza  {
	private int quantity;
	private  LocalTime orderTime, deliveryTime;
	private String type;
	private double price;
	private double orderCost;

	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated.
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 *
	 * @param quantity - The number of pizzas ordered
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid
	 *
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		// TO DO
		LocalTime startTime = LocalTime.parse("19:00:00");
		LocalTime finishTime = LocalTime.parse("23:00:00");
		LocalTime deliveryStart = LocalTime.parse("19:10:00");
		LocalTime deliveryEnd = LocalTime.parse("00:00:00");

		this.quantity = quantity; this.orderTime = orderTime; this.deliveryTime = deliveryTime;
		this.type = type; this.price = price;

		if (quantity <= 0) {
			throw new PizzaException ("ERROR: Please enter pizza quantity.");
		}else if (quantity > 10){
			throw new PizzaException ("ERROR: Only 10 pizzas per order!");
		}

		if (orderTime.isBefore(startTime) == true || orderTime.isAfter(finishTime) == true) {
			throw new PizzaException ("ERROR: Sorry no orders are accepted at this time!");
		}

		if (deliveryTime.isBefore(deliveryStart) == true && deliveryTime.isAfter(deliveryEnd) == true) {
			throw new PizzaException ("ERROR: Sorry no delivery available at this time!");

		}

		if (type.trim().length() == 0 || type.trim().length() > 11) { //pizza type exception
			throw new PizzaException("Error: Please provide valid description of desiarable pizza.");
		}

		if (price <= 0.0) {
			throw new PizzaException ("ERROR: Invalid price!");
		}else if (price > 120.00){
			throw new PizzaException ("ERROR: Exceeded limit!");
		}

	}

	/**
	 * Calculates how much a pizza would cost to make calculated from its toppings.
	 *
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		// TO DO
		if (type.trim().equals("Margherita") == true ) {
			orderCost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost();
		}
		if (type.trim().equals("Vegetarian") == true ) {
			orderCost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost() + PizzaTopping.EGGPLANT.getCost() + PizzaTopping.MUSHROOM.getCost() + PizzaTopping.CAPSICUM.getCost();
		}
		if (type.trim().equals("Meat Lovers") == true ) {
			orderCost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost() + PizzaTopping.BACON.getCost() + PizzaTopping.PEPPERONI.getCost() + PizzaTopping.SALAMI.getCost();
		}

	}

	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		// TO DO
		return orderCost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		// TO DO
		return (price/quantity);
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas.
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas.
	 */
	public final double getOrderCost(){
		// TO DO
		return (quantity * orderCost);
	}

	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas.
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas.
	 */
	public final double getOrderPrice(){
		// TO DO
		return price;
	}


	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		// TO DO
		return price - getOrderCost();
	}


	/**
	 * Indicates if the pizza contains the specified pizza topping or not.
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		// TO DO
		boolean yes = true;
		if (type.equals("Margherita") == true) {
			if (topping == PizzaTopping.CHEESE || topping == PizzaTopping.TOMATO){
				yes = true;
			}else yes = false;
		}

		if (type.equals("Vegetarian") == true) {
			if (topping == PizzaTopping.CHEESE || topping == PizzaTopping.TOMATO || topping == PizzaTopping.EGGPLANT || topping == PizzaTopping.MUSHROOM || topping == PizzaTopping.CAPSICUM){
				yes = true;
			}else yes = false;
		}

		if (type.equals("Meat Lovers") == true) {
			if (topping == PizzaTopping.CHEESE || topping == PizzaTopping.TOMATO || topping == PizzaTopping.BACON || topping == PizzaTopping.PEPPERONI || topping == PizzaTopping.SALAMI){
				yes = true;
			}else yes = false;
		}
		return yes;
	}

	/**
	 * Returns the quantity of pizzas ordered.
	 * @return the quantity of pizzas ordered.
	 */
	public final int getQuantity(){
		// TO DO
		return quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type.
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification.
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		// TO DO
		return type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza()
	 * and getQuantity().
	 *
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}


}
