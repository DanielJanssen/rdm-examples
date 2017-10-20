package de.th_koeln.example.shoppingcard.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.service.ShoppingCartService;

@Named
@RequestScoped
public class ShoppingCartPageBean {

	private List<ShoppingCart> shoppingCarts;

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

}
