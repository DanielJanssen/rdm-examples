package de.th_koeln.example.shoppingcart.domain.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.IntegerAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "Quantity"))
public class Quantity extends IntegerAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected Quantity() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	@Deprecated
	private Quantity(Integer aQuantity) {
		super(aQuantity);
	}

	@Override
	protected Boolean isValid(Integer aValue) {
		if (aValue < 0) {
			return false;
		}
		return true;
	}

	public static Quantity fromValue(Integer aQuantity) {
		return new Quantity(aQuantity);
	}

	public Quantity reduce(Quantity aQuantity) {
		return Quantity.fromValue(getValue() - aQuantity.getValue());
	}

	public Quantity add(Quantity aQuantity) {
		return Quantity.fromValue(getValue() + aQuantity.getValue());
	}

}
