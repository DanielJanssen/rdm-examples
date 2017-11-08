package de.th_koeln.example.propertyeditor;

import java.beans.PropertyEditorSupport;

import de.th_koeln.example.shoppingcart.attribute.ArticleNumber;

public class ArticleNumberEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		setValue(ArticleNumber.fromValue(Integer.valueOf(text)));
	}

}
