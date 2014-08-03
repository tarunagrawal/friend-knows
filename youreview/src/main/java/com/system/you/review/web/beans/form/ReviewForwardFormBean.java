package com.system.you.review.web.beans.form;

import org.hibernate.validator.constraints.NotBlank;

public class ReviewForwardFormBean {

	public String getReviewerRequestId() {
		return reviewerRequestId;
	}

	public void setReviewerRequestId(String reviewerRequestId) {
		this.reviewerRequestId = reviewerRequestId;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}
	
	@NotBlank
	private String reviewerRequestId;

	@NotBlank
	private String friends ;

}
