package it.novellis.pos.test;

import it.novellis.pos.api.Terminal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTerminal {
	private Terminal terminal = null;
	
	@Before
	public void setUp()
	{
		terminal = new Terminal();
    	terminal.setPricing('A', 2.00, 7.00, 4);
    	terminal.setPricing('B', 12.00, 12.00, 1);
    	terminal.setPricing('C', 1.25, 6.00, 6);
    	terminal.setPricing('D', 0.10, 0.15, 1);
	}
	
	@After
	public void tearDown()
	{
		terminal.reset();
	}

    @Test
    public void testShoppingCart1() {
    	String shoppingCart = "ABCDABAA";
    	
    	for (int i = 0, n = shoppingCart.length(); i < n; i++) {
    	    char c = shoppingCart.charAt(i);
    	    terminal.scan(c);
    	}
    	
    	double result = terminal.total();
    	
        assertEquals(result, 32.40, 0.0);
    }
    
    @Test
    public void testShoppingCart2() {
    	String shoppingCart = "CCCCCCC";
    	
    	for (int i = 0, n = shoppingCart.length(); i < n; i++) {
    	    char c = shoppingCart.charAt(i);
    	    terminal.scan(c);
    	}
    	
    	double result = terminal.total();
    	
        assertEquals(result, 7.25, 0.0);
    }
    
    @Test
    public void testShoppingCart3() {
    	String shoppingCart = "ABCD";
    	
    	for (int i = 0, n = shoppingCart.length(); i < n; i++) {
    	    char c = shoppingCart.charAt(i);
    	    terminal.scan(c);
    	}
    	
    	double result = terminal.total();
    	
        assertEquals(result, 15.40, 0.0);
    }
}