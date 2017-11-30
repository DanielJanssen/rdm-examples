package de.th_koeln.example.shoppingcart.domain.attribute;

import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.UuidAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ArticleId"))
public class ArticleId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	public ArticleId() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method
	 */
	@Deprecated
	private ArticleId(String aQuantity) {
		super(aQuantity);
	}

	public static ArticleId fromValue() {
		return new ArticleId(UUID.randomUUID().toString());
	}

	public static ArticleId fromValue(String anUuid) {
		return new ArticleId(anUuid);
	}

	@Override
	public final boolean equals(Object aObj) { //needed for jpa
		return super.equals(aObj);
	}

	@Override
	public final int hashCode() { //needed for jpa
		return super.hashCode();
	}

}
