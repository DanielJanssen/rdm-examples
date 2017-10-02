package de.th_koeln.example.shoppingcard.entity;

import de.th_koeln.example.shoppingcard.attribute.Quantity;
import de.th_koeln.example.shoppingcard.attribute.ShoppingCardItemId;
import de.th_koeln.example.shoppingcard.vo.Price;

public class ShoppingCardItem {

	private ShoppingCardItemId id;
	private Price price;
	private Quantity quantity;
	private Article article;

	private ShoppingCardItem(Builder aBuilder) {
		super();
		id = ShoppingCardItemId.fromValue();
		price = aBuilder.getPrice();
		quantity = aBuilder.getQuantity();
		article = aBuilder.getArticle();
	}

	//erhöhe quantity

	//reduziere Quantity

	//get gesamtpreis

	//Builder zum erzeugen, alles ist notwendig

	public ShoppingCardItemId getId() {
		return id;
	}

	public Price getPrice() {
		return price;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public Article getArticle() {
		return article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ShoppingCardItem other = (ShoppingCardItem) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public static class Builder {
		private Price price;
		private Quantity quantity;
		private Article article;

		public Price getPrice() {
			return price;
		}

		public Quantity getQuantity() {
			return quantity;
		}

		public Article getArticle() {
			return article;
		}

		public Builder forPrice(Price aPrice) {
			price = aPrice;
			return this;
		}

		public Builder withQuantity(Integer aQuantity) {
			return withQuantity(Quantity.fromValue(aQuantity));
		}

		public Builder withQuantity(Quantity aQuantity) {
			quantity = aQuantity;
			return this;
		}

		public Builder withArticle(Article anArticle) {
			article = anArticle;
			return this;
		}

		public ShoppingCardItem build() {
			isValid();
			return new ShoppingCardItem(this);
		}

		private void isValid() {
			if (price == null) {
				throw new IllegalStateException("Price has to be set for building a ShoppingCar");
			}
			if (quantity == null || quantity.isNullOrEmpty()) {
				throw new IllegalStateException("Quantity has to be set for building a ShoppingCar");
			}
			if (article == null) {
				throw new IllegalStateException("Article has to be set for building a ShoppingCar");
			}
		}
	}
}