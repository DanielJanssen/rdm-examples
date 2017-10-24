package de.th_koeln.example.shoppingcart.enums;

import de.th_koeln.example.shoppingcart.calculator.ShoppingCartCalculator;
import de.th_koeln.example.shoppingcart.calculator.ShoppingCartCalculatorDefault;
import de.th_koeln.example.shoppingcart.calculator.ShoppingCartCalculatorDiscount;

public enum DiscountShoppingCartType {
	// @formatter:off
	NONE("None", new ShoppingCartCalculatorDefault()),
	DISCOUNT("Discount", new ShoppingCartCalculatorDiscount());
	// @formatter:on

	private String label;
	private ShoppingCartCalculator calculator;

	private DiscountShoppingCartType(String aLabel, ShoppingCartCalculator aCalculator) {
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

	public ShoppingCartCalculator getCalculator() {
		return calculator;
	}
}