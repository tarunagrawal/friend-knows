package com.system.you.review.category.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.service.CategorySearchService;
import com.system.you.review.core.service.SearchService;

@Service
public class CategorySearchServiceImpl extends SearchService implements
		CategorySearchService {

	@Override
	public Collection<Category> search(String term) {

		SearchQuery<Category> saerchQuery = searchQuery(Category.class,
				"description", term);
		Collection<Category> results = saerchQuery.executeWildCard();
		return results;
	}

}
