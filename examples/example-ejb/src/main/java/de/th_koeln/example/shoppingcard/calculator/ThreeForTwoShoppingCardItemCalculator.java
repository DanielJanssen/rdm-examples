package de.th_koeln.example.shoppingcard.calculator;

import de.th_koeln.example.shoppingcard.attribute.Amount;
import de.th_koeln.example.shoppingcard.attribute.Currency;
import de.th_koeln.example.shoppingcard.attribute.Quantity;
import de.th_koeln.example.shoppingcard.entity.ShoppingCardItem;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

public class ThreeForTwoShoppingCardItemCalculator implements ShoppingCardItemCalculator {

	@Override
	public TotalPrice calculate(ShoppingCardItem aShoppingCardItem) {
		Quantity quantity = aShoppingCardItem.getNumberOfPieces();
		Quantity freeItems = Quantity.fromValue(quantity.getValue() / 3);
		Amount amountPerPiece = aShoppingCardItem.getPricePerPiece().getAmount();
		Quantity reducedQuantity = quantity.reduce(freeItems);
		Currency currency = aShoppingCardItem.getPricePerPiece().getCurrency();
		return new TotalPrice.Builder().withPriceValue(amountPerPiece.multiply(reducedQuantity)).forCurrency(currency).build();
	}
}