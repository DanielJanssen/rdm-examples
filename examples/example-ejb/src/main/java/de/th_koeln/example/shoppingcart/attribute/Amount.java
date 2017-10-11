package de.th_koeln.example.shoppingcart.attribute;

import java.math.BigDecimal;

import de.th_koeln.rdm.attribute.BigDecimalAttribute;
import de.th_koeln.rdm.attribute.IntegerAttribute;

public class Amount extends BigDecimalAttribute {

	private static final long serialVersionUID = 1L;

	private Amount(BigDecimal aValue) {
		super(aValue);
	}

	// TODO rt57, 11.10.2017: isValid => nicht negativ ?

	public static Amount fromValue(BigDecimal aValue) {
		return new Amount(aValue);
	}

	public Amount multiply(IntegerAttribute anAttribut) {
		return fromValue(getValue().multiply(BigDecimal.valueOf(anAttribut.getValue())));
	}
}
