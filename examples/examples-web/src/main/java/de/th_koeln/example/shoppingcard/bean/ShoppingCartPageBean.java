package de.th_koeln.example.shoppingcard.bean;

import javax.annotation.ManagedBean;

@ManagedBean
public class ShoppingCartPageBean {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
