package de.th_koeln.example.shoppingcart.domain.vo;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.domain.attribute.Amount;
import de.th_koeln.example.shoppingcart.domain.attribute.Currency;
import de.th_koeln.example.shoppingcart.domain.vo.PricePerPiece;

public class PricePerPieceTest {

	@Test(expected = IllegalStateException.class)
	public void testPricePerPieceBuilder_withoutParams() {
		new PricePerPiece.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testPricePerPieceBuilder_withoutAmount() {
		new PricePerPiece.Builder().forCurrency("Euro").build();
	}

	@Test(expected = IllegalStateException.class)
	public void testPricePerPieceBuilder_withoutCurrency() {
		new PricePerPiece.Builder().withAmount(new BigDecimal(5)).build();
	}

	@Test
	public void testPricePerPieceBuilder() {
		PricePerPiece actual = new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
		assertNotNull(actual);
		assertEquals(Amount.fromValue(new BigDecimal(5)), actual.getAmount());
		assertEquals(Currency.fromValue("Euro"), actual.getCurrency());
	}
}
