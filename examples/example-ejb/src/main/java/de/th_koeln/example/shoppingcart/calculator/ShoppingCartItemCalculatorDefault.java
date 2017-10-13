package de.th_koeln.example.shoppingcart.calculator;

import de.th_koeln.example.shoppingcart.attribute.Amount;
import de.th_koeln.example.shoppingcart.attribute.Currency;
import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public class ShoppingCartItemCalculatorDefault implements ShoppingCartItemCalculator {

	@Override
	public TotalPrice calculate(ShoppingCartItem aShoppingCartItem) {
		Quantity quantity = aShoppingCartItem.getNumberOfPieces();
		Amount amountPerPiece = aShoppingCartItem.getPricePerPiece().getAmount();
		Currency currency = aShoppingCartItem.getPricePerPiece().getCurrency();
		return new TotalPrice.Builder().withAmount(amountPerPiece.multiply(quantity)).forCurrency(currency).build();
	}
}
