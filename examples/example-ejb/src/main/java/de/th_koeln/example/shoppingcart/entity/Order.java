package de.th_koeln.example.shoppingcart.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.th_koeln.example.shoppingcart.attribute.OrderDate;
import de.th_koeln.example.shoppingcart.attribute.OrderId;
import de.th_koeln.example.shoppingcart.vo.TotalPrice;

@Entity
@Table(name = "ordering")
public class Order {

	@EmbeddedId
	private OrderId id;
	@OneToOne(mappedBy = "order")
	private ShoppingCart shoppingCart;
	@Embedded
	private TotalPrice totalPrice;
	@Embedded
	private OrderDate orderDate;

	protected Order() {
		super();
	}

	private Order(Builder aBuilder) {
		super();
		id = OrderId.fromValue();
		orderDate = OrderDate.fromValue(new Date());
		shoppingCart = aBuilder.getShoppingCart();
		totalPrice = shoppingCart.getTotal();
	}

	public OrderId getId() {
		return id;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public TotalPrice getTotalPrice() {
		return totalPrice;
	}

	public OrderDate getOrderDate() {
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

	public static class Builder {
		private ShoppingCart shoppingCart;

		public Builder forShoppingCart(ShoppingCart aShoppingCart) {
			shoppingCart = aShoppingCart;
			return this;
		}

		public ShoppingCart getShoppingCart() {
			return shoppingCart;
		}

		public Order build() {
			if (!isValid()) {
				throw new IllegalArgumentException("ShoppingCart has to be set for building an Order");
			}
			return new Order(this);
		}

		private boolean isValid() {
			return shoppingCart != null && !shoppingCart.getItems().isEmpty();
		}
	}

}
