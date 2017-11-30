package de.th_koeln.example.shoppingcart.domain.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.StringAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ArticleName"))
public class ArticleName extends StringAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected ArticleName() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	@Deprecated
	private ArticleName(String aValue) {
		super(aValue);
	}

	public static ArticleName fromValue(String aValue) {
		return new ArticleName(aValue);
	}
}
