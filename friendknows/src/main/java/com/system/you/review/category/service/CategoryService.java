package com.system.you.review.category.service;

import java.util.List;

import com.system.you.review.category.bean.Category;

public interface CategoryService {
	
	public Category getDefaultCategory();

	public void addCategory(Category category);
	
	public List<Category> all();

	public static String DEFAULT_CATEGORY_DESC = "default";
}
