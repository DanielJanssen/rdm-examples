package de.th_koeln.example.shoppingcart.attribute;

import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.UuidAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "OrderId"))
public class OrderId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	public OrderId() { //needed for JPA
		super();
	}

	private OrderId(String aQuantity) {
		super(aQuantity);
	}

	public static OrderId fromValue() {
		return new OrderId(UUID.randomUUID().toString());
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
