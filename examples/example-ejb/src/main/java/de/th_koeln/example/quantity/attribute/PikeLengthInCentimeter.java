package de.th_koeln.example.quantity.attribute;

import de.th_koeln.rdm.attribute.ShortAttribute;

public class PikeLengthInCentimeter extends ShortAttribute {

	private static final long serialVersionUID = 1L;

	private PikeLengthInCentimeter(Short aLengtInCentimeter) {
		super(aLengtInCentimeter);
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

	public static PikeLengthInCentimeter fromValue(Short aLengtInCentimeter) {
		return new PikeLengthInCentimeter(aLengtInCentimeter);
	}

}
