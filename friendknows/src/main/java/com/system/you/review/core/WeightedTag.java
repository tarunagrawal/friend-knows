package com.system.you.review.core;

import java.io.Serializable;

public class WeightedTag implements Serializable {

	public WeightedTag(Tag tag, int count, int percentage) {
		this.tag = tag;
		this.count = count;
		this.percentage = percentage;
	}

	public Tag getTag() {
		return tag;
	}

	public int getPercentage() {
		return percentage;
	}

	public int getCount() {
		return count;
	}

	private Tag tag;

	private int count;

	private int percentage;

	private static final long serialVersionUID = -5255597648472482276L;

}
