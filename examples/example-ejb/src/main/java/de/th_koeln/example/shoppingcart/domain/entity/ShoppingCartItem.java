package de.th_koeln.example.shoppingcart.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import de.th_koeln.example.shoppingcart.domain.attribute.Quantity;
import de.th_koeln.example.shoppingcart.domain.attribute.ShoppingCartItemId;
import de.th_koeln.example.shoppingcart.domain.enums.DiscountShoppingCartItemType;
import de.th_koeln.example.shoppingcart.domain.vo.PricePerPiece;
import de.th_koeln.example.shoppingcart.domain.vo.TotalPrice;
import de.th_koeln.example.shoppingcart.enums.domain.converter.DiscountShoppingCartItemTypeConverter;

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
	@ManyToOne(cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart;
	@Column
	@Convert(converter = DiscountShoppingCartItemTypeConverter.class)
	private DiscountShoppingCartItemType discountType;

	protected ShoppingCartItem() {
		super();
	}

	private ShoppingCartItem(Builder aBuilder) {
		super();
		id = ShoppingCartItemId.fromValue();
		numberOfPieces = aBuilder.getQuantity();
		article = aBuilder.getArticle();
		pricePerPiece = article.getPrice();
		discountType = aBuilder.getDiscountType();
	}

	public void addNumberOfPieces(Quantity aNumberOfPieces) {
		numberOfPieces = numberOfPieces.add(aNumberOfPieces);
	}

	public void reduceNumberOfPieces(Quantity aNumberOfPieces) {
		numberOfPieces = numberOfPieces.reduce(aNumberOfPieces);
	}

	public void setShoppingCart(ShoppingCart aShoppingCart) {
		shoppingCart = aShoppingCart;
		aShoppingCart.getItems().add(this);
	}

	public TotalPrice getTotalPrice() {
		return discountType.getCalculator().calculate(this);
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

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
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
		private Quantity quantity;
		private Article article;
		private DiscountShoppingCartItemType discountType = DiscountShoppingCartItemType.NONE;

		public Quantity getQuantity() {
			return quantity;
		}

		public Article getArticle() {
			return article;
		}

		public DiscountShoppingCartItemType getDiscountType() {
			return discountType;
		}

		public Builder withQuantity(Integer aQuantity) {
			quantity = Quantity.fromValue(aQuantity);
			return this;
		}

		public Builder withQuantity(Quantity aQuantity) {
			return withQuantity(aQuantity.getValue());

		}

		public Builder withArticle(Article anArticle) {
			article = anArticle;
			return this;
		}

		public Builder withDiscountType(DiscountShoppingCartItemType aDiscountType) {
			discountType = aDiscountType;
			return this;
		}

		public ShoppingCartItem build() {
			isValid();
			return new ShoppingCartItem(this);
		}

		private void isValid() {
			if (quantity == null || quantity.isNullOrEmpty()) {
				throw new IllegalStateException("Quantity has to be set for building a ShoppingCar");
			}
			if (article == null) {
				throw new IllegalStateException("Article has to be set for building a ShoppingCar");
			}
		}
	}
}