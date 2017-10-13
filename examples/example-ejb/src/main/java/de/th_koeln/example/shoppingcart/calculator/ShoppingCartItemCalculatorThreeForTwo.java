package de.th_koeln.example.shoppingcart.calculator;

import de.th_koeln.example.shoppingcart.attribute.Amount;
import de.th_koeln.example.shoppingcart.attribute.Currency;
import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public class ShoppingCartItemCalculatorThreeForTwo implements ShoppingCartItemCalculator {

	@Override
	public TotalPrice calculate(ShoppingCartItem aShoppingCartItem) {
		Quantity quantity = aShoppingCartItem.getNumberOfPieces();
		Quantity freeItems = Quantity.fromValue(quantity.getValue() / 3);
		Quantity reducedQuantity = quantity.reduce(freeItems);
		Amount amountPerPiece = aShoppingCartItem.getPricePerPiece().getAmount();
		Currency currency = aShoppingCartItem.getPricePerPiece().getCurrency();
		return new TotalPrice.Builder().withAmount(amountPerPiece.multiply(reducedQuantity)).forCurrency(currency).build();
	}
}