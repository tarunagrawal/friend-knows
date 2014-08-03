package com.system.you.review.core;

import java.io.Serializable;

public class PopularTags implements Serializable {

	public PopularTags(WeightedTag[] popularTags) {
		this.popularTags = popularTags;
	}

	public WeightedTag[] getPopularTags() {
		return popularTags;
	}

	private WeightedTag[] popularTags;
	private static final long serialVersionUID = -2981002501572592106L;

}
