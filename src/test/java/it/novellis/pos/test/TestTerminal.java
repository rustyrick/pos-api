package it.novellis.pos.test;

import it.novellis.pos.api.Terminal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTerminal {
	private Terminal terminal = null;
	
	@Before
	public void setUp() {
		terminal = new Terminal();
    	terminal.setPricing("A", 2.00, 7.00, 4);
    	terminal.setPricing("B", 12.00, 12.00, 1);
    	terminal.setPricing("C", 1.25, 6.00, 6);
    	terminal.setPricing("D", 0.10, 0.15, 1);
	}
	
	@After
	public void tearDown() {
		terminal.reset();
	}

    @Test
    public void testTerminal1() {
    	String shoppingCart = "ABCDABAA";
    	
    	for (int i = 0, n = shoppingCart.length(); i < n; i++) {
    	    char c = shoppingCart.charAt(i);
    	    terminal.scan(String.valueOf(c));
    	}
    	
    	double result = terminal.calculateTotal();
    	
        assertEquals(result, 32.40, 0.0);
    }
    
    @Test
    public void testTerminal2() {
    	ShoppingCart shoppingCart = new ShoppingCart("CCCCCCC", "");
    	
    	for (String aItem : shoppingCart.getShoppingItems()) {
    	    terminal.scan(aItem);
    	}
    	
    	double result = terminal.calculateTotal();
    	
        assertEquals(result, 7.25, 0.0);
    }
    
    @Test
    public void testTerminal3() {
    	ShoppingCart shoppingCart = new ShoppingCart("ABCD", "");
    	
    	for (String aItem : shoppingCart.getShoppingItems()) {
    	    terminal.scan(aItem);
    	}
    	
    	double result = terminal.calculateTotal();
    	
        assertEquals(result, 15.40, 0.0);
    }
    
    @Test
    public void testTerminalEmptyMap() {
    	terminal.reset();
    	assertEquals("No element in the shopping cart - total amount should be 0.0", 0.0, terminal.calculateTotal(), 0.0);
    }
    
    @Test
    public void testTerminalNegativeUnitPrice() {
    	terminal.reset();
    	terminal.setPricing("Z", -1.0, 2.0, 1);
    	assertEquals("Negative unit price, exception caught - total amount should be 0.0", 0.0, terminal.calculateTotal(), 0.0);
    }
    
    @Test
    public void testTerminalNegativeLotPrice() {
    	terminal.reset();
    	terminal.setPricing("Z", 1.0, -2.0, 1);
    	assertEquals("Negative lot price, exception caught - total amount should be 0.0", 0.0, terminal.calculateTotal(), 0.0);	
    }
    
    @Test
    public void testTerminalNegativeLotQuantity() {
    	terminal.reset();
    	terminal.setPricing("Z", 1.0, 2.0, -1);
    	assertEquals("Negative lot quantity, exception caught - total amount should be 0.0", 0.0, terminal.calculateTotal(), 0.0);
    }
    
    @Test
    public void testTerminalNullLotQuantity() {
    	terminal.reset();
    	terminal.setPricing("Z", 1.0, 2.0, 0);
    	assertEquals("Null lot quantity, exception caught - total amount should be 0.0", 0.0, terminal.calculateTotal(), 0.0);
    }
    
    @Test
    public void testTerminalProductNotFound() {
    	assertFalse("Z not in the shopping list - Terminal.scan should return false", terminal.scan("Z"));
    	assertEquals("Scanned elements not in product list, total amount should be 0.0", 0.0, terminal.calculateTotal(), 0.0);
    }
}