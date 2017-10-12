package de.th_koeln.example.shoppingcart.enums;

public enum State {
	// @formatter:off
	NOT_ORDERED("Not Ordered"),
	ORDERED("Ordered");
	// @formatter:on

	private String label;

	private State(String aLabel) {
		label = aLabel;
	}

	public static State encode(String aCode) {
		if (aCode == null) {
			return null;
		}
		for (State tempOrderStatus : State.values()) {
			if (aCode.equals(tempOrderStatus.getLabel())) {
				return tempOrderStatus;
			}
		}
		throw new IllegalArgumentException("State not found. Illegal Code " + aCode);
	}

	public static String decode(State aOrderStatus) {
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
