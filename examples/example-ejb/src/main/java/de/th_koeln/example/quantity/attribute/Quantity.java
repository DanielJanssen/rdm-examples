package de.th_koeln.example.quantity.attribute;

import de.th_koeln.rdm.attribute.IntegerAttribute;

public class Quantity extends IntegerAttribute {

	private static final long serialVersionUID = 1L;

	private Quantity(Integer aQuantity) {
		super(aQuantity);
	}

	@Override
	protected Boolean isValid() {
		if (getValue() < 0) {
			return false;
		}
		return true;
	}

	// TODO rt57, 23.07.2017: was ist mit null als wert? machen solche VOs Sinn?
	public static Quantity fromValue(Integer aQuantity) {
		Quantity quantity = new Quantity(aQuantity);
		if (quantity.isValid()) {
			return quantity;
		}
		throw new IllegalArgumentException("Value " + aQuantity + " is not valid for Quantity ");
	}

}
