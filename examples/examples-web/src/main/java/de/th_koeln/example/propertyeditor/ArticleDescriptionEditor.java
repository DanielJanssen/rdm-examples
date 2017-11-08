package de.th_koeln.example.propertyeditor;

import java.beans.PropertyEditorSupport;

import de.th_koeln.example.shoppingcart.attribute.ArticleDescription;

public class ArticleDescriptionEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		setValue(ArticleDescription.fromValue(text));
	}

}
