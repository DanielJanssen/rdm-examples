package de.th_koeln.example.shoppingcart.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.impl.JPAQuery;

import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.entity.QShoppingCart;
import de.th_koeln.example.shoppingcart.entity.ShoppingCart;

@Stateless
public class ShoppingCartRepository {
	private static final QShoppingCart SHOPPING_CART = QShoppingCart.shoppingCart;

	@PersistenceContext(unitName = "examples-ejb")
	private EntityManager em;

	public List<ShoppingCart> getAllShoppingCarts() {
		return new JPAQuery(em).from(SHOPPING_CART).list(SHOPPING_CART);
	}

	public ShoppingCart getShoppingCart(ShoppingCartId anId) {
		return em.find(ShoppingCart.class, anId);
	}

	public ShoppingCart save(ShoppingCart aShoppingCart) {
		return em.merge(aShoppingCart);
	}
}
