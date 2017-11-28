package de.th_koeln.example.shoppingcard.bean.searcharticle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import de.th_koeln.example.shoppingcard.bean.addarticle.Articles;
import de.th_koeln.example.shoppingcart.application.service.article.ArticleSearchVo;
import de.th_koeln.example.shoppingcart.application.service.article.ArticleService;

@Named
@SessionScoped
public class SearchArticleDialogBean implements Serializable {

	private static final String DIALOG_ID = "searchArticleDialogVar";
	private static final long serialVersionUID = 1L;

	ArticleSearchVo searchVo;
	Articles result;

	@Inject
	ArticleService articleService;

	public void init(@Observes SearchArticleEvent anEvent) {
		searchVo = new ArticleSearchVo();
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').show()");
	}

	public void close() {
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').hide()");
	}

	public void searchArticles() {
		result = new Articles(articleService.getArticles(searchVo));
	}

	public ArticleSearchVo getSearchVo() {
		return searchVo;
	}

	public Articles getResult() {
		return result;
	}

}
