package com.system.you.review.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TagWeights implements Serializable {

	public TagWeights(TagWeights[] tagWeights) {
		initialize();
		for (TagWeights tagWeight : tagWeights) {
			updateTags(tagWeight);
		}
	}

	public TagWeights(Tag[] tags) {
		initialize();
		for (Tag tag : tags) {
			updateTag(tag.getName(), 1);
		}
	}

	public PopularTags getPopularTags() {
		if (!stale) {
			return popularTags;
		}
		synchronized (this) {
			if (!stale) {
				return popularTags;
			}
			this.popularTags = initializePopularTags();
			stale = false;
		}
		return popularTags;
	}

	public int getTotal(){
		return total;
	}
	
	private void initialize() {
		this.tagWeights = new HashMap<String, Integer>();
		this.total = 0;
		this.stale = true;
	}

	private void updateTags(TagWeights tagWeights) {
		Map<String, Integer> weights = tagWeights.tagWeights;
		for (Entry<String, Integer> entry : weights.entrySet()) {
			String tagName = entry.getKey();
			int count = entry.getValue();
			updateTag(tagName, count);
		}
	}

	private int updateTag(String tagName, int increment) {
		int existingCount = getCountForTag(tagName);
		updateTag(tagName, existingCount, increment);
		stale = true;
		return existingCount;
	}

	private int getCountForTag(String tagName) {
		int count = 0;
		if (tagWeights.containsKey(tagName)) {
			count = tagWeights.get(tagName);
		}
		return count;
	}

	private PopularTags initializePopularTags() {
		List<WeightedTag> tags = new ArrayList<WeightedTag>();
		for (Entry<String, Integer> entry : tagWeights.entrySet()) {
			Tag tag = Tag.valueOf(entry.getKey());
			tags.add(new WeightedTag(tag, entry.getValue(),
					calculatePercentage(total, entry.getValue())));
		}

		Collections.sort(tags, new Comparator<WeightedTag>() {
			@Override
			public int compare(WeightedTag arg0, WeightedTag arg1) {
				return new Integer(arg0.getCount()).compareTo(new Integer(arg1
						.getCount()));
			}
		});

		WeightedTag[] weightedTags = new WeightedTag[POPULAR_TAGS_COUNT];
		for (int i = 0; i < POPULAR_TAGS_COUNT && i < tags.size(); i++) {
			weightedTags[i] = tags.get(i);
		}
		return new PopularTags(weightedTags);
	}

	private int calculatePercentage(int total, Integer value) {
		return (value / total) * 100;
	}

	private void updateTag(String tagName, int count, int increment) {
		updateTotal(increment);
		int total = increment + count;
		this.tagWeights.put(tagName, total);
	}

	private void updateTotal(int increment) {
		this.total = this.total + increment;
	}

	private int total;
	private boolean stale;
	private PopularTags popularTags;
	private Map<String, Integer> tagWeights;

	private static final int POPULAR_TAGS_COUNT = 3;
	private static final long serialVersionUID = -9075236914881878312L;

}
