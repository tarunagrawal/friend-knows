package com.system.you.review.web.beans.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.style.ToStringCreator;

public class ReviewerFormBean {

	public String getReviewerProviderId() {
		return reviewerProviderId;
	}

	public void setReviewerProviderId(String reviewerProviderId) {
		this.reviewerProviderId = reviewerProviderId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public boolean isExisting() {
		return existing;
	}

	public void setExisting(boolean existing) {
		this.existing = existing;
	}

	public String toString(){
		return new ToStringCreator(this)
				.append("providerId",reviewerProviderId)
				.append("mailId", mailId)
				.append("contactNumber", contactNumber).toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	private String reviewerProviderId; 
	@NotBlank
	private String mailId; 
	private String contactNumber;
	private boolean existing;
	private String name;
}
