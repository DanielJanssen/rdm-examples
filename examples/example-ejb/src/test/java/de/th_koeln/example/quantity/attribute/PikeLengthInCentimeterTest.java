package de.th_koeln.example.quantity.attribute;

import static org.junit.Assert.*;

import org.junit.Test;

public class PikeLengthInCentimeterTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFromValueIsBelowZero() {
		PikeLengthInCentimeter.fromValue((short) -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromValueIsGreaterMaximum() {
		PikeLengthInCentimeter.fromValue((short) 161);
	}

	public void testFromValueIsZero() {
		PikeLengthInCentimeter actual = PikeLengthInCentimeter.fromValue((short) 0);
		assertEquals(Short.valueOf((short) 0), actual.getValue());
	}

	@Test
	public void testFromValueIsGreaterThanZero() {

		PikeLengthInCentimeter actual = PikeLengthInCentimeter.fromValue((short) 61);

		assertEquals(Short.valueOf((short) 61), actual.getValue());
	}
}
