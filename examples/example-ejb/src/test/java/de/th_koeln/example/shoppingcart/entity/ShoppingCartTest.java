package de.th_koeln.example.shoppingcart.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.attribute.State;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

public class ShoppingCartTest {

	@Test(expected = IllegalArgumentException.class)
	public void testShoppingCartBuilder_withException() {
		new ShoppingCart.Builder().build();
	}

	@Test()
	public void testShoppingCartBuilder_withUserAccount() {
		ShoppingCart ShoppingCart = new ShoppingCart.Builder().withUserAccount(new UserAccount()).build();
		assertNotNull(ShoppingCart);
	}

	@Test()
	public void testShoppingCartBuilder_withItems() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem()).build();
		int expected = 2;
		assertNotNull(actual);
		assertEquals(expected, actual.getItems().size());
	}

	@Test
	public void testRemoveItem() {
		ShoppingCartItem firstItem = getDummyShoppingCartItem();
		ShoppingCartItem secondItem = getDummyShoppingCartItem();
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(firstItem).addItem(secondItem).build();
		actual.removeItem(firstItem);
		int expected = 1;
		assertEquals(expected, actual.getItems().size());
		assertTrue(actual.getItems().contains(secondItem));
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveItem_stillOrdered() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem()).build();
		actual.order();
		actual.removeItem(getDummyShoppingCartItem());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveItem_notInShoppingCart() {
		ShoppingCartItem item = getDummyShoppingCartItem();
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		actual.removeItem(item);
	}

	@Test
	public void testOrder() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem()).build();
		actual.order();
		State expected = State.fromValue(State.ORDERED);
		assertEquals(expected, actual.getState());
	}

	@Test(expected = IllegalStateException.class)
	public void testOrder_stillOrdered() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem()).build();
		actual.order();
		actual.order();
	}

	@Test(expected = IllegalStateException.class)
	public void testOrder_withoutItmes() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).build();
		actual.order();
	}

	private ShoppingCartItem getDummyShoppingCartItem() {
		return new ShoppingCartItem.Builder().withQuantity(1).withArticle(getDummyArticle()).forPricePerPiece(getDummyPrice()).build();
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}