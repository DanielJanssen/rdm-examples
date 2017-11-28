package de.th_koeln.example.shoppingcart.domain.enums;

public enum OrderState {
	// @formatter:off
	NOT_ORDERED("Not Ordered"),
	ORDERED("Ordered"),
	CANCELLED("Cancelled");
	// @formatter:on

	private String label;

	private OrderState(String aLabel) {
		label = aLabel;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public String getLabel() {
		return label;
	}

	public Boolean isOrdered() {
		return this == ORDERED;
	}
}