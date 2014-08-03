package com.system.you.review.item.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.system.you.review.core.service.SearchService;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.service.ItemSearchService;

@Service
public class ItemSearchServiceImpl extends SearchService implements
		ItemSearchService {

	@Override
	public Collection<Item> searchDescription(String term) {
		SearchQuery<Item> searchQuery = searchQuery(Item.class, "description", term);
		Collection<Item> list = searchQuery.executeWildCard();
		return list;
	}
}
