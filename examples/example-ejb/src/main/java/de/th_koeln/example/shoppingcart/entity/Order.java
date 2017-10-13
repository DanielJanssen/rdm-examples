package de.th_koeln.example.shoppingcart.entity;

import java.util.Date;

import de.th_koeln.example.shoppingcart.attribute.OrderId;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

public class Order {

	private OrderId id;
	private ShoppingCart shoppingCart;
	private TotalPrice totalPrice;
	private Date orderDate;

	public OrderId getId() {
		return id;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public TotalPrice getTotalPrice() {
		return totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
