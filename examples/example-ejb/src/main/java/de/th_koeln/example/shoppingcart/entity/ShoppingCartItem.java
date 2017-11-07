package de.th_koeln.example.shoppingcart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartItemId;
import de.th_koeln.example.shoppingcart.enums.DiscountShoppingCartItemType;
import de.th_koeln.example.shoppingcart.enums.converter.DiscountShoppingCartItemTypeConverter;
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
		pricePerPiece = aBuilder.getPricePerPiece();
		numberOfPieces = aBuilder.getQuantity();
		article = aBuilder.getArticle();
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
		private PricePerPiece price;
		private Quantity quantity;
		private Article article;
		private DiscountShoppingCartItemType discountType = DiscountShoppingCartItemType.NONE;

		public PricePerPiece getPricePerPiece() {
			return price;
		}

		public Quantity getQuantity() {
			return quantity;
		}

		public Article getArticle() {
			return article;
		}

		public DiscountShoppingCartItemType getDiscountType() {
			return discountType;
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

		public Builder withDiscountType(DiscountShoppingCartItemType aDiscountType) {
			discountType = aDiscountType;
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