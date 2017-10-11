package de.th_koeln.example.shoppingcard.attribute;

import de.th_koeln.rdm.attribute.IntegerAttribute;

public class Quantity extends IntegerAttribute {

	private static final long serialVersionUID = 1L;

	private Quantity(Integer aQuantity) {
		super(aQuantity);
	}

	@Override
	protected Boolean isValid(Integer aValue) {
		if (aValue < 0) {
			return false;
		}
		return true;
	}

	public static Quantity fromValue(Integer aQuantity) {
		return new Quantity(aQuantity);
	}

	public Quantity reduce(Quantity aQuantity) {
		return Quantity.fromValue(getValue() - aQuantity.getValue());
	}

	public Quantity add(Quantity aQuantity) {
		return Quantity.fromValue(getValue() + aQuantity.getValue());
	}

}
