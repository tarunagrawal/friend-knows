package com.system.you.review.feedback.bean;

import org.hibernate.validator.constraints.NotBlank;

public class FeedbackFormBean {

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	private String category ;
	
	@NotBlank
	private String description ;
}
