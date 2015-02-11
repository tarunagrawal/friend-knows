package com.system.you.review.web.beans.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.style.ToStringCreator;

public class RequestFormBean {

	public String getItem() {
		return item;
	}

	public void setItem(String items) {
		this.item = items;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String toString() {
		return new ToStringCreator(this).append("item", item)
				.append("description", description).append("scope", scope)
				.toString();
	}

	@NotBlank(message="You should select an item")
	private String item;

	@NotBlank(message="You should select a category")
	private String category;

	@NotBlank(message="You should not leave description blank")
	@Length(max=500, message="Description should not exceeds 500 chars")
	private String description;

	@NotBlank(message="You should select scope")
	private String scope;

	@NotBlank(message="You should select a friend")
	private String friends;
}
