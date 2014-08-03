package com.system.you.review.request.service;

import com.system.you.review.request.bean.ExternalReviewer;

public interface ExternalReviewerService {

	public ExternalReviewer getExternalReviewerByProviderId(String providerId);

	public ExternalReviewer addExternalReviewer(
			ExternalReviewer externalReviewer);

	public ExternalReviewer getExternalReviewerForReviewerRequest(
			String reviewerRequestId);
}
