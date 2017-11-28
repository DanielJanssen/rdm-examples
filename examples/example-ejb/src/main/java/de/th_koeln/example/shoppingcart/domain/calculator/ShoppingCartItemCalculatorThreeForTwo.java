package de.th_koeln.example.shoppingcart.domain.calculator;

import de.th_koeln.example.shoppingcart.domain.attribute.Amount;
import de.th_koeln.example.shoppingcart.domain.attribute.Currency;
import de.th_koeln.example.shoppingcart.domain.attribute.Quantity;
import de.th_koeln.example.shoppingcart.domain.entity.ShoppingCartItem;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;

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