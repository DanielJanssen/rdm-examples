package de.th_koeln.example.shoppingcard.attribute;

import de.th_koeln.rdm.attribute.IntegerAttribute;

public class ArticleNumber extends IntegerAttribute {

	private static final long serialVersionUID = 1L;

	private ArticleNumber(Integer aQuantity) {
		super(aQuantity);
	}

	@Override
	protected Boolean isValid(Integer aValue) {
		if (aValue < 0) {
			return false;
		}
		return true;
	}

	public static ArticleNumber fromValue(Integer aQuantity) {
		return new ArticleNumber(aQuantity);
	}

}
