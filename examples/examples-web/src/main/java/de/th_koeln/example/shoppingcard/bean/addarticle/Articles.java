package de.th_koeln.example.shoppingcard.bean.addarticle;

import java.util.List;

import de.th_koeln.example.shoppingcart.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.entity.Article;

public class Articles {

	private List<Article> value;

	public Articles(List<Article> aValue) {
		super();
		value = aValue;
	}

	public List<Article> getValue() {
		return value;
	}

	public Article getArticleFor(ArticleId selectedArticleId) {
		for (Article article : value) {
			if (article.getId().isEqualsThan(selectedArticleId)) {
				return article;
			}
		}
		throw new IllegalStateException("Article not found");
	}
}
