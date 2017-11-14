package de.th_koeln.example.shoppingcart.service.shoppingcart;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.repository.ShoppingCartRepository;
import de.th_koeln.example.shoppingcart.service.article.ArticleService;

@Stateless
public class ShoppingCartService {

	@Inject
	ShoppingCartRepository shoppingCartRepository;

	@Inject
	ArticleService articleService;

	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartRepository.getAllShoppingCarts();
	}

	public void addItemToShoppingCart(ShoppingCartId aShoppingCartId, ShoppingCartItem aShoppingCartItem) {
		ShoppingCart shoppingCart = getShoppingCart(aShoppingCartId);
		shoppingCart.addItem(aShoppingCartItem);

		saveShoppingCart(shoppingCart);
	}

	ShoppingCart getShoppingCart(ShoppingCartId aShoppingCartId) {
		return shoppingCartRepository.getShoppingCart(aShoppingCartId);
	}

	ShoppingCart saveShoppingCart(ShoppingCart aShoppingCart) {
		return shoppingCartRepository.save(aShoppingCart);
	}
}