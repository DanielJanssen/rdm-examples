package de.th_koeln.example.shoppingcard.entity;

import org.junit.Test;

public class ShoppingCardTest {

	@Test(expected = IllegalArgumentException.class)
	public void testShoppingCardBuilder_withException() {
		new ShoppingCard.Builder().build();
	}

}
