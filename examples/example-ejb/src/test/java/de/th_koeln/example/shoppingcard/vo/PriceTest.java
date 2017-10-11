package de.th_koeln.example.shoppingcard.vo;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.attribute.Amount;
import de.th_koeln.example.shoppingcard.attribute.Currency;

public class PriceTest {

	@Test(expected = IllegalStateException.class)
	public void testPriceBuilder_withoutParams() {
		new PricePerPiece.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testPriceBuilder_withoutPriceValue() {
		new PricePerPiece.Builder().forCurrency("Euro").build();
	}

	@Test(expected = IllegalStateException.class)
	public void testPriceBuilder_withoutCurrency() {
		new PricePerPiece.Builder().withPriceValue(new BigDecimal(5)).build();
	}

	@Test
	public void testPriceBuilder() {
		PricePerPiece actual = new PricePerPiece.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
		assertNotNull(actual);
		assertEquals(Amount.fromValue(new BigDecimal(5)), actual.getAmount());
		assertEquals(Currency.fromValue("Euro"), actual.getCurrency());
	}
}
