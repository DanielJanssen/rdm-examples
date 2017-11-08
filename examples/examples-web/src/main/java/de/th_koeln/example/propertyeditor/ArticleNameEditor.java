package de.th_koeln.example.propertyeditor;

import java.beans.PropertyEditorSupport;

import de.th_koeln.example.shoppingcart.attribute.ArticleName;

public class ArticleNameEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		setValue(ArticleName.fromValue(text));
	}

}
