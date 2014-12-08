package it.novellis.pos.api;

/**
 * Package-private class representing a product type, to be used only by the Terminal class.
 * It contains price informations and allows to define a lot discount for a certain quantity
 * of items, whose count is kept updated. 
 * 
 * @author francesco.novellis
 *
 */
class ProductType {
	private String _productCode;
	private double _unitPrice;
	private double _lotPrice;
	private int _lotQuantity;
	private int _itemCounter;
	
	/**
	 * Class constructor.
	 * Initializes the price and quantity requirements needed for a volume discount for the
	 * specific product type.
	 * 
	 * @param iCode, String value indicating the product type
	 * @param iUnitPrice, double value indicating the unit price
	 * @param iLotPrice, double value indicating the lot price
	 * @param iLotQuantity, indicating the number of items needed for the volume discount
	 */
	ProductType(String iCode, double iUnitPrice, double iLotPrice, int iLotQuantity) {
		_productCode = iCode;
		_unitPrice = iUnitPrice;
		_lotPrice = iLotPrice;
		_lotQuantity = iLotQuantity;
		_itemCounter = 0;
	}
	
	/**
	 * Package-private method to increment by 1 the item counter for the specific product type.
	 */
	void addItem() {
		++_itemCounter;
	}
	
	/**
	 * Package-private method to reset the counter of the items of the specific product type.
	 */
	void resetCounter() {
		_itemCounter = 0;
	}
	
	/**
	 * Package-private method to calculate the total amount due for a certain product type, considering volume
	 * discounts, if applicable.
	 * 
	 * @return double value, as the amount due for the specific product type.
	 */
	double calculateTotal() {
		return (_itemCounter / _lotQuantity) * _lotPrice + (_itemCounter % _lotQuantity) * _unitPrice;
	}

}
