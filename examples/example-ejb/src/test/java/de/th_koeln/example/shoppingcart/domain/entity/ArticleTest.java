package de.th_koeln.example.shoppingcart.domain.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import de.th_koeln.example.shoppingcart.domain.attribute.ArticleDescription;
import de.th_koeln.example.shoppingcart.domain.attribute.ArticleName;
import de.th_koeln.example.shoppingcart.domain.attribute.ArticleNumber;
import de.th_koeln.example.shoppingcart.domain.entity.Article;
import de.th_koeln.example.shoppingcart.domain.vo.PricePerPiece;

public class ArticleTest {

	@Test(expected = IllegalStateException.class)
	public void testArticleBuilder_withoutParams() {
		new Article.Builder().build();
	}

	@Test(expected = IllegalStateException.class)
	public void testArticleBuilder_withoutNumber() {
		new Article.Builder().withName("Name").withDescription("Description").build();
	}

	@Test(expected = IllegalStateException.class)
	public void testArticleBuilder_withoutName() {
		new Article.Builder().withNumber(12345).withDescription("Description").build();
	}

	@Test(expected = IllegalStateException.class)
	public void testArticleBuilder_withoutDescription() {
		new Article.Builder().withNumber(12345).withName("Name").build();
	}

	@Test()
	public void testArticleBuilder() {
		Article actual = new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").forPrice(getDummyPrice()).build();
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertEquals(ArticleNumber.fromValue(12345), actual.getNumber());
		assertEquals(ArticleName.fromValue("Name"), actual.getName());
		assertEquals(ArticleDescription.fromValue("Description"), actual.getDescription());
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}

}
