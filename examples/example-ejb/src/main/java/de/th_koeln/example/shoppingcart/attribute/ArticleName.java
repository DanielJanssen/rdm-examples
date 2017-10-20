package de.th_koeln.example.shoppingcart.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.StringAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ArticleName"))
public class ArticleName extends StringAttribute {

	private static final long serialVersionUID = 1L;

	protected ArticleName() { //needed for JPA
		super();
	}

	private ArticleName(String aValue) {
		super(aValue);
	}

	public static ArticleName fromValue(String aValue) {
		return new ArticleName(aValue);
	}
}
