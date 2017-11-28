package de.th_koeln.example.shoppingcart.domain.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.IntegerAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ArticleNumber"))
public class ArticleNumber extends IntegerAttribute {

	private static final long serialVersionUID = 1L;

	protected ArticleNumber() { //needed for JPA
		super();
	}

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
