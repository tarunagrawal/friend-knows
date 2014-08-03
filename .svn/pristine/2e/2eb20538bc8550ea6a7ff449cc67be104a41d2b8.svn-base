package com.system.you.review.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.system.you.review.request.bean.Review;

public class ItemCache implements Serializable {

	public ItemCache(String itemId, List<Review> reviews) {
		this.itemId = itemId;
		this.connectedTags = new ConcurrentHashMap<String, TagWeights>();
		if(reviews != null && reviews.size() >0){
			initialize(reviews);
		}else{
			//as there are no review hence initialization is required. This should have not happend
			totalTags = new TagWeights(new Tag[0]);
		}
	}
	
	public String getItemId(){
		return itemId;
	}

	public TagWeights getTotalTags() {
		return totalTags;
	}

	public Map<String, TagWeights> getConnectedTags(
			List<String> connectedFriends) {
		Map<String, TagWeights> connectedTagWeights = new HashMap<String, TagWeights>();
		for (String providerUserId : connectedFriends) {
			if (connectedTags.containsKey(providerUserId)) {
				connectedTagWeights.put(providerUserId,
						connectedTags.get(providerUserId));
			}
		}
		return connectedTagWeights;
	}

	private void initialize(List<Review> reviews) {
		if (reviews != null && reviews.size() > 0) {
			Tag[] tags = new Tag[reviews.size()];
			Map<String, List<Tag>> userTags = new HashMap<String, List<Tag>>();
			int i = 0;
			for (Review review : reviews) {
				Tag tag = Tag.getTag(review.getRating());
				tags[i++] = tag;
				String providerUserId = review.getReviewer()
						.getProviderUserId();
				if (userTags.containsKey(providerUserId)) {
					userTags.get(providerUserId).add(tag);
				} else {
					List<Tag> list = new ArrayList<Tag>();
					list.add(tag);
					userTags.put(providerUserId, list);
				}
			}
			processForOverallTags(tags);
			processForConnectedTags(userTags);
		}
	}

	private void processForOverallTags(Tag[] tags) {
		totalTags = new TagWeights(tags);
	}

	private void processForConnectedTags(Map<String, List<Tag>> usersTags) {
		Set<String> providerIds = usersTags.keySet();
		for (String providerId : providerIds) {
			List<Tag> tagList = usersTags.get(providerId);
			Tag[] tags = new Tag[tagList.size()];
			tagList.toArray(tags);
			TagWeights connectedWeight = new TagWeights(tags);
			connectedTags.put(providerId, connectedWeight);
		}
	}

	private String itemId;
	private TagWeights totalTags;
	private Map<String, TagWeights> connectedTags;

	private static final long serialVersionUID = -8991584276190235703L;

}
