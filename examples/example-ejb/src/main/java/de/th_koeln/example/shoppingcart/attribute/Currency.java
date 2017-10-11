package de.th_koeln.example.shoppingcart.attribute;

import de.th_koeln.rdm.attribute.StringAttribute;

public class Currency extends StringAttribute {

	private static final long serialVersionUID = 1L;

	private Currency(String aValue) {
		super(aValue);
	}

	public static Currency fromValue(String aValue) {
		return new Currency(aValue);
	}

}
