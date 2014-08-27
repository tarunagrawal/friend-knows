package com.system.you.review.feedback.bean;

import com.system.you.review.web.beans.view.UserViewBean;

public class FeedbackViewBean {
	
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

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public UserViewBean getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(UserViewBean reviewUser) {
		this.reviewUser = reviewUser;
	}

	private String category;

	private String description;

	private String createDateTime;

	private String updateDateTime;

	private UserViewBean reviewUser;
}
