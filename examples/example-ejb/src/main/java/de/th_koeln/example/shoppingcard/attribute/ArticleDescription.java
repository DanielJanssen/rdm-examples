package de.th_koeln.example.shoppingcard.attribute;

import de.th_koeln.rdm.attribute.StringAttribute;

public class ArticleDescription extends StringAttribute {

	private static final long serialVersionUID = 1L;

	private ArticleDescription(String aValue) {
		super(aValue);
	}

	public static ArticleDescription fromValue(String aValue) {
		return new ArticleDescription(aValue);
	}
}
