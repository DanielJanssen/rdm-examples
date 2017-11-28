package de.th_koeln.example.shoppingcart.domain.calculator;

import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;

public interface ShoppingCartItemCalculator {

	public TotalPrice calculate(ShoppingCartItem aShoppingCartItem);
}
