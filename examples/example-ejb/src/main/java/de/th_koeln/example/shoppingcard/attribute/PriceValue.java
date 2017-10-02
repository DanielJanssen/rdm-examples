package de.th_koeln.example.shoppingcard.attribute;

import java.math.BigDecimal;

import de.th_koeln.rdm.attribute.BigDecimalAttribute;

public class PriceValue extends BigDecimalAttribute {

	private static final long serialVersionUID = 1L;

	private PriceValue(BigDecimal aValue) {
		super(aValue);
	}

	public static PriceValue fromValue(BigDecimal aValue) {
		return new PriceValue(aValue);
	}
}
