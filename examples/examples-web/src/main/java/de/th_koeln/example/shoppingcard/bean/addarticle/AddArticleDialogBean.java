package de.th_koeln.example.shoppingcard.bean.addarticle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.service.article.ArticleService;

@Named
@SessionScoped
public class AddArticleDialogBean implements Serializable {

	private static final String DIALOG_ID = "addArticleDialogVar";
	private static final long serialVersionUID = 1L;

	private Article.Builder article;

	@Inject
	ArticleService articleService;

	public void init(@SuppressWarnings("unused") @Observes AddArticleEvent anEvent) {
		article = new Article.Builder();
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').show()");
	}

	public void save() {
		articleService.save(article);
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').hide()");
	}

	public Article.Builder getArticle() {
		return article;
	}
}
