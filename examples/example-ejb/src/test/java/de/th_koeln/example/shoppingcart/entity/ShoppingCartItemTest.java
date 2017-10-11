package de.th_koeln.example.shoppingcart.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.calculator.DefaultShoppingCartItemCalculator;
import de.th_koeln.example.shoppingcart.calculator.ThreeForTwoShoppingCartItemCalculator;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public class ShoppingCartItemTest {

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutParams() {
		new ShoppingCartItem.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutArticle() {
		new ShoppingCartItem.Builder().withQuantity(1).forPricePerPiece(getDummyPrice()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutQuantity() {
		new ShoppingCartItem.Builder().withArticle(getDummyArticle()).forPricePerPiece(getDummyPrice()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutPrice() {
		new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(1).build();
	}

	@Test
	public void testShoppingCartItemBuilder() {
		ShoppingCartItem actual = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(1).forPricePerPiece(getDummyPrice()).build();
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertNotNull(actual.getArticle());
		assertEquals(Quantity.fromValue(1), actual.getNumberOfPieces());
		assertNotNull(actual.getPricePerPiece());
	}

	@Test
	public void testGetTotal_DefaultCalculator() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice())
				.withCalculator(new DefaultShoppingCartItemCalculator()).build();
		TotalPrice actual = sut.getTotalPrice();
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(25)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTotal_ThreeForTwoCalculator() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice())
				.withCalculator(new ThreeForTwoShoppingCartItemCalculator()).build();
		TotalPrice actual = sut.getTotalPrice();
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testReduceNumberOfPieces() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice()).build();
		sut.reduceNumberOfPieces(Quantity.fromValue(2));
		Quantity expected = Quantity.fromValue(3);
		Quantity actual = sut.getNumberOfPieces();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddNumberOfPieces() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice()).build();
		sut.addNumberOfPieces(Quantity.fromValue(2));
		Quantity expected = Quantity.fromValue(7);
		Quantity actual = sut.getNumberOfPieces();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}