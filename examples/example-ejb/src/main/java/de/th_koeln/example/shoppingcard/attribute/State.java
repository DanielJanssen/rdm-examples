package de.th_koeln.example.shoppingcard.attribute;

import de.th_koeln.rdm.attribute.StringAttribute;

//vll noch enum rausmachen?
public class State extends StringAttribute {

	public static final String NOT_ORDERED = "Not Ordered";
	public static final String ORDERED = "Ordered";

	private State(String aValue) {
		super(aValue);
	}

	@Override
	protected Boolean isValid(String aValue) {
		if (aValue.isEmpty()) {
			return Boolean.FALSE;
		}
		if (aValue.equals(NOT_ORDERED)) {
			return Boolean.TRUE;
		}
		if (aValue.equals(ORDERED)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static State fromValue(String aValue) {
		return new State(aValue);
	}
}
