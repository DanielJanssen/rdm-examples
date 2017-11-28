package de.th_koeln.example.shoppingcart.domain.calculator;

import java.math.BigDecimal;

import de.th_koeln.example.shoppingcart.domain.attribute.Amount;
import de.th_koeln.example.shoppingcart.domain.attribute.Currency;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCart;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;

public class ShoppingCartCalculatorDiscount implements ShoppingCartCalculator {

	private static final BigDecimal DISCOUNT_IN_PERCENT = BigDecimal.valueOf(0.9);

	@Override
	public TotalPrice calculate(ShoppingCart aShoppingCart) {
		Amount amount = Amount.fromValue(BigDecimal.ZERO);
		Currency currency = null;
		for (ShoppingCartItem item : aShoppingCart.getItems()) {
			amount = amount.add(item.getTotalPrice().getAmount());
			if (currency == null) {
				currency = item.getTotalPrice().getCurrency();
			}
		}
		BigDecimal amountWithDiscount = amount.getValue().multiply(DISCOUNT_IN_PERCENT);
		return new TotalPrice.Builder().withAmount(amountWithDiscount).forCurrency(currency).build();
	}
}
