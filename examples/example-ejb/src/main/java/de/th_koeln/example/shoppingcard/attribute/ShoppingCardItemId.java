package de.th_koeln.example.shoppingcard.attribute;

import java.util.UUID;

import de.th_koeln.rdm.attribute.UuidAttribute;

public class ShoppingCardItemId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	private ShoppingCardItemId(String aQuantity) {
		super(aQuantity);
	}

	//ist from value hier wirklich richtig?
	// einerseits generiert er was zufälliges? lieber .newInstance?
	// andererseits ist es dann einheitlich
	public static ShoppingCardItemId fromValue() {
		return new ShoppingCardItemId(UUID.randomUUID().toString());
	}
}
