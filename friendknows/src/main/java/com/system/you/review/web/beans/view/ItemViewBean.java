package com.system.you.review.web.beans.view;

import java.util.List;

import com.system.you.review.core.PopularTags;

public class ItemViewBean {

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

	public List<TagViewBean> getRating() {
		return rating;
	}

	public void setRating(List<TagViewBean> rating) {
		this.rating = rating;
	}

	public List<TagViewBean> getConnectedRating() {
		return connectedRating;
	}

	public void setConnectedRating(List<TagViewBean> connectedRating) {
		this.connectedRating = connectedRating;
	}

	private String id;
	private String description;
	private List<TagViewBean> rating;
	private List<TagViewBean> connectedRating;
}
