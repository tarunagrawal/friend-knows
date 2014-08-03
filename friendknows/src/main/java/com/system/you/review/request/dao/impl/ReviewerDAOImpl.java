package com.system.you.review.request.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.request.bean.ExternalReviewer;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.bean.Reviewer.Channel;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.user.bean.ReviewUser;

@SuppressWarnings("unchecked")
public class ReviewerDAOImpl extends DAOSupport<Reviewer> implements
		ReviewerDAO {

	@Override
	public void addReviewer(Reviewer reviewerRequest) {
		getSession().save(reviewerRequest);
	}

	@Override
	public void addExternalReviewer(ExternalReviewer externalReviewer) {
		getSession().save(externalReviewer);
	}

	@Override
	public List<Reviewer> getReviewers(ReviewUser reviewer) {
		return getCriteria()
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.eq("reviewerID", reviewer.getProviderUserId()))
				.add(Restrictions.ne("status", Request.Status.CLOSED)).list();
	}

	@Override
	public List<Reviewer> getReviewers(Request reviewRequest) {
		return getCriteria()
				.createAlias("request", "request")
				.add(Restrictions.eq("request.requestID",
						reviewRequest.getRequestID())).list();
	}

	@Override
	public Reviewer getReviewer(String id) {
		return (Reviewer) getCriteria().add(Restrictions.eq("requestID", id))
				.uniqueResult();
	}

	@Override
	public List<Reviewer> getReviewers(Channel channel) {
		return getCriteria()
				.add(Restrictions.eq("channel", channel.toString())).list();

	}

	@Override
	public List<Reviewer> getReviewers(Set<String> requests) {
		return getCriteria().createAlias("request", "request")
				.add(Restrictions.in("request.requestID", requests)).list();
	}

	@Override
	public Reviewer close(String reviewerId) {
		Reviewer reviewer = getReviewer(reviewerId);
		if (reviewer != null) {
			getSession().delete(reviewer);
			/*reviewer.setStatus(Request.Status.CLOSED);
			getSession().update(reviewer);*/
		}
		return reviewer;
	}

	@Override
	public void update(Reviewer reviewer) {
		getSession().update(reviewer);
	}

	@Override
	protected Class<Reviewer> getEntity() {
		return Reviewer.class;
	}

	@Override
	public int getReviewerCount(String requestId) {
		return ((Number) getCriteria().createAlias("request", "request")
				.add(Restrictions.eq("request.requestID", requestId))
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
	}

	@Override
	public boolean alreadyExist(String requestId, String providerId) {
		int rows = ((Number) getCriteria().createAlias("request", "request")
				.add(Restrictions.eq("request.requestID", requestId))
				.add(Restrictions.eq("reviewerID", providerId))
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		if (rows > 0) {
			return true;
		}
		return false;
	}

}
