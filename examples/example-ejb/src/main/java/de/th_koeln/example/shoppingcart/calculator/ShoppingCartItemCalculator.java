package de.th_koeln.example.shoppingcart.calculator;

import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public interface ShoppingCartItemCalculator {

	public TotalPrice calculate(ShoppingCartItem aShoppingCartItem);
}
