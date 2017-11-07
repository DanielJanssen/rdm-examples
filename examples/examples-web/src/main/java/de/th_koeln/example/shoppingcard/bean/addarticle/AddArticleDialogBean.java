package de.th_koeln.example.shoppingcard.bean.addarticle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import de.th_koeln.example.shoppingcart.entity.Article;

@Named
@SessionScoped
public class AddArticleDialogBean implements Serializable {

	private static final String DIALOG = "addArticleDialogVar";
	private static final long serialVersionUID = 1L;

	private Article.Builder article;

	public void init(@SuppressWarnings("unused") @Observes AddArticleEvent anEvent) {
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG + "').show()");
	}

	public void save() {
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG + "').hide()");
	}
}
