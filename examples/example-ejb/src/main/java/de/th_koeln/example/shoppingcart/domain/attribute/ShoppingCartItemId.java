package de.th_koeln.example.shoppingcart.domain.attribute;

import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.UuidAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ShoppingCartItemId"))
public class ShoppingCartItemId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	public ShoppingCartItemId() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	@Deprecated
	private ShoppingCartItemId(String aQuantity) {
		super(aQuantity);
	}

	public static ShoppingCartItemId fromValue() {
		return new ShoppingCartItemId(UUID.randomUUID().toString());
	}

	@Override
	public final boolean equals(Object aObj) { //needed for jpa
		return super.equals(aObj);
	}

	@Override
	public final int hashCode() { //needed for jpa
		return super.hashCode();
	}
}
