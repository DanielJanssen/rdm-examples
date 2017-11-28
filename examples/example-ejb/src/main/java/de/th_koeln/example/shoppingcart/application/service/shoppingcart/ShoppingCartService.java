package de.th_koeln.example.shoppingcart.application.service.shoppingcart;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.th_koeln.example.shoppingcart.application.service.article.ArticleService;
import de.th_koeln.example.shoppingcart.domain.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.infrastructure.repository.ShoppingCartRepository;

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