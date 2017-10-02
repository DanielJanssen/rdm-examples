package de.th_koeln.example.shoppingcard.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.attribute.Quantity;
import de.th_koeln.example.shoppingcard.vo.Price;

public class ShoppingCardItemTest {

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutParams() {
		new ShoppingCardItem.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutArticle() {
		new ShoppingCardItem.Builder().withQuantity(1).forPrice(getDummyPrice()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutQuantity() {
		new ShoppingCardItem.Builder().withArticle(getDummyArticle()).forPrice(getDummyPrice()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testShoppingCardItemBuilder_withoutPrice() {
		new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(1).build();
	}

	@Test
	public void testShoppingCardItemBuilder() {
		ShoppingCardItem actual = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(1).forPrice(getDummyPrice()).build();
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertNotNull(actual.getArticle());
		assertEquals(Quantity.fromValue(1), actual.getQuantity());
		assertNotNull(actual.getPrice());
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private Price getDummyPrice() {
		return new Price.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
	}
}
