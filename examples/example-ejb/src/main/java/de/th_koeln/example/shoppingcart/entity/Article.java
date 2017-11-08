package de.th_koeln.example.shoppingcart.entity;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import de.th_koeln.example.shoppingcart.attribute.ArticleDescription;
import de.th_koeln.example.shoppingcart.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.attribute.ArticleName;
import de.th_koeln.example.shoppingcart.attribute.ArticleNumber;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

@Entity
public class Article {

	@EmbeddedId
	private ArticleId id;
	@Embedded
	private ArticleNumber number;
	@Embedded
	private ArticleName name;
	@Embedded
	private ArticleDescription description;
	@Embedded
	private PricePerPiece price;

	protected Article() {
		super();
	}

	private Article(Builder aBuilder) {
		super();
		id = ArticleId.fromValue();
		number = aBuilder.getNumber();
		name = aBuilder.getName();
		description = aBuilder.getDescription();
		price = aBuilder.getPrice();
	}

	public ArticleId getId() {
		return id;
	}

	public ArticleNumber getNumber() {
		return number;
	}

	public ArticleName getName() {
		return name;
	}

	public ArticleDescription getDescription() {
		return description;
	}

	public PricePerPiece getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return getName().toString();
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
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	//null values nicht zulassen in den objekten?! muss also immer einen wert haben?
	public static class Builder {
		private ArticleNumber number;
		private ArticleName name;
		private ArticleDescription description;
		private PricePerPiece price;

		public ArticleNumber getNumber() {
			return number;
		}

		public ArticleName getName() {
			return name;
		}

		public ArticleDescription getDescription() {
			return description;
		}

		public PricePerPiece getPrice() {
			return price;
		}

		public Builder withNumber(Integer aNumber) {
			number = ArticleNumber.fromValue(aNumber);
			return this;
		}

		public Builder withNumber(ArticleNumber aNumber) {
			return withNumber(aNumber.getValue());
		}

		public Builder withName(String aName) {
			name = ArticleName.fromValue(aName);
			return this;
		}

		public Builder withName(ArticleName aName) {
			return withName(aName.getValue());
		}

		public Builder withDescription(String aDescription) {
			description = ArticleDescription.fromValue(aDescription);
			return this;
		}

		public Builder withDescription(ArticleDescription aDescription) {
			return withDescription(aDescription.getValue());
		}

		public Builder forPrice(PricePerPiece aPrice) {
			price = new PricePerPiece.Builder().forCurrency(aPrice.getCurrency()).withAmount(aPrice.getAmount()).build();
			return this;
		}

		public Article build() {
			isValid();
			return new Article(this);
		}

		private void isValid() {
			if (number == null || number.isNullOrEmpty()) {
				throw new IllegalStateException("Number has to be set for building an Article");
			}
			if (name == null || name.isNullOrEmpty()) {
				throw new IllegalStateException("Name has to be set for building an Article");
			}
			if (description == null || description.isNullOrEmpty()) {
				throw new IllegalStateException("Description has to be set for building an Article");
			}
			if (price == null) {
				throw new IllegalStateException("Price has to be set for building an Article");
			}
		}
	}
}