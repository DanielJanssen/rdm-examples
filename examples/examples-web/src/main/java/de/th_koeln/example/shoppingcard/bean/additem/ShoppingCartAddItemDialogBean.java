package de.th_koeln.example.shoppingcard.bean.additem;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import de.th_koeln.example.event.ActionEvent;
import de.th_koeln.example.shoppingcard.bean.addarticle.Articles;
import de.th_koeln.example.shoppingcart.application.service.article.ArticleService;
import de.th_koeln.example.shoppingcart.application.service.shoppingcart.ShoppingCartService;
import de.th_koeln.example.shoppingcart.domain.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.domain.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;

@Named
@SessionScoped
public class ShoppingCartAddItemDialogBean implements Serializable {

	private static final String DIALOG_ID = "addItemDialogVar";
	private static final long serialVersionUID = 1L;

	private ShoppingCartItem.Builder shoppingCartItem;
	private Articles articles;
	private ArticleId selectedArticleId;
	private ShoppingCartId shoppingCartId;

	@Inject
	ArticleService articleService;

	@Inject
	ShoppingCartService shoppingCartService;

	@Inject
	Event<ActionEvent> event;

	public void init(@Observes AddItemEvent anEvent) {
		shoppingCartId = anEvent.getShoppingCartId();
		shoppingCartItem = new ShoppingCartItem.Builder();
		articles = new Articles(articleService.getAllArticles());
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').show()");
	}

	public void save() {
		shoppingCartItem.withArticle(articles.getArticleFor(selectedArticleId));

		shoppingCartService.addItemToShoppingCart(shoppingCartId, shoppingCartItem.build());
		event.fire(new FinishAddItemEvent());
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').hide()");
	}

	public ShoppingCartItem.Builder getShoppingCartItem() {
		return shoppingCartItem;
	}

	public Articles getArticles() {
		return articles;
	}

	public ArticleId getSelectedArticleId() {
		return selectedArticleId;
	}

	public void setSelectedArticleId(ArticleId aSelectedArticle) {
		selectedArticleId = aSelectedArticle;
	}

}