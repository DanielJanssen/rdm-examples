package de.th_koeln.example.shoppingcart.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.calculator.ThreeForTwoShoppingCartItemCalculator;
import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public class ThreeForTwoShoppingCartItemCalculatorTest {

	@Test
	public void testCalculate_1() {
		ThreeForTwoShoppingCartItemCalculator sut = new ThreeForTwoShoppingCartItemCalculator();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(1).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_3() {
		ThreeForTwoShoppingCartItemCalculator sut = new ThreeForTwoShoppingCartItemCalculator();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(3).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(10)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_5() {
		ThreeForTwoShoppingCartItemCalculator sut = new ThreeForTwoShoppingCartItemCalculator();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_6() {
		ThreeForTwoShoppingCartItemCalculator sut = new ThreeForTwoShoppingCartItemCalculator();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(6).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}