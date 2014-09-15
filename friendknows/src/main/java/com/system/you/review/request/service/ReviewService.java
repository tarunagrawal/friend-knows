package com.system.you.review.request.service;

import java.util.List;

import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.form.ReviewFormBean;

public interface ReviewService {

	public Review addReview(ReviewFormBean review, Reviewer reviewerRequest)
			throws ServiceException;
	
	public Review addIndependentReview(ReviewFormBean review, String category, String itemId) throws ServiceException;

	public Review delete(String id) throws ServiceException;

	public Review edit(String id, ReviewFormBean formBean)
			throws ServiceException;

	public Review verify(String requestId, String reviewerId, String reviewId) throws ServiceException;

	public List<Review> getReviewsForItem(String itemId)
			throws ServiceException;

	public Review get(String id) throws ServiceException;

	public Review copyToReviewer(String reviewId, String reviewerId) throws ServiceException;

	public List<Review> getReviews(ReviewUser user) throws ServiceException;
}
