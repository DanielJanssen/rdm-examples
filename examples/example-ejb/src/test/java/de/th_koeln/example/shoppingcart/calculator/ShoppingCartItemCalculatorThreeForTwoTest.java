package de.th_koeln.example.shoppingcart.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public class ShoppingCartItemCalculatorThreeForTwoTest {

	@Test
	public void testCalculate_1() {
		ShoppingCartItemCalculatorThreeForTwo sut = new ShoppingCartItemCalculatorThreeForTwo();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(1).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_3() {
		ShoppingCartItemCalculatorThreeForTwo sut = new ShoppingCartItemCalculatorThreeForTwo();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(3).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(10)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_5() {
		ShoppingCartItemCalculatorThreeForTwo sut = new ShoppingCartItemCalculatorThreeForTwo();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_6() {
		ShoppingCartItemCalculatorThreeForTwo sut = new ShoppingCartItemCalculatorThreeForTwo();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(6).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").forPrice(getDummyPrice()).build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}