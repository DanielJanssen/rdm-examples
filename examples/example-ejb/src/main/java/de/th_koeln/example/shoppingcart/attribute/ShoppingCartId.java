package de.th_koeln.example.shoppingcart.attribute;

import java.util.UUID;

import de.th_koeln.rdm.attribute.UuidAttribute;

public class ShoppingCartId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	private ShoppingCartId(String aQuantity) {
		super(aQuantity);
	}

	//ist from value hier wirklich richtig?
	// einerseits generiert er was zufälliges? lieber .newInstance?
	// andererseits ist es dann einheitlich
	public static ShoppingCartId fromValue() {
		return new ShoppingCartId(UUID.randomUUID().toString());
	}
}
