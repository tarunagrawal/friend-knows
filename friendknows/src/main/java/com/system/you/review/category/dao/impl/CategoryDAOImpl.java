package com.system.you.review.category.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.dao.CategoryDAO;
import com.system.you.review.database.DAOSupport;

public class CategoryDAOImpl extends DAOSupport<Category> implements
		CategoryDAO {

	@Override
	public void add(Category category) throws DataAccessException {
		getSession().save(category);
	}

	@Override
	public Category getCategory(String id) throws DataAccessException {
		return (Category) getSession().get(Category.class, id);
	}

	@Override
	public Category getCategoryByDescription(String description)
			throws DataAccessException {
		return (Category) getCriteria().add(
				Restrictions.eq("description", description)).uniqueResult();
	}
	
	@Override
	public Class<Category> getEntity(){
		return Category.class;
	}
}
