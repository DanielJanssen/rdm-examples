package de.th_koeln.example.quantity.attribute;

import de.th_koeln.rdm.attribute.ShortAttribute;

public class LengthInCentimeter extends ShortAttribute {

	private static final long serialVersionUID = 1L;

	private LengthInCentimeter(Short aQuantity) {
		super(aQuantity);
	}

	@Override
	protected Boolean isValid(Short aValue) {
		if (aValue < 0) {
			return Boolean.FALSE;
		}
		if (aValue > 160) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public static LengthInCentimeter fromValue(Short aQuantity) {
		return new LengthInCentimeter(aQuantity);
	}

}
