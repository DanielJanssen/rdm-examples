package de.th_koeln.example.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

import de.th_koeln.example.shoppingcart.attribute.Amount;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

public class PricePerPieceEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		Amount amount = Amount.fromValue(BigDecimal.valueOf(Float.parseFloat(text)));
		setValue(new PricePerPiece.Builder().forCurrency("EURO").withAmount(amount).build());
	}

}
