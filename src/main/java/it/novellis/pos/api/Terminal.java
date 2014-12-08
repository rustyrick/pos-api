package it.novellis.pos.api;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Class representing the top level POS terminal service.
 * Instances of this class define a map of products/prices and keep track of the products in a shopping cart,
 * with their correspondent, to simply calculate the total amount due, even in case of volume discounts.
 * 
 * @author francesco.novellis
 *
 */
public class Terminal {
	
	private final static Logger logger = Logger.getLogger(Terminal.class.getName());
	private Map<String, ProductType> _productList = null;
	
	/**
	 * Class constructor.
	 * It initializes the product list which keeps track of the available products, their prices
	 * and their count in the shopping cart.
	 * 
	 */
	public Terminal() {
		_productList = new HashMap<String, ProductType>();
		logger.getParent().getHandlers()[0].setFormatter(new MyLogFormatter());
	}
	
	/**
	 * Thread-safe public method to define the unit/lot prices of a specific product type.
	 * 
	 * @param iCode, String value to identify the product type
	 * @param iUnitPrice, double value indicating the unit price of the product
	 * @param iLotPrice, double value indicating the lot price of the product
	 * @param iLotQuantity, int value indicating the amount of items needed for the volume discount
	 */
	public synchronized void setPricing(String iCode, double iUnitPrice, double iLotPrice, int iLotQuantity) {
		if(!_productList.containsKey(iCode)) {
			_productList.put(iCode, new ProductType(iCode, iUnitPrice, iLotPrice, iLotQuantity));
			
			StringBuilder logmsg = new StringBuilder();
			logmsg.append("Adding product type ")
				.append(iCode).append(" - unit price: ").append(iUnitPrice)
				.append(", lot price: ").append(iLotPrice).append(" for ")
				.append(iLotQuantity).append(" items)");
			logger.info(logmsg.toString());
		}
	}
	
	/**
	 * Thread-safe public method to add an item to the shopping cart.
	 * 
	 * @param iCode, char value identifying the product type of the item being added 
	 * @return true if operation succeeds, false in case the product type is not found in the map
	 */
	public synchronized boolean scan(String iCode) {
		StringBuilder logmsg = new StringBuilder();
		try {
			_productList.get(iCode).addItem();
			logmsg.append("Adding an item of type ").append(iCode).append(" to the shopping cart.");
			logger.info(logmsg.toString());
			return true;
		}
		catch(NullPointerException e) {
			logmsg.append("Product type ").append(iCode).append(" not found!");
			logger.warning(logmsg.toString());
			return false;
		}
	}
	
	/**
	 * Thread-safe public method to reset the product list and shopping cart.
	 */
	public synchronized void reset() {
		_productList.clear();
		logger.info("Cleaning the product list");
	}
	
	/**
	 * Thread-safe public method to calculate the total amount due of the shopping cart.
	 * 
	 * @return a double value, as the amount due of the shopping cart.
	 */
	public synchronized double calculateTotal() {
		StringBuilder logmsg = new StringBuilder();
		double total = 0.0;
		for(Map.Entry<String, ProductType> entry : _productList.entrySet()) {
			total += entry.getValue().calculateTotal();
			
			logmsg.delete(0, logmsg.length());
			logmsg.append("Total amount for product type ").append(entry.getKey()).append(": $")
				.append(String.format("%1.2f", entry.getValue().calculateTotal()));
			logger.info(logmsg.toString());
		}
		
		logmsg.delete(0, logmsg.length());
		logmsg.append("Total amount due: $").append(String.format("%1.2f", total));
		logger.info(logmsg.toString());
		
		return total;
	}
	
	/**
	 * Thread-safe public method which returns a formatted String representing the total amount due with exactly 2 decimals.
	 * 
	 * @return a String value, as the formatted amount due with 2 decimals.
	 */
	public synchronized String formattedTotal() {
		return String.format("%1.2f", calculateTotal());
	}
}
