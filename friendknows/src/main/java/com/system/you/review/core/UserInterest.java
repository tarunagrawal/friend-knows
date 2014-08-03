package com.system.you.review.core;

import com.system.you.review.user.bean.ReviewUser;

public class UserInterest {

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public ReviewUser getUser() {
		return user;
	}
	public void setUser(ReviewUser user) {
		this.user = user;
	}
	public PopularTags getTags() {
		return tags;
	}
	public void setTags(PopularTags tags) {
		this.tags = tags;
	}
	public int getToalReviews() {
		return toalReviews;
	}
	public void setToalReviews(int toalReviews) {
		this.toalReviews = toalReviews;
	}
	private String itemId;
	private ReviewUser user;
	private PopularTags tags;
	private int toalReviews;
}
