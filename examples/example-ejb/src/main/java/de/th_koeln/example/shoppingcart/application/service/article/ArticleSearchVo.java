package de.th_koeln.example.shoppingcart.application.service.article;

import de.th_koeln.example.shoppingcart.domain.attribute.ArticleName;
import de.th_koeln.example.shoppingcart.domain.attribute.ArticleNumber;

public class ArticleSearchVo {

	ArticleNumber articleNumber;
	ArticleName articlenName;

	public ArticleNumber getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(ArticleNumber aArticleNumber) {
		articleNumber = aArticleNumber;
	}

	public ArticleName getArticleName() {
		return articlenName;
	}

	public void setArticleName(ArticleName aArticleName) {
		articlenName = aArticleName;
	}

}
