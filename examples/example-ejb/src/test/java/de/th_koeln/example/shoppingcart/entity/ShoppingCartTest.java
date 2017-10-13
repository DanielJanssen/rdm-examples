package de.th_koeln.example.shoppingcart.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.attribute.ArticleNumber;
import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.enums.State;
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
				.addItem(getDummyShoppingCartItem2()).build();
		int expected = 2;
		assertNotNull(actual);
		assertEquals(expected, actual.getItems().size());
	}

	@Test
	public void testRemoveItem() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem2()).build();
		actual.removeItem(getDummyShoppingCartItem());
		int expectedItemSize = 1;
		ArticleNumber expectedArticleNumber = ArticleNumber.fromValue(12346);
		assertEquals(expectedItemSize, actual.getItems().size());
		assertEquals(expectedArticleNumber, actual.getItems().get(0).getArticle().getNumber());
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveItem_stillOrdered() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).addItem(getDummyShoppingCartItem())
				.build();
		sut.order();
		sut.removeItem(getDummyShoppingCartItem());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveItem_notInShoppingCart() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		sut.removeItem(getDummyShoppingCartItem2());
	}

	@Test(expected = IllegalStateException.class)
	public void testAddItem_stillOrdered() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).addItem(getDummyShoppingCartItem())
				.build();
		sut.order();
		sut.addItem(getDummyShoppingCartItem());
	}

	@Test
	public void testAddItem_unknownItem() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		sut.addItem(getDummyShoppingCartItem2());
		int expected = 2;
		assertEquals(expected, sut.getItems().size());
	}

	@Test
	public void testAddItem_knownItem() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		sut.addItem(getDummyShoppingCartItem());
		int expected = 1;
		assertEquals(expected, sut.getItems().size());
	}

	@Test(expected = IllegalStateException.class)
	public void testReduceNumberOfPieces_stillOrdered() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).addItem(getDummyShoppingCartItem())
				.build();
		sut.order();
		sut.reduceNumberOfPieces(getDummyShoppingCartItem(), Quantity.fromValue(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReduceNumberOfPieces_unknownItem() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		sut.reduceNumberOfPieces(getDummyShoppingCartItem2(), Quantity.fromValue(2));
	}

	@Test
	public void testReduceNumberOfPieces_quantityBelower0() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		sut.reduceNumberOfPieces(getDummyShoppingCartItem(), Quantity.fromValue(10));
		int expected = 0;
		assertEquals(expected, sut.getItems().size());
	}

	@Test
	public void testReduceNumberOfPieces_quantityIs0() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem()).build();
		sut.reduceNumberOfPieces(getDummyShoppingCartItem(), Quantity.fromValue(1));
		int expected = 0;
		assertEquals(expected, sut.getItems().size());
	}

	@Test
	public void testReduceNumberOfPieces_quantityIsGreater0() {
		ShoppingCart sut = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem2()).build();
		sut.reduceNumberOfPieces(getDummyShoppingCartItem2(), Quantity.fromValue(1));
		int expected = 1;
		Quantity expectedNumberOfPieces = Quantity.fromValue(9);
		assertEquals(expected, sut.getItems().size());
		assertEquals(expectedNumberOfPieces, sut.getItems().get(0).getNumberOfPieces());
	}

	@Test
	public void testOrder() {
		ShoppingCart actual = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem()).build();
		actual.order();
		State expected = State.ORDERED;
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

	private ShoppingCartItem getDummyShoppingCartItem2() {
		return new ShoppingCartItem.Builder().withQuantity(10).withArticle(getDummyArticle2()).forPricePerPiece(getDummyPrice()).build();
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private Article getDummyArticle2() {
		return new Article.Builder().withNumber(12346).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}