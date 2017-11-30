package de.th_koeln.example.shoppingcart.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import de.th_koeln.example.shoppingcart.domain.attribute.UserAccountId;

@Entity
public class UserAccount {

	@EmbeddedId
	UserAccountId id;

	public UserAccountId getId() {
		return id;
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
		UserAccount other = (UserAccount) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User";
	}
}