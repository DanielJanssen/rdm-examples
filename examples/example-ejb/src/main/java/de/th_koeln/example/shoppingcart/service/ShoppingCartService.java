package de.th_koeln.example.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.th_koeln.example.shoppingcart.attribute.Currency;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.repository.ShoppingCartRepository;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

@Stateless
public class ShoppingCartService {

	@Inject
	ShoppingCartRepository shoppingCartRepository;

	@Inject
	ArticleService articleService;

	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartRepository.getAllShoppingCarts();
	}

	public ShoppingCart getShoppingCart(ShoppingCartId aShoppingCartId) {
		return shoppingCartRepository.getShoppingCart(aShoppingCartId);
	}

	public ShoppingCart saveShoppingCart(ShoppingCart aShoppingCart) {
		return shoppingCartRepository.save(aShoppingCart);
	}

	public void addItemToShoppingCart(ShoppingCartId aShoppingCartId, ShoppingCartItem.Builder shoppingCartItem, Article anArticle) {
		//	TODO	das setzen hier ist auch nicht optimal
		shoppingCartItem.withArticle(anArticle);
		//// TODO rt57, 07.11.2017: in den artikel!
		shoppingCartItem.forPricePerPiece(new PricePerPiece.Builder().forCurrency(Currency.fromValue("EURO")).withAmount(BigDecimal.ONE).build());

		ShoppingCart shoppingCart = getShoppingCart(aShoppingCartId);
		shoppingCart.addItem(shoppingCartItem.build());

		saveShoppingCart(shoppingCart);
	}
}