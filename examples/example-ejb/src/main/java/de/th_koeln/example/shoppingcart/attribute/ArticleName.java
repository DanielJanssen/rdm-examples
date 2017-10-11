package de.th_koeln.example.shoppingcart.attribute;

import de.th_koeln.rdm.attribute.StringAttribute;

public class ArticleName extends StringAttribute {

	private static final long serialVersionUID = 1L;

	private ArticleName(String aValue) {
		super(aValue);
	}

	public static ArticleName fromValue(String aValue) {
		return new ArticleName(aValue);
	}
}
