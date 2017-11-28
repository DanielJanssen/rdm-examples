package de.th_koeln.example.shoppingcart.domain.attribute;

import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.UuidAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ShoppingCartId"))
public class ShoppingCartId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	public ShoppingCartId() { //needed for JPA
		super();
	}

	private ShoppingCartId(String aQuantity) {
		super(aQuantity);
	}

	//ist from value hier wirklich richtig?
	// einerseits generiert er was zufälliges? lieber .newInstance?
	// andererseits ist es dann einheitlich
	public static ShoppingCartId fromValue() {
		return new ShoppingCartId(UUID.randomUUID().toString());
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
