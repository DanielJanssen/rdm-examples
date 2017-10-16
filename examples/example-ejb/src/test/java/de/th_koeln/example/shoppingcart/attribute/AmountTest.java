package de.th_koeln.example.shoppingcart.attribute;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class AmountTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFromValue_nullValue() {
		Amount.fromValue(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromValue_valueLowerZero() {
		Amount.fromValue(BigDecimal.valueOf(-1));
	}

	@Test
	public void testFromValue_valueZero() {
		Amount sut = Amount.fromValue(BigDecimal.ZERO);
		BigDecimal expected = BigDecimal.ZERO;
		assertEquals(expected, sut.getValue());
	}

	@Test
	public void testFromValue_valueGreaterZero() {
		Amount sut = Amount.fromValue(BigDecimal.ONE);
		BigDecimal expected = BigDecimal.ONE;
		assertEquals(expected, sut.getValue());
	}

}
