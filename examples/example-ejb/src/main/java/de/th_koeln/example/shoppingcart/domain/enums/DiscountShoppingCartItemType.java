package de.th_koeln.example.shoppingcart.domain.enums;

import de.th_koeln.example.shoppingcart.domain.calculator.ShoppingCartItemCalculator;
import de.th_koeln.example.shoppingcart.domain.calculator.ShoppingCartItemCalculatorDefault;
import de.th_koeln.example.shoppingcart.domain.calculator.ShoppingCartItemCalculatorThreeForTwo;

public enum DiscountShoppingCartItemType {
	// @formatter:off
	NONE("None", new ShoppingCartItemCalculatorDefault()),
	THREE_FOR_TWO("Three for Two", new ShoppingCartItemCalculatorThreeForTwo());
	// @formatter:on

	private String label;
	private ShoppingCartItemCalculator calculator;

	private DiscountShoppingCartItemType(String aLabel, ShoppingCartItemCalculator aCalculator) {
		label = aLabel;
		calculator = aCalculator;
	}

	@Override
	public String toString() {
		return name();
	}

	public String getLabel() {
		return label;
	}

	public ShoppingCartItemCalculator getCalculator() {
		return calculator;
	}
}