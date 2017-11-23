package de.th_koeln.example.quantity.attribute;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuantityTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFromValueIsBelowZero() {
		Quantity.fromValue((short) -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromValueIsGreaterMaximum() {
		Quantity.fromValue((short) 1001);
	}

	@Test
	public void testFromValueIsZero() {
		Quantity actual = Quantity.fromValue((short) 0);
		assertEquals(Short.valueOf((short) 0), actual.getValue());
	}

	@Test
	public void testFromValueIsGreaterThanZero() {

		Quantity actual = Quantity.fromValue((short) 1);

		assertEquals(Short.valueOf((short) 1), actual.getValue());
	}
}
