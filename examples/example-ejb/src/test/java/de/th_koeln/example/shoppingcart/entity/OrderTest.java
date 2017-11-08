package de.th_koeln.example.shoppingcart.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

public class OrderTest {

	@Test(expected = IllegalArgumentException.class)
	public void testOrderBuilder_withoutArguments() {
		new Order.Builder().build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOrderBuilder_withoutItems() {
		new Order.Builder().forShoppingCart(new ShoppingCart.Builder().withUserAccount(new UserAccount()).build()).build();
	}

	@Test
	public void testOrderBuilder() {
		Order actual = new Order.Builder().forShoppingCart(getDummyShoppingCart()).build();
		assertNotNull(actual);
	}

	public ShoppingCart getDummyShoppingCart() {
		return new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
	}

	private ShoppingCartItem getDummyShoppingCartItem() {
		return new ShoppingCartItem.Builder().withQuantity(1).withArticle(getDummyArticle()).build();
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").forPrice(getDummyPrice()).build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}

}
