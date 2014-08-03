package com.system.you.review.request.dao;

import java.util.List;
import java.util.Set;

import com.system.you.review.item.bean.Item;
import com.system.you.review.request.bean.Review;
import com.system.you.review.user.bean.ReviewUser;

public interface ReviewDAO {

	public void addReview(Review review);

	public void update(Review review);

	public Review getReview(String id);

	public Review delete(String id);

	public List<Review> getReviewsByReviewerRequestID(String id);

	public List<Review> getReviewsByReviewerRequests(
			Set<String> reviewerRequestIds);

	public List<Review> getReviewsByItem(String itemId);
	
	public List<Review> getReviewsByItem(Item item);

	public List<Review> getReviewsByReviewer(ReviewUser reviewer);

	public List<Review> getVerifiedReviewsForReviewer(ReviewUser reviewer);

	public List<Review> getPendingVerificationReviewsForReviewee(
			ReviewUser reviewee);
}
