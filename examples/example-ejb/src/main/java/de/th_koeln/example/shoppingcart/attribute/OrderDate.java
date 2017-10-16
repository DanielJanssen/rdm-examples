package de.th_koeln.example.shoppingcart.attribute;

import java.util.Date;

import de.th_koeln.rdm.attribute.DateAttribute;

public class OrderDate extends DateAttribute {

	private static final long serialVersionUID = 1L;

	private OrderDate(Date aValue) {
		super(aValue);
	}

	public static OrderDate fromValue(Date aValue) {
		return new OrderDate(aValue);
	}

}
