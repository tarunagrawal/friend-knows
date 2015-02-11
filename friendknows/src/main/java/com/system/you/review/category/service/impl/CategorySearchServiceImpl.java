package com.system.you.review.category.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.service.CategorySearchService;
import com.system.you.review.core.service.SearchService;

@Service
public class CategorySearchServiceImpl extends SearchService implements
		CategorySearchService {

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<Category> search(String term) {

		SearchQuery<Category> saerchQuery = searchQuery(Category.class,
				"description", term);
		Collection<Category> results = saerchQuery.executeWildCard();
		return results;
	}

}
