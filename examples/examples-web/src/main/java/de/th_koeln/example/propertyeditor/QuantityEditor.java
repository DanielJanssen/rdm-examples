package de.th_koeln.example.propertyeditor;

import java.beans.PropertyEditorSupport;

import de.th_koeln.example.shoppingcart.attribute.Quantity;

public class QuantityEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		setValue(Quantity.fromValue(Integer.valueOf(text)));
	}

}
