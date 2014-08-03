package com.system.you.review.item.service;

import java.util.Collection;

import com.system.you.review.item.bean.Item;

public interface ItemSearchService {

	public Collection<Item> searchDescription(String term);
}
