package de.th_koeln.example.shoppingcard.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import de.th_koeln.example.shoppingcard.attribute.ArticleDescription;
import de.th_koeln.example.shoppingcard.attribute.ArticleName;
import de.th_koeln.example.shoppingcard.attribute.ArticleNumber;

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
		Article actual = new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertEquals(ArticleNumber.fromValue(12345), actual.getNumber());
		assertEquals(ArticleName.fromValue("Name"), actual.getName());
		assertEquals(ArticleDescription.fromValue("Description"), actual.getDescription());
	}

}
