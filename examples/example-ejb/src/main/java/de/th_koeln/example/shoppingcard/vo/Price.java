package de.th_koeln.example.shoppingcard.vo;

import java.math.BigDecimal;

import de.th_koeln.example.shoppingcard.attribute.Currency;
import de.th_koeln.example.shoppingcard.attribute.PriceValue;

// TODO rt57, 02.10.2017: price implementieren
public class Price {

	private PriceValue value;
	private Currency currency;

	public Price(Builder aBuider) {
		value = aBuider.getValue();
		currency = aBuider.getCurrency();
	}

	public PriceValue getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}

	public static class Builder {
		private PriceValue value;
		private Currency currency;

		public PriceValue getValue() {
			return value;
		}

		public Currency getCurrency() {
			return currency;
		}

		public Builder withPriceValue(BigDecimal aValue) {
			return withPriceValue(PriceValue.fromValue(aValue));
		}

		public Builder withPriceValue(PriceValue aValue) {
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

		public Price build() {
			isValid();
			return new Price(this);
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
