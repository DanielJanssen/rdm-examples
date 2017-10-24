package de.th_koeln.example.shoppingcart.enums.converter;

import javax.persistence.AttributeConverter;

import de.th_koeln.example.shoppingcart.enums.DiscountShoppingCartItemType;

public class DiscountShoppingCartItemTypeConverter implements AttributeConverter<DiscountShoppingCartItemType, String> {

	@Override
	public String convertToDatabaseColumn(DiscountShoppingCartItemType aDiscountShoppingCartType) {
		if (aDiscountShoppingCartType == null) {
			return null;
		}
		return aDiscountShoppingCartType.getLabel();
	}

	@Override
	public DiscountShoppingCartItemType convertToEntityAttribute(String aCode) {
		if (aCode == null) {
			return null;
		}
		for (DiscountShoppingCartItemType discountTypes : DiscountShoppingCartItemType.values()) {
			if (aCode.equals(discountTypes.getLabel())) {
				return discountTypes;
			}
		}
		throw new IllegalArgumentException("DiscountShoppingCartItemType not found. Illegal Code: " + aCode);
	}
}
