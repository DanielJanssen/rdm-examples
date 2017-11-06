package de.th_koeln.example.shoppingcard.bean.additem;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import de.th_koeln.example.shoppingcart.entity.ShoppingCartItem;

@Named
@SessionScoped
public class ShoppingCartAddItemDialogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ShoppingCartItem.Builder shoppingCartItem;

	public void init(@Observes AddItemEvent anEvent) {
		shoppingCartItem = new ShoppingCartItem.Builder();
	}

	public ShoppingCartItem.Builder getShoppingCartItem() {
		return shoppingCartItem;
	}

}
