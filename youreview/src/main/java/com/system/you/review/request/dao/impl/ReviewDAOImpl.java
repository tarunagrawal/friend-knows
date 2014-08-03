package com.system.you.review.request.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.item.bean.Item;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.dao.ReviewDAO;
import com.system.you.review.user.bean.ReviewUser;

@SuppressWarnings("unchecked")
public class ReviewDAOImpl extends DAOSupport<Review> implements ReviewDAO {

	@Override
	public void addReview(Review review) {
		getSession().save(review);
	}

	@Override
	public Review getReview(String id) {
		return (Review) getSession().get(Review.class, id);
	}

	@Override
	public List<Review> getReviewsByReviewerRequestID(String id) {
		return getCriteria().add(
				Restrictions.eq("reviewerRequest.requestID", id)).list();

	}

	@Override
	public List<Review> getReviewsByItem(Item item) {
		return getReviewsByItem(item.getId());
	}

	@Override
	public List<Review> getReviewsByReviewer(ReviewUser reviewer) {
		return getCriteria().add(
				Restrictions.eq("reviewer.id", reviewer.getId())).list();
	}

	@Override
	public List<Review> getVerifiedReviewsForReviewer(ReviewUser reviewer) {
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("reviewer.id", reviewer.getId());
		map.put("Verified", String.valueOf(Review.VERIFED));
		return getCriteria().add(Restrictions.allEq(map)).list();
	}

	@Override
	public List<Review> getPendingVerificationReviewsForReviewee(
			ReviewUser reviewee) {
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("reviewerRequest.reviewRequest.reviewee.id", reviewee.getId());
		map.put("Verified", String.valueOf(Review.NOT_VERIFED));
		return getCriteria().add(Restrictions.allEq(map)).list();
	}

	@Override
	public List<Review> getReviewsByReviewerRequests(
			Set<String> reviewerRequestIds) {
		Criteria criteria = getCriteria().createCriteria("reviewerRequest")
				.add(Restrictions.in("requestID", reviewerRequestIds));
		return criteria.list();
	}

	@Override
	public Review delete(String id) {
		Review review = getReview(id);
		if (review != null) {
			getSession().delete(review);
			return review;
		}
		return null;
	}

	@Override
	public void update(Review review) {
		getSession().update(review);
	}

	@Override
	public List<Review> getReviewsByItem(String itemId) {
		return getCriteria().add(Restrictions.eq("item.id", itemId))
				.list();
	}

	@Override
	protected Class<Review> getEntity() {
		return Review.class;
	}
}
