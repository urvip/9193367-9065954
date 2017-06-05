package asgn2Customers;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import asgn2Exceptions.CustomerException;

/** A class that represents a customer that has chosen to have their pizza delivered by a drone.
 * This class extends the abstract Customer class and calculates the delivery distance as the Euclidean
 * Distance between the customer and the restaurant.  A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.
 *
 * @author Hieu Vuong
 *
 */
public class DroneDeliveryCustomer extends Customer {
	private int locationX, locationY;
	private static String drone = "DNC";

	/**
	 *  This class represents a customer of the Pizza Palace restaurant that has chosen to have their pizza delivered by
	 *  a drone.  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment
	 *  Specification are violated.
     *
     * <P> PRE: TRUE
     * <P> POST: All field values are set
     *
	 * @param name - The Customer's name
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks'
	 * @param locationY  The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks'
	 * @throws CustomerException if supplied parameters are invalid
	 *
	 */
	public DroneDeliveryCustomer(String name, String mobileNumber, int locationX, int locationY) throws CustomerException {
		// TO DO
		super(name, mobileNumber, locationX, locationY, drone);

		if (locationX == 0 && locationY == 0) {
			throw new CustomerException("Error: Delivery order");
		}

		this.locationX = locationX; this.locationY = locationY;
	}

	/**
	 * Returns the Euclidean Distance between the instance of DroneDeliveryCustomer and the restaurant. Overrides
	 * getDeliveryDistance() in Customer.
	 *
     * @return The distance between the restaurant and the customer in Euclidean distance.
	 */
	@Override
	public double getDeliveryDistance() {
		// TO DO
		DecimalFormat df = new DecimalFormat("#.0");
		df.setRoundingMode(RoundingMode.CEILING);
		double result = Math.sqrt(Math.pow((double) locationX, 2) + Math.pow((double) locationY, 2));
		return Double.parseDouble(df.format(result));
	}


}
