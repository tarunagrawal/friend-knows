package com.system.you.review.request.dao;

import com.system.you.review.request.bean.ExternalReviewer;

public interface ExternalReviewerDAO {
	public ExternalReviewer getExternalReviewerByProviderId(String providerId);

	public ExternalReviewer addExternalReviewer(
			ExternalReviewer externalReviewer);
	
	public ExternalReviewer getExternalReviewerForReviewerRequest(
			String reviewerRequestId);
}
