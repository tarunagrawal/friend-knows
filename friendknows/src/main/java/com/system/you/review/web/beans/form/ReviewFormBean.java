package com.system.you.review.web.beans.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class ReviewFormBean {

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public String getReviewerRequestId() {
		return reviewerRequestId;
	}

	public void setReviewerRequestId(String reviewerRequestId) {
		this.reviewerRequestId = reviewerRequestId;
	}

	@Range(min=0,max= 5)
	private int rating;
	
	@NotBlank(message="Description should not be left blank")
	@Length(max=500, message="Description should not exceed 500 char")
	private String reviewDescription;
	
	@NotNull
	@NotBlank
	private String reviewerRequestId;
}
