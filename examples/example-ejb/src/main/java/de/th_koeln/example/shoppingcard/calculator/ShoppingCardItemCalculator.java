package de.th_koeln.example.shoppingcard.calculator;

import de.th_koeln.example.shoppingcard.entity.ShoppingCardItem;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

// TODO rt57, 11.10.2017: tests!
public interface ShoppingCardItemCalculator {

	public TotalPrice calculate(ShoppingCardItem aShoppingCardItem);
}
