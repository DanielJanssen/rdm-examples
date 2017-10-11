package de.th_koeln.example.shoppingcard.calculator;

import de.th_koeln.example.shoppingcard.attribute.Amount;
import de.th_koeln.example.shoppingcard.attribute.Currency;
import de.th_koeln.example.shoppingcard.attribute.Quantity;
import de.th_koeln.example.shoppingcard.entity.ShoppingCardItem;
import de.th_koeln.example.shoppingcard.vo.TotalPrice;

public class DefaultShoppingCardItemCalculator implements ShoppingCardItemCalculator {

	@Override
	public TotalPrice calculate(ShoppingCardItem aShoppingCardItem) {
		Quantity quantity = aShoppingCardItem.getNumberOfPieces();
		Amount amountPerPiece = aShoppingCardItem.getPricePerPiece().getAmount();
		Currency currency = aShoppingCardItem.getPricePerPiece().getCurrency();
		return new TotalPrice.Builder().withPriceValue(amountPerPiece.multiply(quantity)).forCurrency(currency).build();
	}
}
