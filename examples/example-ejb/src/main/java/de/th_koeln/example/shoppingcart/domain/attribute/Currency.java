package de.th_koeln.example.shoppingcart.domain.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.StringAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "Currency"))
public class Currency extends StringAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	protected Currency() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	private Currency(String aValue) {
		super(aValue);
	}

	public static Currency fromValue(String aValue) {
		return new Currency(aValue);
	}

}
