package de.th_koeln.example.shoppingcart.vo;

import java.math.BigDecimal;

import de.th_koeln.example.shoppingcart.attribute.Amount;
import de.th_koeln.example.shoppingcart.attribute.Currency;

public class PricePerPiece {

	private Amount amount;
	private Currency currency;

	public PricePerPiece(Builder aBuider) {
		amount = aBuider.getValue();
		currency = aBuider.getCurrency();
	}

	public Amount getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PricePerPiece other = (PricePerPiece) obj;
		if (currency == null) {
			if (other.currency != null) {
				return false;
			}
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		return true;
	}

	public static class Builder {
		private Amount value;
		private Currency currency;

		public Amount getValue() {
			return value;
		}

		public Currency getCurrency() {
			return currency;
		}

		public Builder withAmount(BigDecimal aValue) {
			return withAmount(Amount.fromValue(aValue));
		}

		public Builder withAmount(Amount aValue) {
			value = aValue;
			return this;
		}

		public Builder forCurrency(String aCurrency) {
			return forCurrency(Currency.fromValue(aCurrency));
		}

		public Builder forCurrency(Currency aCurrency) {
			currency = aCurrency;
			return this;
		}

		public PricePerPiece build() {
			isValid();
			return new PricePerPiece(this);
		}

		private void isValid() {
			if (value == null) {
				throw new IllegalStateException("PriceValue has to be set for building a Price");
			}
			if (currency == null || currency.isNullOrEmpty()) {
				throw new IllegalStateException("Currency has to be set for building a Price");
			}
		}
	}

}
