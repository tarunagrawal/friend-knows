package com.system.you.review.web.beans.view;

import java.util.Collection;

public class ReviewerViewBean {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserViewBean getUser() {
		return user;
	}

	public void setUser(UserViewBean user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<ReviewViewBean> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<ReviewViewBean> reviews) {
		this.reviews = reviews;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public ItemViewBean getItem() {
		return item;
	}

	public void setItem(ItemViewBean item) {
		this.item = item;
	}

	public CategoryViewBean getCategory() {
		return category;
	}

	public void setCategory(CategoryViewBean category) {
		this.category = category;
	}

	public UserViewBean getInitiatedUser() {
		return initiatedUser;
	}

	public void setInitiatedUser(UserViewBean initiatedUser) {
		this.initiatedUser = initiatedUser;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	public RequestViewBean getForwardRequest() {
		return forwardRequest;
	}

	public void setForwardRequest(RequestViewBean forwardRequest) {
		this.forwardRequest = forwardRequest;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}



	private String id;
	private String status;
	private String requestId;
	private RequestViewBean forwardRequest ;
	private String createDateTime;
	private String requestDescription;
	private UserViewBean user;
	private UserViewBean initiatedUser;
	private ItemViewBean item;
	private CategoryViewBean category;
	private Collection<ReviewViewBean> reviews;
}
