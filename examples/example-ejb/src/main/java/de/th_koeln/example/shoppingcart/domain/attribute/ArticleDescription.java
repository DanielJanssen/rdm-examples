package de.th_koeln.example.shoppingcart.domain.attribute;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.StringAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ArticleDescription"))
public class ArticleDescription extends StringAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected ArticleDescription() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	@Deprecated
	private ArticleDescription(String aValue) {
		super(aValue);
	}

	public static ArticleDescription fromValue(String aValue) {
		return new ArticleDescription(aValue);
	}
}