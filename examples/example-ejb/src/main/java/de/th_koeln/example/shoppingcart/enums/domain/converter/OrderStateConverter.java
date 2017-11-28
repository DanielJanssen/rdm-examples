package de.th_koeln.example.shoppingcart.enums.domain.converter;

import javax.persistence.AttributeConverter;

import de.th_koeln.example.shoppingcart.domain.enums.OrderState;

public class OrderStateConverter implements AttributeConverter<OrderState, String> {

	@Override
	public String convertToDatabaseColumn(OrderState aOrderStatus) {
		if (aOrderStatus == null) {
			return null;
		}
		return aOrderStatus.getLabel();
	}

	@Override
	public OrderState convertToEntityAttribute(String aCode) {
		if (aCode == null) {
			return null;
		}
		for (OrderState tempOrderStatus : OrderState.values()) {
			if (aCode.equals(tempOrderStatus.getLabel())) {
				return tempOrderStatus;
			}
		}
		throw new IllegalArgumentException("State not found. Illegal Code " + aCode);
	}

}