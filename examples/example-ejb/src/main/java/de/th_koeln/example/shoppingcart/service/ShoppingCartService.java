package de.th_koeln.example.shoppingcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.th_koeln.example.shoppingcart.entity.Article;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.entity.UserAccount;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

@Stateless
public class ShoppingCartService {

	// TODO rt57, 18.10.2017: implement => DAO access
	public List<ShoppingCart> getAllShoppingCarts() {
		List<ShoppingCart> shoppingCarts = new ArrayList<>();
		ShoppingCart shoppingCart = new ShoppingCart.Builder().withUserAccount(new UserAccount()).addItem(getDummyShoppingCartItem())
				.addItem(getDummyShoppingCartItem2()).build();
		shoppingCarts.add(shoppingCart);
		return shoppingCarts;
	}

	private ShoppingCartItem getDummyShoppingCartItem() {
		return new ShoppingCartItem.Builder().withQuantity(1).withArticle(getDummyArticle()).forPricePerPiece(getDummyPrice()).build();
	}

	private ShoppingCartItem getDummyShoppingCartItem2() {
		return new ShoppingCartItem.Builder().withQuantity(10).withArticle(getDummyArticle2()).forPricePerPiece(getDummyPrice()).build();
	}

	private Article getDummyArticle() {
		return new Article.Builder().withNumber(12345).withName("Name").withDescription("Description").build();
	}

	private Article getDummyArticle2() {
		return new Article.Builder().withNumber(12346).withName("Name").withDescription("Description").build();
	}

	private PricePerPiece getDummyPrice() {
		return new PricePerPiece.Builder().withAmount(new BigDecimal(5)).forCurrency("Euro").build();
	}
}
