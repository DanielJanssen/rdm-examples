package de.th_koeln.example.shoppingcart.infrastructure.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

import de.th_koeln.example.shoppingcart.application.service.article.ArticleSearchVo;
import de.th_koeln.example.shoppingcart.domain.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.domain.attribute.ArticleName;
import de.th_koeln.example.shoppingcart.domain.entity.Article;
import de.th_koeln.example.shoppingcart.domain.entity.QArticle;

@Stateless
public class ArticleRepository {
	private static final QArticle ARTICLE = QArticle.article;

	@PersistenceContext(unitName = "examples-ejb")
	private EntityManager em;

	public List<Article> getAllArticles() {
		return new JPAQuery(em).from(ARTICLE).list(ARTICLE);
	}

	public Article getArticle(ArticleId aArticleId) {
		return em.find(Article.class, aArticleId);
	}

	public void save(Article anArticle) {
		em.merge(anArticle);
	}

	public List<Article> getArticle(ArticleSearchVo aSearchVo) {
		BooleanBuilder tempConditions = new BooleanBuilder();
		addArticleName(aSearchVo.getArticleName(), tempConditions);
		return new JPAQuery(em).from(ARTICLE).where(tempConditions).list(ARTICLE);
	}

	private void addArticleName(ArticleName articleName, BooleanBuilder tempConditions) {
		if (articleName != null && !articleName.isNullOrEmpty()) {
			tempConditions.and(ARTICLE.name.eq(articleName));
		}
	}
}