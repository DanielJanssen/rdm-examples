package de.th_koeln.example.shoppingcard.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.th_koeln.example.event.ActionEvent;
import de.th_koeln.example.shoppingcard.bean.additem.AddItemEvent;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.service.ShoppingCartService;

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

	public void init() {
		shoppingCarts = service.getAllShoppingCarts();
	}

	public List<ShoppingCart> getShoppingCarts() {
		if (shoppingCarts == null) {
			init();
		}
		return shoppingCarts;
	}

	public void openItemDialog() {
		event.fire(new AddItemEvent());
	}

	public ShoppingCart getSelectedShoppingCart() {
		return selectedShoppingCart;
	}

	public void setSelectedShoppingCart(ShoppingCart aSelectedShoppingCart) {
		selectedShoppingCart = aSelectedShoppingCart;
	}

}
