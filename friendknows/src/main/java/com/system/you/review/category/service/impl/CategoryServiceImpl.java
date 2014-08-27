package com.system.you.review.category.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.dao.CategoryDAO;
import com.system.you.review.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@PostConstruct
	public void postConstructor(){
		//this.categories = all();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Category getDefaultCategory() {
		synchronized (this) {
			if (defaultCategory == null) {
				Category category = categoryDAO
						.getCategoryByDescription(DEFAULT_CATEGORY_DESC);
				if (category == null) {
					category = new Category();
					category.setDescription(DEFAULT_CATEGORY_DESC);
					addCategory(category);
					defaultCategory = category;
				} else {
					defaultCategory = category;
				}
			}
		}
		return defaultCategory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addCategory(Category category) {
		categoryDAO.add(category);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Category> all() {
		if (categories == null) {
			categories = categoryDAO.all();
			return categories;
		}
		return categories;
	}

	@Autowired
	private CategoryDAO categoryDAO;

	private List<Category> categories ;

	private static Category defaultCategory;
}
