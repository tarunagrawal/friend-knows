package com.system.you.review.request.dao.impl;

import org.hibernate.criterion.Restrictions;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.request.bean.ExternalReviewer;
import com.system.you.review.request.dao.ExternalReviewerDAO;

public class ExternalReviewerDAOImpl extends DAOSupport<ExternalReviewer>
		implements ExternalReviewerDAO {

	@Override
	public ExternalReviewer getExternalReviewerByProviderId(
			String providerUserId) {
		return (ExternalReviewer) getCriteria().add(
				Restrictions.eq("providerUserId", providerUserId))
				.uniqueResult();
	}

	@Override
	public ExternalReviewer getExternalReviewerForReviewerRequest(
			String reviewerRequestId) {
		return (ExternalReviewer) getCriteria()
				.createCriteria("reviewerRequest")
				.add(Restrictions.eq("requestID", reviewerRequestId))
				.uniqueResult();
	}

	@Override
	public ExternalReviewer addExternalReviewer(
			ExternalReviewer externalReviewer) {
		getSession().save(externalReviewer);
		return externalReviewer;
	}

	@Override
	public Class<ExternalReviewer> getEntity(){
		return ExternalReviewer.class;
	}

}
