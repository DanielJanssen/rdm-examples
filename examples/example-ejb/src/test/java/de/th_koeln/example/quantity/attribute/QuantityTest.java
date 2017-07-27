package de.th_koeln.example.quantity.attribute;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuantityTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFromValueIsBelowZero() {
		Quantity.fromValue(-1);
	}

	@Test
	public void testFromValueIsZero() {
		Quantity actual = Quantity.fromValue(0);
		assertEquals(Integer.valueOf(0), actual.getValue());
	}

	@Test
	public void testFromValueIsGreaterThanZero() {
		Quantity actual = Quantity.fromValue(1);
		assertEquals(Integer.valueOf(1), actual.getValue());
	}

}
