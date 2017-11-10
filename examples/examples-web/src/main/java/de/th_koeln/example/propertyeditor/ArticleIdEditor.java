package de.th_koeln.example.propertyeditor;

import java.beans.PropertyEditorSupport;

import de.th_koeln.example.shoppingcart.attribute.ArticleId;

public class ArticleIdEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		setValue(ArticleId.fromValue(text));
	}

}
