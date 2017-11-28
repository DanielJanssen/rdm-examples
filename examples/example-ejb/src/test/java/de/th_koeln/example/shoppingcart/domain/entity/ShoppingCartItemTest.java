package de.th_koeln.example.shoppingcart.domain.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.domain.attribute.Quantity;
import de.th_koeln.example.shoppingcart.domain.entity.Article;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.domain.enums.DiscountShoppingCartItemType;
import de.th_koeln.example.shoppingcart.domain.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;

public class ShoppingCartItemTest {

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutParams() {
		new ShoppingCartItem.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutArticle() {
		new ShoppingCartItem.Builder().withQuantity(1).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCartItemBuilder_withoutQuantity() {
		new ShoppingCartItem.Builder().withArticle(getDummyArticle()).build();
	}

	@Test
	public void testShoppingCartItemBuilder() {
		ShoppingCartItem actual = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(1).build();
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertNotNull(actual.getArticle());
		assertEquals(Quantity.fromValue(1), actual.getNumberOfPieces());
		assertNotNull(actual.getPricePerPiece());
	}

	@Test
	public void testGetTotal_DefaultCalculator() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).withDiscountType(DiscountShoppingCartItemType.NONE)
				.build();
		TotalPrice actual = sut.getTotalPrice();
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(25)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTotal_ThreeForTwoCalculator() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5)
				.withDiscountType(DiscountShoppingCartItemType.THREE_FOR_TWO).build();
		TotalPrice actual = sut.getTotalPrice();
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testReduceNumberOfPieces() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).build();
		sut.reduceNumberOfPieces(Quantity.fromValue(2));
		Quantity expected = Quantity.fromValue(3);
		Quantity actual = sut.getNumberOfPieces();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddNumberOfPieces() {
		ShoppingCartItem sut = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).build();
		sut.addNumberOfPieces(Quantity.fromValue(2));
		Quantity expected = Quantity.fromValue(7);
		Quantity actual = sut.getNumberOfPieces();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").forPrice(getDummyPrice()).build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}