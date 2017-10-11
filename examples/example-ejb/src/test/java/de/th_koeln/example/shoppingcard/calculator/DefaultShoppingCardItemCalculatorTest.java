package de.th_koeln.example.shoppingcard.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.entity.Article;
import de.th_koeln.example.shoppingcard.entity.ShoppingCardItem;
import de.th_koeln.example.shoppingcard.vo.PricePerPiece;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

public class DefaultShoppingCardItemCalculatorTest {

	@Test
	public void testCalculate_1() {
		DefaultShoppingCardItemCalculator sut = new DefaultShoppingCardItemCalculator();
		ShoppingCardItem item = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(1).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_5() {
		DefaultShoppingCardItemCalculator sut = new DefaultShoppingCardItemCalculator();
		ShoppingCardItem item = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(25)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
	}

}
