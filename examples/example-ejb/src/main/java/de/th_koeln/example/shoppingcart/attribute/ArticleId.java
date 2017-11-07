package de.th_koeln.example.shoppingcart.attribute;

import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.th_koeln.rdm.attribute.UuidAttribute;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "ArticleId"))
public class ArticleId extends UuidAttribute {

	private static final long serialVersionUID = 1L;

	public ArticleId() { //needed for JPA
		super();
	}

	private ArticleId(String aQuantity) {
		super(aQuantity);
	}

	//ist from value hier wirklich richtig?
	// einerseits generiert er was zufälliges? lieber .newInstance?
	// andererseits ist es dann einheitlich
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
