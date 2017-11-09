package de.th_koeln.example.shoppingcart.attribute;

import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.UuidAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "UserAccountId"))
public class UserAccountId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	public UserAccountId() { //needed for JPA
		super();
	}

	private UserAccountId(String aQuantity) {
		super(aQuantity);
	}

	public static UserAccountId fromValue() {
		return new UserAccountId(UUID.randomUUID().toString());
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
