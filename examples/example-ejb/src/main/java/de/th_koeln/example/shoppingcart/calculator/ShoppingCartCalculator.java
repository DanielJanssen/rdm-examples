package de.th_koeln.example.shoppingcart.calculator;

import de.th_koeln.example.shoppingcart.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public interface ShoppingCartCalculator {

	public TotalPrice calculate(ShoppingCart aShoppingCart);
}
