package de.th_koeln.example.shoppingcart.entity;

import java.util.ArrayList;
import java.util.List;

import de.th_koeln.example.shoppingcart.attribute.Quantity;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.enums.State;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

public class ShoppingCart {

	private ShoppingCartId id;
	private UserAccount userAccount;
	private State state;
	private List<ShoppingCartItem> items;

	private ShoppingCart(Builder aBuilder) {
		id = ShoppingCartId.fromValue();
		state = State.NOT_ORDERED;
		userAccount = aBuilder.getUserAccount();
		items = aBuilder.getItems();
		//gibt es einen fixierten total price? vermutlich schon!? => oder die Order/Bestellung hat den fixierten Preis
	}

	//was ist wenn sich der price geändert hat?
	public void addItem(ShoppingCartItem anItem) {
		if (!state.isOrdered()) {
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
		if (!state.isOrdered()) {
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
		if (!state.isOrdered()) {
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

	//hier muss vermutlich der fixe preis erzeugt werden
	public void order() {
		if (!state.isOrdered()) {
			if (!items.isEmpty()) {
				state = State.ORDERED;
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
	//berechne Gesamtpreis

	// wirklich die get-methode, dann kann man darauf ja wieder verändern? oder wirklich nur die anwendungsfälle?
	// oder eine neue liste zurückgeben
	//protected ist ganz gut für die tests... gucken, was wir später auf der GUI damit machen
	protected List<ShoppingCartItem> getItems() {
		return items;
	}

	protected State getState() {
		return state;
	}

	public PricePerPiece getTotalPrice() {
		return null;
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

		public List<ShoppingCartItem> getItems() {
			return items;
		}

		public UserAccount getUserAccount() {
			return userAccount;
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

		public ShoppingCart build() {
			if (isValid()) {
				throw new IllegalArgumentException("UserAccount has to be set for building a ShoppingCart");
			}
			return new ShoppingCart(this);
		}

		private boolean isValid() {
			return userAccount == null;
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