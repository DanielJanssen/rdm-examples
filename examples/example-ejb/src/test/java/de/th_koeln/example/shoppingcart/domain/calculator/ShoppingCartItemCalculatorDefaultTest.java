package de.th_koeln.example.shoppingcart.domain.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.domain.calculator.ShoppingCartItemCalculatorDefault;
import de.th_koeln.example.shoppingcart.domain.entity.Article;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.domain.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;

public class ShoppingCartItemCalculatorDefaultTest {

	@Test
	public void testCalculate_1() {
		ShoppingCartItemCalculatorDefault sut = new ShoppingCartItemCalculatorDefault();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(1).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_5() {
		ShoppingCartItemCalculatorDefault sut = new ShoppingCartItemCalculatorDefault();
		ShoppingCartItem item = new ShoppingCartItem.Builder().withArticle(getDummyArticle()).withQuantity(5).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withAmount(new BigDecimal(25)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").forPrice(getDummyPrice()).build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}

}
