package de.th_koeln.example.shoppingcart.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.impl.JPAQuery;

import de.th_koeln.example.shoppingcart.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.QArticle;

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
}
