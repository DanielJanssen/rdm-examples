package de.th_koeln.example.shoppingcart.enums;

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

	public static OrderState encode(String aCode) {
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

	public static String decode(OrderState aOrderStatus) {
		if (aOrderStatus == null) {
			return null;
		}
		return aOrderStatus.getLabel();
	}

	@Override
	public String toString() {
		return name();
	}

	public String getLabel() {
		return label;
	}

	public Boolean isOrdered() {
		return this == ORDERED;
	}
}