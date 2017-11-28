package de.th_koeln.example.shoppingcart.domain.attribute;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.DateAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "OrderDate"))
public class OrderDate extends DateAttribute {

	private static final long serialVersionUID = 1L;

	protected OrderDate() { //needed for JPA
		super();
	}

	private OrderDate(Date aValue) {
		super(aValue);
	}

	public static OrderDate fromValue(Date aValue) {
		return new OrderDate(aValue);
	}

}
