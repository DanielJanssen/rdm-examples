package de.th_koeln.example.shoppingcard.vo;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.attribute.Currency;
import de.th_koeln.example.shoppingcard.attribute.PriceValue;

public class PriceTest {

	@Test(expected = IllegalStateException.class)
	public void testPriceBuilder_withoutParams() {
		new Price.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testPriceBuilder_withoutPriceValue() {
		new Price.Builder().forCurrency("Euro").build();
	}

	@Test(expected = IllegalStateException.class)
	public void testPriceBuilder_withoutCurrency() {
		new Price.Builder().withPriceValue(new BigDecimal(5)).build();
	}

	@Test
	public void testPriceBuilder() {
		Price actual = new Price.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
		assertNotNull(actual);
		assertEquals(PriceValue.fromValue(new BigDecimal(5)), actual.getValue());
		assertEquals(Currency.fromValue("Euro"), actual.getCurrency());
	}

}
