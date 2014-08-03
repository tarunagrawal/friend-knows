package com.system.you.review.request.dao;

import java.util.List;
import java.util.Set;

import com.system.you.review.request.bean.ExternalReviewer;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.user.bean.ReviewUser;

public interface ReviewerDAO {

	public void addReviewer(Reviewer reviewerRequest);

	public void update(Reviewer reviewer);

	public void addExternalReviewer(ExternalReviewer externalReviewer);

	public Reviewer getReviewer(String id);

	public List<Reviewer> getReviewers(ReviewUser reviewer);

	public List<Reviewer> getReviewers(Request reviewRequest);

	public List<Reviewer> getReviewers(Reviewer.Channel channel);

	public List<Reviewer> getReviewers(Set<String> requestIds);

	public Reviewer close(String reviewerId);

	public int getReviewerCount(String requestId);

	public boolean alreadyExist(String requestId, String providerId);
}
