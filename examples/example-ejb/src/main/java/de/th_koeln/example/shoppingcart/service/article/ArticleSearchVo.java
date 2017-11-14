package de.th_koeln.example.shoppingcart.service.article;

import de.th_koeln.example.shoppingcart.attribute.ArticleName;
import de.th_koeln.example.shoppingcart.attribute.ArticleNumber;

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
