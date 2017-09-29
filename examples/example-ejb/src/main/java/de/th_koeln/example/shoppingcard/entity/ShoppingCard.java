package de.th_koeln.example.shoppingcard.entity;

import java.util.ArrayList;
import java.util.List;

import de.th_koeln.example.shoppingcard.attribute.Price;
import de.th_koeln.example.shoppingcard.attribute.ShoppingCardId;
import de.th_koeln.example.shoppingcard.attribute.State;

public class ShoppingCard {

	private ShoppingCardId id;
	private UserAccount userAccount;
	private State state;
	private List<ShoppingCardItem> items;

	//add item => geht nur im bestimmten status
	//remove item => geht nur im bestimmten status

	//bestellen? => dann ändert sich der State und nix kann mehr verändert werden

	//berechne Gesamtpreis

	public Price getTotalPrice() {
		return null;
	}

	public static class Builder {
		private UserAccount userAccount;
		private List<ShoppingCardItem> items = new ArrayList<>();

		public Builder() {

		}

		public List<ShoppingCardItem> getItems() {
			return items;
		}

		public UserAccount getUserAccount() {
			return userAccount;
		}

		public Builder withUserAccount(UserAccount anUserAccount) {
			userAccount = anUserAccount;
			return this;
		}

		public Builder addItem(ShoppingCardItem anItem) {
			items.add(anItem);
			return this;
		}

		public ShoppingCard build() {
			if (userAccount == null) {
				throw new IllegalArgumentException("UserAccount has to be set for building a ShoppingCard");
			}
			return new ShoppingCard(this);
		}
	}

	private ShoppingCard(Builder aBuilder) {
		id = ShoppingCardId.fromValue();
		state = State.fromValue(State.NOT_ORDERED);
		userAccount = aBuilder.getUserAccount();
		items = aBuilder.getItems();
	}

}
