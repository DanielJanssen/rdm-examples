package de.th_koeln.example.shoppingcart.application.service.article;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.th_koeln.example.shoppingcart.domain.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.domain.entity.Article;
import de.th_koeln.example.shoppingcart.domain.entity.Article.Builder;
import de.th_koeln.example.shoppingcart.infrastructure.repository.ArticleRepository;

@Stateless
public class ArticleService {

	@Inject
	ArticleRepository articleRepository;

	public List<Article> getAllArticles() {
		return articleRepository.getAllArticles();
	}

	public Article getArticle(ArticleId anArticleId) {
		return articleRepository.getArticle(anArticleId);
	}

	public void save(Builder anArticle) {
		articleRepository.save(anArticle.build());
	}

	public List<Article> getArticles(ArticleSearchVo aSearchVo) {
		return articleRepository.getArticle(aSearchVo);
	}

}
