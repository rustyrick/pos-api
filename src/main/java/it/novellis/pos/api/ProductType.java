package it.novellis.pos.api;

/**
 * Class representing a product type.
 * It contains price informations and allows to define a lot discount for a certain
 * quantity of items, whose count is kept updated. 
 * 
 * @author francesco.novellis
 *
 */
public class ProductType {
	private char _productCode;
	private double _unitPrice;
	private double _lotPrice;
	private int _lotQuantity;
	private int _itemCounter;
	
	/**
	 * Class constructor.
	 * Initializes the price and quantity requirements needed for a volume discount for a
	 * specific product type.
	 * 
	 * @param iCode, char value indicating the product type
	 * @param iUnitPrice, double value indicating the unit price
	 * @param iLotPrice, double value indicating the lot price
	 * @param iLotQuantity, indicating the amount of items needed for the volume discount
	 */
	public ProductType(char iCode, double iUnitPrice, double iLotPrice, int iLotQuantity)
	{
		_productCode = iCode;
		_unitPrice = iUnitPrice;
		_lotPrice = iLotPrice;
		_lotQuantity = iLotQuantity;
		_itemCounter = 0;
	}
	
	/**
	 * Public method to increment by 1 the counter of the items of the specific product type
	 * contained in the shopping cart.
	 */
	public void addItem()
	{
		++_itemCounter;
	}
	
	/**
	 * Public method to reset the counter of the items of the specific product type.
	 */
	public void resetCounter()
	{
		_itemCounter = 0;
	}
	
	/**
	 * Public method to calculate the total amount due for a certain product type, considering volume
	 * discounts, if applicable.
	 * 
	 * @return double value, as the amount due for the specific product type.
	 */
	public double calculateTotal()
	{
		return (_itemCounter / _lotQuantity) * _lotPrice + (_itemCounter % _lotQuantity) * _unitPrice;
	}

}
