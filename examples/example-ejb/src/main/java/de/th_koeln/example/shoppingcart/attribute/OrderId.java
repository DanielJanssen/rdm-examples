package de.th_koeln.example.shoppingcart.attribute;

import java.util.UUID;

import de.th_koeln.rdm.attribute.UuidAttribute;

public class OrderId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	private OrderId(String aQuantity) {
		super(aQuantity);
	}

	//ist from value hier wirklich richtig?
	// einerseits generiert er was zufälliges? lieber .newInstance?
	// andererseits ist es dann einheitlich
	public static OrderId fromValue() {
		return new OrderId(UUID.randomUUID().toString());
	}
}
