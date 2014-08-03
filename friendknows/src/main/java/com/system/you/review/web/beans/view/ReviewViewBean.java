package com.system.you.review.web.beans.view;

import com.system.you.review.core.PopularTags;


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
	
	public PopularTags getTag() {
		return tag;
	}

	public void setTag(PopularTags tag) {
		this.tag = tag;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	private String id;
	private String description;
	private String dateTime;
	private PopularTags tag;
	private int rating ;
}
