package de.th_koeln.example.shoppingcard.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.attribute.State;
import de.th_koeln.example.shoppingcard.vo.PricePerPiece;

public class ShoppingCardTest {

	@Test(expected = IllegalArgumentException.class)
	public void testShoppingCardBuilder_withException() {
		new ShoppingCard.Builder().build();
	}

	@Test()
	public void testShoppingCardBuilder_withUserAccount() {
		ShoppingCard shoppingCard = new ShoppingCard.Builder().withUserAccount(new UserAccount()).build();
		assertNotNull(shoppingCard);
	}

	@Test()
	public void testShoppingCardBuilder_withItems() {
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCardItem())
				.addItem(getDummyShoppingCardItem()).build();
		int expected = 2;
		assertNotNull(actual);
		assertEquals(expected, actual.getItems().size());
	}

	@Test
	public void testRemoveItem() {
		ShoppingCardItem firstItem = getDummyShoppingCardItem();
		ShoppingCardItem secondItem = getDummyShoppingCardItem();
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).addItem(firstItem).addItem(secondItem).build();
		actual.removeItem(firstItem);
		int expected = 1;
		assertEquals(expected, actual.getItems().size());
		assertTrue(actual.getItems().contains(secondItem));
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveItem_stillOrdered() {
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCardItem())
				.addItem(getDummyShoppingCardItem()).build();
		actual.order();
		actual.removeItem(getDummyShoppingCardItem());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveItem_notInShoppingCard() {
		ShoppingCardItem item = getDummyShoppingCardItem();
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCardItem()).build();
		actual.removeItem(item);
	}

	@Test
	public void testOrder() {
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCardItem())
				.addItem(getDummyShoppingCardItem()).build();
		actual.order();
		State expected = State.fromValue(State.ORDERED);
		assertEquals(expected, actual.getState());
	}

	@Test(expected = IllegalStateException.class)
	public void testOrder_stillOrdered() {
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCardItem())
				.addItem(getDummyShoppingCardItem()).build();
		actual.order();
		actual.order();
	}

	@Test(expected = IllegalStateException.class)
	public void testOrder_withoutItmes() {
		ShoppingCard actual = new ShoppingCard.Builder().withUserAccount(new UserAccount()).build();
		actual.order();
	}

	private ShoppingCardItem getDummyShoppingCardItem() {
		return new ShoppingCardItem.Builder().withQuantity(1).withArticle(getDummyArticle()).forPricePerPiece(getDummyPrice()).build();
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
	}
}