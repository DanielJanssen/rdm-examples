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

	public Article getArticle(ArticleId anArticleId) {
		return em.find(Article.class, anArticleId);
	}

	public Article save(Article anArticle) {
		return em.merge(anArticle);
	}

	public void delete(Article anArticle) {
		em.remove(anArticle);
	}

	public List<Article> getArticle(ArticleSearchVo aSearchVo) {
		BooleanBuilder conditions = new BooleanBuilder();
		addArticleName(aSearchVo.getArticleName(), conditions);
		return new JPAQuery(em).from(ARTICLE).where(conditions).list(ARTICLE);
	}

	private void addArticleName(ArticleName anArticleName, BooleanBuilder someConditions) {
		if (anArticleName != null && !anArticleName.isNullOrEmpty()) {
			someConditions.and(ARTICLE.name.eq(anArticleName));
		}
	}
}
