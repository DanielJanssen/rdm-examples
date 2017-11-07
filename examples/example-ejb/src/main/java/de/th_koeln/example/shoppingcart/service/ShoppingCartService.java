package de.th_koeln.example.shoppingcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.repository.ShoppingCartRepository;

@Stateless
public class ShoppingCartService {

	@Inject
	ShoppingCartRepository shoppingCartRepository;

	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartRepository.getAllShoppingCarts();
	}

	public ShoppingCart getShoppingCart(ShoppingCartId aShoppingCartId) {
		return shoppingCartRepository.getShoppingCart(aShoppingCartId);
	}

	public ShoppingCart saveShoppingCart(ShoppingCart aShoppingCart) {
		return shoppingCartRepository.save(aShoppingCart);
	}
}