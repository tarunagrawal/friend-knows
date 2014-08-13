package com.system.you.review.web.beans.form;

import javax.validation.constraints.NotNull;

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

	@NotBlank
	private String item;

	@NotBlank
	private String category;

	@NotNull
	@NotBlank
	@Length(max=500, message="Description should not exceeds 500 chars")
	private String description;

	@NotNull
	@NotBlank
	private String scope;

	@NotNull
	@NotBlank
	private String friends;
}
