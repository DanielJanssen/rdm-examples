package de.th_koeln.example.shoppingcart.domain.calculator;

import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;

public interface ShoppingCartCalculator {

	public TotalPrice calculate(ShoppingCart aShoppingCart);
}
