package com.system.you.review.request.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.request.bean.ExternalReviewer;
import com.system.you.review.request.dao.ExternalReviewerDAO;
import com.system.you.review.request.service.ExternalReviewerService;

@Service
public class ExternalReviewerServiceImpl implements ExternalReviewerService {

	@Override
	@Transactional(readOnly = true)
	public ExternalReviewer getExternalReviewerByProviderId(String providerId) {
		return externalReviewerDAO.getExternalReviewerByProviderId(providerId);
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalReviewer getExternalReviewerForReviewerRequest(
			String reviewerRequestId) {
		return externalReviewerDAO
				.getExternalReviewerForReviewerRequest(reviewerRequestId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ExternalReviewer addExternalReviewer(
			ExternalReviewer externalReviewer) {
		return externalReviewerDAO.addExternalReviewer(externalReviewer);
	}

	@Autowired
	private ExternalReviewerDAO externalReviewerDAO;
}
