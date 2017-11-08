package de.th_koeln.example.shoppingcard.bean.additem;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import de.th_koeln.example.event.ActionEvent;
import de.th_koeln.example.shoppingcart.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.service.ArticleService;
import de.th_koeln.example.shoppingcart.service.ShoppingCartService;

@Named
@SessionScoped
public class ShoppingCartAddItemDialogBean implements Serializable {

	private static final String DIALOG_ID = "addItemDialogVar";
	private static final long serialVersionUID = 1L;

	private ShoppingCartItem.Builder shoppingCartItem;
	private List<Article> articles;
	private String selectedArticle;
	private ShoppingCartId shoppingCart;

	@Inject
	ArticleService articleService;

	@Inject
	ShoppingCartService shoppingCartService;

	@Inject
	Event<ActionEvent> event;

	public void init(@Observes AddItemEvent anEvent) {
		shoppingCart = anEvent.getShoppingCartId();
		shoppingCartItem = new ShoppingCartItem.Builder();
		articles = articleService.getAllArticles();
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').show()");
	}

	public ShoppingCartItem.Builder getShoppingCartItem() {
		return shoppingCartItem;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public String getSelectedArticle() {
		return selectedArticle;
	}

	public void setSelectedArticle(String aSelectedArticle) {
		selectedArticle = aSelectedArticle;
	}

	public void save() {
		shoppingCartService.addItemToShoppingCart(shoppingCart, shoppingCartItem, articleService.getArticle(ArticleId.fromValue(selectedArticle)));
		event.fire(new FinishAddItemEvent());
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG_ID + "').hide()");
	}
}