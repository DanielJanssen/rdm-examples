package de.th_koeln.example.shoppingcard.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.service.ShoppingCartService;

@Named
@RequestScoped
public class ShoppingCartPageBean {

	private ShoppingCart shoppingCart;

	@Inject
	ShoppingCartService service;

	@PostConstruct
	public void init() {
		shoppingCart = service.getAllShoppingCarts().get(0);
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

}
