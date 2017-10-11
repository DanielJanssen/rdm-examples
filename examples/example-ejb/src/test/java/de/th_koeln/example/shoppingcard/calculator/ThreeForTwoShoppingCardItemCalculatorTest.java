package de.th_koeln.example.shoppingcard.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.entity.Article;
import de.th_koeln.example.shoppingcard.entity.ShoppingCardItem;
import de.th_koeln.example.shoppingcard.vo.PricePerPiece;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

public class ThreeForTwoShoppingCardItemCalculatorTest {

	@Test
	public void testCalculate_1() {
		ThreeForTwoShoppingCardItemCalculator sut = new ThreeForTwoShoppingCardItemCalculator();
		ShoppingCardItem item = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(1).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_3() {
		ThreeForTwoShoppingCardItemCalculator sut = new ThreeForTwoShoppingCardItemCalculator();
		ShoppingCardItem item = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(3).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(10)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_5() {
		ThreeForTwoShoppingCardItemCalculator sut = new ThreeForTwoShoppingCardItemCalculator();
		ShoppingCardItem item = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculate_6() {
		ThreeForTwoShoppingCardItemCalculator sut = new ThreeForTwoShoppingCardItemCalculator();
		ShoppingCardItem item = new ShoppingCardItem.Builder().withArticle(getDummyArticle()).withQuantity(5).forPricePerPiece(getDummyPrice()).build();

		TotalPrice actual = sut.calculate(item);
		TotalPrice expected = new TotalPrice.Builder().withPriceValue(new BigDecimal(20)).forCurrency("Euro").build();
		assertEquals(expected, actual);
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withPriceValue(new BigDecimal(5)).forCurrency("Euro").build();
	}
}