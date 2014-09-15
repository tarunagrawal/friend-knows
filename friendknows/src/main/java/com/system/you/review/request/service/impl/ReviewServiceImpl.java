package com.system.you.review.request.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.bean.helper.impl.ItemBeanHelper;
import com.system.you.review.item.bean.helper.impl.ReviewBeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Request.Status;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.dao.ReviewDAO;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.request.service.ServiceSupport;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class ReviewServiceImpl extends ServiceSupport implements ReviewService {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review addReview(ReviewFormBean review, Reviewer reviewerRequest)
			throws ServiceException {
		try {
			hasRight(reviewerRequest);
			Review bean = reviewBean(review, reviewerRequest.getId(),
					reviewerRequest.getRequest().getItem());
			reviewDAO.addReview(bean);

			Status status = reviewerRequest.getStatus();
			Status newStatus = Status.ANSWERED;
			if (status == Status.PROPAGATED) {
				newStatus = Status.ASWERED_FORWARED;
			}
			reviewerRequest.setStatus(newStatus);
			updateReviewer(reviewerRequest);
			triggerAddChange(bean);
			return bean;
		} catch (Exception e) {
			logErrorAndThrowException("exception occured while adding review ",
					e);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review addIndependentReview(ReviewFormBean review, String category,
			String itemId) throws ServiceException {
		try {
			Item item = itemBeanHelper.formToData(category, itemId);
			Review bean = reviewBean(review, null, item);
			reviewDAO.addReview(bean);
			triggerAddChange(bean);
			return bean;
		} catch (Exception e) {
			logErrorAndThrowException("exception occured while adding review ",
					e);
		}
		return null;
	}

	private void updateReviewer(Reviewer reviewerRequest) {
		reviewerRequest.setUpdateDateTime(new Date());
		reviewerDAO.update(reviewerRequest);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review delete(String id) throws ServiceException {
		try {
			Review review = reviewDAO.getReview(id);
			if (review != null) {
				hasRight(review);
				reviewDAO.delete(id);
				Reviewer reviewer = reviewerDAO.getReviewer(review
						.getReviewerRequestId());
				Status newStatus = Status.INITIATED;
				Status existingStatus = reviewer.getStatus();
				if (existingStatus == Status.ASWERED_FORWARED) {
					newStatus = Status.PROPAGATED;
				}
				reviewer.setStatus(newStatus);
				updateReviewer(reviewer);
				triggerDeleteChange(review);
				return review;
			}
		} catch (Exception ex) {
			logErrorAndThrowException(
					"exception occurred while deleting review ", ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review edit(String id, ReviewFormBean formBean)
			throws ServiceException {
		try {
			Review dbBean = reviewDAO.getReview(id);
			if (dbBean != null) {
				hasRight(dbBean);
				dbBean.setReviewDescription(formBean.getReviewDescription());
				dbBean.setRating(formBean.getRating());
				dbBean.setUpdateDateTime(new Date());
				reviewDAO.update(dbBean);
				triggerModifyChange(dbBean);
				return dbBean;
			}
		} catch (Exception ex) {
			logErrorAndThrowException("error occured while edit the review", ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review verify(String requestId, String reviewerId, String reviewId)
			throws ServiceException {
		try {
			Reviewer reviewer = reviewerDAO.getReviewer(reviewerId);
			if (reviewer != null) {
				Request request = reviewer.getRequest();
				// make sure that input reviewer id belongs request
				if (request.getId().equalsIgnoreCase(requestId)) {
					// check that current user has made the request
					hasRight(request);
					Review review = reviewDAO.getReview(reviewId);
					if (review != null) {
						String reviewerRequestId = review
								.getReviewerRequestId();
						// make sure that reviewer request owns the review
						if (reviewerRequestId
								.equalsIgnoreCase(reviewer.getId())) {
							review.setVerified('Y');
							reviewDAO.update(review);
							return review;
						}
					}
				}
			}
		} catch (Exception ex) {
			logErrorAndThrowException(
					"error occured while verifying the review", ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Review> getReviewsForItem(String itemId)
			throws ServiceException {
		try {
			return reviewDAO.getReviewsByItem(itemId);
		} catch (Exception ex) {
			logErrorAndThrowException(
					"error occured while getting reviews for item", ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review get(String id) throws ServiceException {
		try {
			return reviewDAO.getReview(id);
		} catch (Exception ex) {
			logErrorAndThrowException("error occured while retrieving review "
					+ id, ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review copyToReviewer(String reviewId, String reviewerId)
			throws ServiceException {
		try {
			Reviewer reviewer = reviewerDAO.getReviewer(reviewerId);
			hasRight(reviewer);
			Review review = reviewDAO.getReview(reviewId);
			if (anyConnection(reviewer, review)) {
				Review copied = copy(review, reviewer);
				reviewDAO.addReview(copied);

				Status newStatus = Status.ANSWERED;
				Status existingStatus = reviewer.getStatus();
				if (existingStatus == Status.PROPAGATED) {
					newStatus = Status.ASWERED_FORWARED;
				}
				reviewer.setStatus(newStatus);
				updateReviewer(reviewer);
				triggerAddChange(review);
				return copied;
			}
		} catch (Exception ex) {
			logErrorAndThrowException("error occured while copying review "
					+ reviewId + " reviewer " + reviewerId, ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Review> getReviews(ReviewUser user) throws ServiceException {
		try {
			return reviewDAO.getReviewsByReviewer(user);
		} catch (Exception ex) {
			logErrorAndThrowException(
					"error occured while getting reviews for user"
							+ user.getName(), ex);
		}
		return null;
	}

	private Review copy(Review review, Reviewer newReviewer) {
		Review copied = new Review();
		Date now = new Date();
		copied.setCreateDateTime(now);
		copied.setItem(review.getItem());
		copied.setRating(review.getRating());
		copied.setReviewDescription(review.getDescription());
		copied.setReviewer(getCurrentUser());
		copied.setReviewerRequestId(newReviewer.getId());
		copied.setUpdateDateTime(now);
		copied.setVerified(Review.NOT_VERIFED);
		return copied;
	}

	private boolean anyConnection(Reviewer reviewer, Review review) {
		String reviewerId = review.getReviewerRequestId();
		Reviewer reviewOnwer = reviewerDAO.getReviewer(reviewerId);
		if (reviewOnwer == reviewer) {
			return true;
		}

		Request onwerParentRequest = reviewOnwer.getRequest()
				.getParentRequest();
		Request parentRequest = reviewer.getRequest();

		return onwerParentRequest == parentRequest;
	}

	private Review reviewBean(ReviewFormBean formBean, String reviewerId,
			Item item) {
		Review review = reviewBeanHelper.formToDB(formBean);
		review.setReviewerRequestId(reviewerId);
		review.setItem(item);
		return review;
	}

	private void logErrorAndThrowException(String message, Exception ex)
			throws ServiceException {
		logger.error(message, ex);
		throw new ServiceException(message, ex);
	}

	@Autowired
	private ItemBeanHelper itemBeanHelper;

	@Autowired
	private ReviewDAO reviewDAO;

	@Autowired
	private ReviewerDAO reviewerDAO;

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;

	private static Logger logger = LoggerFactory
			.getLogger(ReviewServiceImpl.class);

}
