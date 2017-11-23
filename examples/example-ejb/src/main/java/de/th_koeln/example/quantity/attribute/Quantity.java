package de.th_koeln.example.quantity.attribute;

import de.th_koeln.rdm.attribute.ShortAttribute;

public class Quantity extends ShortAttribute {

	private static final long serialVersionUID = 1L;

	private Quantity(Short aQuantity) {
		super(aQuantity);
	}

	@Override
	protected Boolean isValid(Short aValue) {
		if (aValue < 0) {
			return Boolean.FALSE;
		}
		if (aValue > 1000) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public static Quantity fromValue(Short aQuantity) {
		return new Quantity(aQuantity);
	}

}
