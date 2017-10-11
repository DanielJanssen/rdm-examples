package de.th_koeln.example.shoppingcard.calculator;

import de.th_koeln.example.shoppingcard.entity.ShoppingCardItem;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

public interface ShoppingCardItemCalculator {

	public TotalPrice calculate(ShoppingCardItem aShoppingCardItem);
}
