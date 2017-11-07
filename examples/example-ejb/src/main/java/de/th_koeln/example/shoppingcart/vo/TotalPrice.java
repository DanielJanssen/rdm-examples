package de.th_koeln.example.shoppingcart.vo;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import de.th_koeln.example.shoppingcart.attribute.Amount;
import de.th_koeln.example.shoppingcart.attribute.Currency;

@Embeddable
public class TotalPrice {

	@Embedded
	private Amount amount;
	@Embedded
	private Currency currency;

	protected TotalPrice() {
		super();
	}

	private TotalPrice(Builder aBuider) {
		super();
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
	public String toString() {
		return amount.toString() + " " + currency.toString();
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
		TotalPrice other = (TotalPrice) obj;
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
			value = Amount.fromValue(aValue);
			return this;
		}

		public Builder withAmount(Amount aValue) {
			return withAmount(aValue.getValue());
		}

		public Builder forCurrency(String aCurrency) {
			currency = Currency.fromValue(aCurrency);
			return this;
		}

		public Builder forCurrency(Currency aCurrency) {
			return forCurrency(aCurrency.getValue());
		}

		public TotalPrice build() {
			isValid();
			return new TotalPrice(this);
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
