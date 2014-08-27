package com.system.you.review.category.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.system.you.review.category.bean.Category;

public interface CategoryDAO {

	public void add(Category category) throws DataAccessException;

	public Category getCategory(String id) throws DataAccessException;
	
	public List<Category> all();

	public Category getCategoryByDescription(String description)
			throws DataAccessException;
}
