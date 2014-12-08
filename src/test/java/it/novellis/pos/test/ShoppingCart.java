package it.novellis.pos.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package-private class representing a shopping cart, used only in tests to
 * facilitate the parsing of the input data.
 * 
 * @author francesco.novellis
 *
 */
class ShoppingCart {
	private List<String> _shoppingItems = null;
	
	/**
	 * Class constructor.
	 * It takes as input the overall string representing the items of the shopping cart and the
	 * separator used to tokenize the elements, in order to populate the actual item list.
	 * 
	 * @param iItems
	 * @param iSeparator
	 */
	ShoppingCart(String iItems, String iSeparator) {
		_shoppingItems = new ArrayList<String>();
		
		if(!iSeparator.isEmpty()) {
			_shoppingItems = Arrays.asList(iItems.split(iSeparator));
		}
		else {
			for(char aChar : iItems.toCharArray())
			{
				_shoppingItems.add(String.valueOf(aChar));
			}
		}
	}
	
	/**
	 * Package-private method which returns the string list representing the items in the shopping cart.
	 * 
	 * @return the string list of the items in the shopping cart
	 */
	List<String> getShoppingItems()
	{
		return _shoppingItems;
	}
}
