package de.th_koeln.example.shoppingcard.bean.additem;

import de.th_koeln.example.event.ActionEvent;
import de.th_koeln.example.shoppingcart.attribute.ShoppingCartId;

public class AddItemEvent implements ActionEvent {

	private ShoppingCartId shoppingCartId;

	public AddItemEvent(ShoppingCartId aShoppingCartId) {
		super();
		shoppingCartId = aShoppingCartId;
	}

	public ShoppingCartId getShoppingCartId() {
		return shoppingCartId;
	}
}
