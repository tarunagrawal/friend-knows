package com.system.you.review.web.beans.view;

import java.util.List;

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
	
	public CategoryViewBean getCategory() {
		return category;
	}

	public void setCategory(CategoryViewBean category) {
		this.category = category;
	}



	private String id;
	private String description;
	private List<TagViewBean> rating;
	private List<TagViewBean> connectedRating;
	private CategoryViewBean category ;
}
