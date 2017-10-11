package de.th_koeln.example.shoppingcart.entity;

import de.th_koeln.example.shoppingcart.attribute.ArticleDescription;
import de.th_koeln.example.shoppingcart.attribute.ArticleId;
import de.th_koeln.example.shoppingcart.attribute.ArticleName;
import de.th_koeln.example.shoppingcart.attribute.ArticleNumber;

public class Article {

	private ArticleId id;
	private ArticleNumber number;
	private ArticleName name;
	private ArticleDescription description;

	private Article(Builder aBuilder) {
		super();
		id = ArticleId.fromValue();
		number = aBuilder.getNumber();
		name = aBuilder.getName();
		description = aBuilder.getDescription();
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

		public ArticleNumber getNumber() {
			return number;
		}

		public ArticleName getName() {
			return name;
		}

		public ArticleDescription getDescription() {
			return description;
		}

		public Builder withNumber(Integer aNumber) {
			return withNumber(ArticleNumber.fromValue(aNumber));
		}

		public Builder withNumber(ArticleNumber aNumber) {
			number = aNumber;
			return this;
		}

		public Builder withName(String aName) {
			return withName(ArticleName.fromValue(aName));
		}

		public Builder withName(ArticleName aName) {
			name = aName;
			return this;
		}

		public Builder withDescription(String aDescription) {
			return withDescription(ArticleDescription.fromValue(aDescription));
		}

		public Builder withDescription(ArticleDescription aDescription) {
			description = aDescription;
			return this;
		}

		public Article build() {
			isValid();
			return new Article(this);
		}

		private void isValid() {
			if (number == null || number.isNullOrEmpty()) {
				throw new IllegalStateException("Number has to be set for building a Article");
			}
			if (name == null || name.isNullOrEmpty()) {
				throw new IllegalStateException("Name has to be set for building a Article");
			}
			if (description == null || description.isNullOrEmpty()) {
				throw new IllegalStateException("Description has to be set for building a Article");
			}
		}

	}

}
