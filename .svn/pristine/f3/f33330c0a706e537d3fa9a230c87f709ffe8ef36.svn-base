package com.system.you.review.category.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.dao.CategoryDAO;
import com.system.you.review.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Override
	@Transactional(readOnly=true)
	public Category getDefaultCategory() {
		synchronized (this) {
			if (defaultCategory == null) {
				Category category= categoryDAO
						.getCategoryByDescription(DEFAULT_CATEGORY_DESC);
				if (category == null) {
					category = new Category();
					category.setDescription(DEFAULT_CATEGORY_DESC);
					addCategory(category);
					defaultCategory = category;
				}else{
					defaultCategory =	category;
				}
			}
		}
		return defaultCategory;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addCategory(Category category) {
		categoryDAO.add(category);
	}
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	private static Category defaultCategory ;
}
