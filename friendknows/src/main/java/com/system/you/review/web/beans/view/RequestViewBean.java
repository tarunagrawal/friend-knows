package com.system.you.review.web.beans.view;

import java.util.Collection;

public class RequestViewBean extends ViewBean{

	public CategoryViewBean getCategory() {
		return category;
	}

	public void setCategory(CategoryViewBean category) {
		this.category = category;
	}

	public ItemViewBean getItem() {
		return item;
	}

	public void setItem(ItemViewBean item) {
		this.item = item;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ReviewerViewBean> getReviewers() {
		return reviewers;
	}

	public void setReviewers(Collection<ReviewerViewBean> reviewers) {
		this.reviewers = reviewers;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RequestViewBean getParentRequest() {
		return parentRequest;
	}

	public void setParentRequest(RequestViewBean parentRequest) {
		this.parentRequest = parentRequest;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	private String id ;
	private CategoryViewBean category;
	private ItemViewBean item;
	private String createDateTime;
	private String updateDatetime;
	private String description;
	private String shortDescription;
	private RequestViewBean parentRequest ;
	private Collection<ReviewerViewBean> reviewers;
}
