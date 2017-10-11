package de.th_koeln.example.shoppingcart.entity;

import java.util.ArrayList;
import java.util.List;

import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;
import de.th_koeln.example.shoppingcart.attribute.State;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

public class ShoppingCart {

	private ShoppingCartId id;
	private UserAccount userAccount;
	private State state;
	private List<ShoppingCartItem> items;

	private ShoppingCart(Builder aBuilder) {
		id = ShoppingCartId.fromValue();
		state = State.fromValue(State.NOT_ORDERED);
		userAccount = aBuilder.getUserAccount();
		items = aBuilder.getItems();
	}

	//add item => geht nur im bestimmten status
	//gucken ob das Item schon drin ist, dann bei dem alten die quantity veärndern?
	public void addItem(ShoppingCartItem anItem) {
		if (state.getValue().equals(State.NOT_ORDERED)) {
			if (items.contains(anItem)) {
				//quantität hochzählen
				//contains hier bestimmt nicht richtig, weil es nur auf die artikelnr ankommt
			} else {
				items.add(anItem);
			}
		} else {
			throw new IllegalStateException("You cannot remove an Item if the ShoppingCart is still ordered");
		}
	}

	//ggf nur die anzahl verringern? oder seperate methode (!)
	public void removeItem(ShoppingCartItem anItem) {
		if (state.getValue().equals(State.NOT_ORDERED)) {
			if (items.contains(anItem)) {
				//contains hier bestimmt nicht richtig, weil es nur auf die artikelnr ankommt
				items.remove(anItem);
			} else {
				throw new IllegalArgumentException("Your ShoppingCart does not contain the requested Item " + anItem);
			}
		} else {
			throw new IllegalStateException("You cannot remove an Item if the ShoppingCart is still ordered");
		}
	}

	public void order() {
		if (state.getValue().equals(State.NOT_ORDERED)) {
			if (!items.isEmpty()) {
				state = State.fromValue(State.ORDERED);
			} else {
				throw new IllegalStateException("ShoppingCart cannot be ordered, because it does not contain any ShoppingCartItems");
			}
		} else {
			throw new IllegalStateException("ShoppingCart is still ordered");
		}
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
			items.add(anItem);
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
	}
}