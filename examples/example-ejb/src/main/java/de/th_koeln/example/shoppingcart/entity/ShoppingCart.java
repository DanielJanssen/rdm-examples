package de.th_koeln.example.shoppingcart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.enums.DiscountShoppingCartType;
import de.th_koeln.example.shoppingcart.enums.OrderState;
import de.th_koeln.example.shoppingcart.enums.converter.DiscountShoppingCartTypeConverter;
import de.th_koeln.example.shoppingcart.enums.converter.OrderStateConverter;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

@Entity
public class ShoppingCart {

	@EmbeddedId
	private ShoppingCartId id;
	@ManyToOne
	private UserAccount userAccount;
	@Column
	@Convert(converter = OrderStateConverter.class)
	private OrderState state;
	@OneToMany(mappedBy = "shoppingCart", targetEntity = ShoppingCartItem.class, fetch = FetchType.EAGER)
	private List<ShoppingCartItem> items;
	@OneToOne
	private Order order;
	@Column
	@Convert(converter = DiscountShoppingCartTypeConverter.class)
	private DiscountShoppingCartType discountType;

	protected ShoppingCart() {
		super();
	}

	private ShoppingCart(Builder aBuilder) {
		super();
		id = ShoppingCartId.fromValue();
		state = OrderState.NOT_ORDERED;
		userAccount = aBuilder.getUserAccount();
		items = aBuilder.getItems();
		discountType = aBuilder.geDiscountShoppingCartType();
	}

	//was ist wenn sich der price des Artikels geändert hat?
	// TODO rt57, 13.10.2017: gleiche currency?
	public void addItem(ShoppingCartItem anItem) {
		if (!isOrdered()) {
			if (containsItem(anItem)) {
				ShoppingCartItem existingItem = getItem(anItem);
				existingItem.addNumberOfPieces(anItem.getNumberOfPieces());
			} else {
				items.add(anItem);
			}
		} else {
			throw new IllegalStateException("You cannot remove an Item if the ShoppingCart is still ordered");
		}
	}

	public void removeItem(ShoppingCartItem anItem) {
		if (!isOrdered()) {
			if (containsItem(anItem)) {
				ShoppingCartItem removeItem = getItem(anItem);
				items.remove(removeItem);
			} else {
				throw new IllegalArgumentException("Your ShoppingCart does not contain the requested Item " + anItem);
			}
		} else {
			throw new IllegalStateException("You cannot remove an Item if the ShoppingCart is still ordered");
		}
	}

	public void reduceNumberOfPieces(ShoppingCartItem anItem, Quantity aReducableNumberOfPieces) {
		if (!isOrdered()) {
			if (containsItem(anItem)) {
				ShoppingCartItem reducableItem = getItem(anItem);
				if (aReducableNumberOfPieces.isGreaterThan(reducableItem.getNumberOfPieces()) || aReducableNumberOfPieces.equals(anItem.getNumberOfPieces())) {
					removeItem(reducableItem);
				} else {
					reducableItem.reduceNumberOfPieces(aReducableNumberOfPieces);
				}
			} else {
				throw new IllegalArgumentException("Your ShoppingCart does not contain the requested Item " + anItem);
			}
		} else {
			throw new IllegalStateException("You cannot remove an Item if the ShoppingCart is still ordered");
		}
	}

	public void order() {
		if (!isOrdered()) {
			if (!items.isEmpty()) {
				order = new Order.Builder().forShoppingCart(this).build();
				state = OrderState.ORDERED;
			} else {
				throw new IllegalStateException("ShoppingCart cannot be ordered, because it does not contain any ShoppingCartItems");
			}
		} else {
			throw new IllegalStateException("ShoppingCart is still ordered");
		}
	}

	private Boolean containsItem(ShoppingCartItem anItem) {
		return getItem(anItem) != null;
	}

	private ShoppingCartItem getItem(ShoppingCartItem anItem) {
		for (ShoppingCartItem item : items) {
			if (item.getArticle().getNumber().equals(anItem.getArticle().getNumber())) {
				return item;
			}
		}
		return null;
	}

	public TotalPrice getTotal() {
		if (isOrdered()) {
			return order.getTotalPrice();
		}
		return discountType.getCalculator().calculate(this);
	}

	private Boolean isOrdered() {
		return state.isOrdered();
	}

	// TODO wirklich die get-methode, dann kann man darauf ja wieder verändern? oder wirklich nur die anwendungsfälle?
	// oder eine neue liste zurückgeben
	//protected ist ganz gut für die tests... gucken, was wir später auf der GUI damit machen
	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public OrderState getState() {
		return state;
	}

	public Order getOrder() {
		return order;
	}

	public UserAccount getUserAccount() {
		return userAccount;
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
		ShoppingCart other = (ShoppingCart) obj;
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
		private UserAccount userAccount;
		private List<ShoppingCartItem> items = new ArrayList<>();
		private DiscountShoppingCartType discountType = DiscountShoppingCartType.NONE;

		public List<ShoppingCartItem> getItems() {
			return items;
		}

		public UserAccount getUserAccount() {
			return userAccount;
		}

		public DiscountShoppingCartType geDiscountShoppingCartType() {
			return discountType;
		}

		public Builder withUserAccount(UserAccount anUserAccount) {
			userAccount = anUserAccount;
			return this;
		}

		public Builder addItem(ShoppingCartItem anItem) {
			if (containsItem(anItem)) {
				ShoppingCartItem existingItem = getItem(anItem);
				existingItem.addNumberOfPieces(anItem.getNumberOfPieces());
			} else {
				items.add(anItem);
			}
			return this;
		}

		public Builder withDisocuntType(DiscountShoppingCartType aDiscountType) {
			discountType = aDiscountType;
			return this;
		}

		public ShoppingCart build() {
			if (!isValid()) {
				throw new IllegalArgumentException("UserAccount has to be set for building a ShoppingCart");
			}
			return new ShoppingCart(this);
		}

		private boolean isValid() {
			return userAccount != null;
		}

		private Boolean containsItem(ShoppingCartItem anItem) {
			return getItem(anItem) != null;
		}

		private ShoppingCartItem getItem(ShoppingCartItem anItem) {
			for (ShoppingCartItem item : items) {
				if (item.getArticle().getNumber().equals(anItem.getArticle().getNumber())) {
					return item;
				}
			}
			return null;
		}
	}
}