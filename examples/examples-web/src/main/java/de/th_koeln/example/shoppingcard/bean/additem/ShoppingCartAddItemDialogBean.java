package de.th_koeln.example.shoppingcard.bean.additem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import de.th_koeln.example.event.ActionEvent;
import de.th_koeln.example.shoppingcart.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.attribute.Currency;
import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.service.ArticleService;
import de.th_koeln.example.shoppingcart.service.ShoppingCartService;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

@Named
@SessionScoped
public class ShoppingCartAddItemDialogBean implements Serializable {

	private static final String DIALOG = "addItemDialogVar";
	private static final long serialVersionUID = 1L;

	private ShoppingCartItem.Builder shoppingCartItem;
	private List<Article> articles;
	private String selectedArticle;
	private ShoppingCart shoppingCart;

	@Inject
	ArticleService articleService;

	@Inject
	ShoppingCartService shoppingCartService;

	@Inject
	Event<ActionEvent> event;

	public void init(@Observes AddItemEvent anEvent) {
		shoppingCart = shoppingCartService.getShoppingCart(anEvent.getShoppingCartId());
		shoppingCartItem = new ShoppingCartItem.Builder();
		articles = articleService.getAllArticles();
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG + "').show()");
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
		// TODO rt57, 07.11.2017: converter und direkt auf dem item arbeiten? aber schöner ist es bei ff:, dann diesen mechanismus mal verstehen...
		//		auch das ganze zusammenbauen vll in den service?
		shoppingCartItem.withArticle(articleService.getArticle(ArticleId.fromValue(selectedArticle)));

		//// TODO rt57, 07.11.2017: in den artikel!
		shoppingCartItem.forPricePerPiece(new PricePerPiece.Builder().forCurrency(Currency.fromValue("EURO")).withAmount(BigDecimal.ONE).build());

		shoppingCart.addItem(shoppingCartItem.build());

		shoppingCart = shoppingCartService.saveShoppingCart(shoppingCart);
		event.fire(new FinishAddItemEvent());
		RequestContext.getCurrentInstance().execute("PF('" + DIALOG + "').hide()");
	}
}