package de.th_koeln.example.shoppingcart.domain.enums.converter;

import javax.persistence.AttributeConverter;

import de.th_koeln.example.shoppingcart.domain.enums.DiscountShoppingCartType;

public class DiscountShoppingCartTypeConverter implements AttributeConverter<DiscountShoppingCartType, String> {

	@Override
	public String convertToDatabaseColumn(DiscountShoppingCartType aDiscountShoppingCartType) {
		if (aDiscountShoppingCartType == null) {
			return null;
		}
		return aDiscountShoppingCartType.getLabel();
	}

	@Override
	public DiscountShoppingCartType convertToEntityAttribute(String aCode) {
		if (aCode == null) {
			return null;
		}
		for (DiscountShoppingCartType discountTypes : DiscountShoppingCartType.values()) {
			if (aCode.equals(discountTypes.getLabel())) {
				return discountTypes;
			}
		}
		throw new IllegalArgumentException("DiscountShoppingCartType not found. Illegal Code: " + aCode);
	}
}
