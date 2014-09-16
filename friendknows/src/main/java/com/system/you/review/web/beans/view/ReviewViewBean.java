package com.system.you.review.web.beans.view;

public class ReviewViewBean {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public TagViewBean getTag() {
		return tag;
	}

	public void setTag(TagViewBean tag) {
		this.tag = tag;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public UserViewBean getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(UserViewBean reviewUser) {
		this.reviewUser = reviewUser;
	}

	public boolean isAgreed() {
		return agreed;
	}

	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	
	public ItemViewBean getItem() {
		return item;
	}

	public void setItem(ItemViewBean item) {
		this.item = item;
	}

	private String id;
	private String description;
	private String dateTime;
	private TagViewBean tag;
	private int rating ;
	private UserViewBean reviewUser;
	private boolean agreed ;
	private ItemViewBean item ;
}
