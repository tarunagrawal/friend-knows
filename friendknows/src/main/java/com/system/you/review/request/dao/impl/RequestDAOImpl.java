package com.system.you.review.request.dao.impl;

import java.util.List;

import org.hibernate.Cache;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.item.bean.Item;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.dao.RequestDAO;
import com.system.you.review.user.bean.ReviewUser;

@SuppressWarnings("unchecked")
public class RequestDAOImpl extends DAOSupport<Request> implements RequestDAO {

	@Override
	public void save(Request reviewRequest) {
		if (reviewRequest.getRequestID() != null) {
			Cache cache = sessionFactory.getCache();
			if (cache != null) {
				String role = getEntity().getCanonicalName() + ".reviewers";
				String requestID = reviewRequest.getRequestID();
				if (cache.containsCollection(role, requestID)) {
					cache.evictCollection(role, requestID);
				}
			}
		}
		getSession().saveOrUpdate(reviewRequest);
	}

	@Override
	public Request get(String id) {
		return (Request) getSession().get(Request.class, id);
	}

	@Override
	public List<Request> get(ReviewUser reviewee) {
		Criteria criteria = getCriteria()
				.add(Restrictions.ne("status", Request.Status.CLOSED))
				.createCriteria("reviewee", "reviewee")
				.add(Restrictions.eq("reviewee.id", reviewee.getId()));
		return criteria.list();
	}

	@Override
	public List<Request> get(Item item) {
		return getCriteria().createCriteria("item")
				.add(Restrictions.eq("id", item.getId())).list();
	}

	@Override
	public Request get(ReviewUser reviewee, Request parentRequest) {
		return (Request) getCriteria()
				.createAlias("parentRequest", "parent")
				.createAlias("reviewee", "user")
				.add(Restrictions.eq("parent.requestID",
						parentRequest.getRequestID()))
				.add(Restrictions.eq("user.id", reviewee.getId()))
				.uniqueResult();
	}

	@Override
	public boolean close(String id) {
		Request request = get(id);
		if (request != null) {
			getSession().delete(request);
			return true;
		}
		return false;
	}

	@Override
	public List<Request> all() {
		return getCriteria().list();
	}

	@Override
	protected Class<Request> getEntity() {
		return Request.class;
	}

	@Override
	public int totalRequest(ReviewUser user) {
		return ((Number) getCriteria().createAlias("reviewee", "user")
				.add(Restrictions.eq("user.id", user.getId()))
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
	}
}
