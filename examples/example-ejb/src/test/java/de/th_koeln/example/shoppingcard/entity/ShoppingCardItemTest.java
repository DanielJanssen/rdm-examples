package de.th_koeln.example.shoppingcard.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.attribute.Quantity;
import de.th_koeln.example.shoppingcard.calculator.DefaultShoppingCardItemCalculator;
import de.th_koeln.example.shoppingcard.calculator.ThreeForTwoShoppingCardItemCalculator;
import de.th_koeln.example.shoppingcard.vo.PricePerPiece;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

public class ShoppingCardItemTest {

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutParams() {
		new ShoppingCardItem.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutArticle() {
		new ShoppingCardItem.Builder().withQuantity(1).forPricePerPiece(getDummyPrice()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutQuantity() {
		new ShoppingCardItem.Builder().withArticle(getDummyArticle()).forPricePerPiece(getDummyPrice()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutPrice() {
		new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(1).build();
	}

	@Test
	public void testShoppingCardItemBuilder() {
		ShoppingCardItem actual = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(1).forPricePerPiece(getDummyPrice()).build();
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertNotNull(actual.getArticle());
		assertEquals(Quantity.fromValue(1), actual.getNumberOfPieces());
		assertNotNull(actual.getPricePerPiece());
	}

	@Test
	public void testGetTotal_DefaultCalculator() {
		ShoppingCardItem sut = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice())
				.withCalculator(new DefaultShoppingCardItemCalculator()).build();
		TotalPrice actual = sut.getTotalPrice();
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(25)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTotal_ThreeForTwoCalculator() {
		ShoppingCardItem sut = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice())
				.withCalculator(new ThreeForTwoShoppingCardItemCalculator()).build();
		TotalPrice actual = sut.getTotalPrice();
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
	}

	// TODO rt57, 11.10.2017: teste calculator => bzw verschiedene setzen
}
