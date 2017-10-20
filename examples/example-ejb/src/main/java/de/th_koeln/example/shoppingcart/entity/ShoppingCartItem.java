package de.th_koeln.example.shoppingcart.entity;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartItemId;
import de.th_koeln.example.shoppingcart.calculator.ShoppingCartItemCalculator;
import de.th_koeln.example.shoppingcart.calculator.ShoppingCartItemCalculatorDefault;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

@Entity
public class ShoppingCartItem {

	@EmbeddedId
	private ShoppingCartItemId id;
	@Embedded
	private PricePerPiece pricePerPiece;
	@Embedded
	private Quantity numberOfPieces;
	@ManyToOne
	private Article article;
	@Transient
	private ShoppingCartItemCalculator calculator;

	protected ShoppingCartItem() {
		super();
	}

	private ShoppingCartItem(Builder aBuilder) {
		super();
		id = ShoppingCartItemId.fromValue();
		pricePerPiece = aBuilder.getPricePerPiece();
		numberOfPieces = aBuilder.getQuantity();
		article = aBuilder.getArticle();
		calculator = aBuilder.getCalculator();
	}

	public void addNumberOfPieces(Quantity aNumberOfPieces) {
		numberOfPieces = numberOfPieces.add(aNumberOfPieces);
	}

	public void reduceNumberOfPieces(Quantity aNumberOfPieces) {
		numberOfPieces = numberOfPieces.reduce(aNumberOfPieces);
	}

	public TotalPrice getTotalPrice() {
		return calculator.calculate(this);
	}

	public ShoppingCartItemId getId() {
		return id;
	}

	public PricePerPiece getPricePerPiece() {
		return pricePerPiece;
	}

	public Quantity getNumberOfPieces() {
		return numberOfPieces;
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
		ShoppingCartItem other = (ShoppingCartItem) obj;
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
		private PricePerPiece price;
		private Quantity quantity;
		private Article article;
		private ShoppingCartItemCalculator calculator = new ShoppingCartItemCalculatorDefault();

		public PricePerPiece getPricePerPiece() {
			return price;
		}

		public Quantity getQuantity() {
			return quantity;
		}

		public Article getArticle() {
			return article;
		}

		public ShoppingCartItemCalculator getCalculator() {
			return calculator;
		}

		public Builder forPricePerPiece(PricePerPiece aPrice) {
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

		public Builder withCalculator(ShoppingCartItemCalculator aCalculator) {
			calculator = aCalculator;
			return this;
		}

		public ShoppingCartItem build() {
			isValid();
			return new ShoppingCartItem(this);
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