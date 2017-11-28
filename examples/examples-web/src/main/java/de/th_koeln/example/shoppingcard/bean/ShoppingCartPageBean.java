package de.th_koeln.example.shoppingcard.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.inject.Named;

import de.th_koeln.example.event.ActionEvent;
import de.th_koeln.example.shoppingcard.bean.addarticle.AddArticleEvent;
import de.th_koeln.example.shoppingcard.bean.additem.AddItemEvent;
import de.th_koeln.example.shoppingcard.bean.additem.FinishAddItemEvent;
import de.th_koeln.example.shoppingcard.bean.searcharticle.SearchArticleEvent;
import de.th_koeln.example.shoppingcart.application.service.shoppingcart.ShoppingCartService;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCart;

@Named
@SessionScoped
public class ShoppingCartPageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ShoppingCart> shoppingCarts;
	@Inject
	Event<ActionEvent> event;

	private ShoppingCart selectedShoppingCart;

	@Inject
	ShoppingCartService service;

	public void getAllShoppingCarts(@Observes FinishAddItemEvent anEvent) {
		shoppingCarts = service.getAllShoppingCarts();
	}

	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void openItemDialog() {
		if (selectedShoppingCart == null) {
			FacesMessage message = new FacesMessage("Select ShoppingCart first");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		event.fire(new AddItemEvent(selectedShoppingCart.getId()));
	}

	public void openArticleDialog() {
		event.fire(new AddArticleEvent());
	}

	public void searchArticle() {
		event.fire(new SearchArticleEvent());
	}

	public ShoppingCart getSelectedShoppingCart() {
		return selectedShoppingCart;
	}

	public void setSelectedShoppingCart(ShoppingCart aSelectedShoppingCart) {
		selectedShoppingCart = aSelectedShoppingCart;
	}

	public void beforePhase(PhaseEvent aEvent) {
		if (shoppingCarts == null) {
			getAllShoppingCarts(null);
		}
	}

}
