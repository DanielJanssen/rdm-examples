package de.th_koeln.example.shoppingcart.domain.attribute;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.BigDecimalAttribute;
import de.th_koeln.rdm.attribute.IntegerAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "Amount"))
public class Amount extends BigDecimalAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected Amount() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	@Deprecated
	private Amount(BigDecimal aValue) {
		super(aValue);
	}

	@Override
	protected Boolean isValid(BigDecimal aValue) {
		return aValue != null && aValue.compareTo(BigDecimal.ZERO) > -1;
	}

	public static Amount fromValue(BigDecimal aValue) {
		return new Amount(aValue);
	}

	public Amount multiply(IntegerAttribute anAttribut) {
		return fromValue(getValue().multiply(BigDecimal.valueOf(anAttribut.getValue())));
	}

	public Amount add(BigDecimalAttribute anAttribut) {
		return fromValue(getValue().add(anAttribut.getValue()));
	}
}
