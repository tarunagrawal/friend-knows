package com.system.you.review.database;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DAOSupport<T> {

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria getCriteria() {
		return getSession().createCriteria(getEntity()).setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).setCacheable(true);
	}

	
	protected abstract Class<T> getEntity();
	
	@Autowired
	protected SessionFactory sessionFactory;

}
