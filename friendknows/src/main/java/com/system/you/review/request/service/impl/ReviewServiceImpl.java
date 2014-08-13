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
import com.system.you.review.item.bean.helper.impl.ReviewBeanHelper;
import com.system.you.review.request.bean.Request.Status;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.dao.ReviewDAO;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.request.service.ServiceSupport;
import com.system.you.review.web.beans.form.ReviewFormBean;

@Service
public class ReviewServiceImpl extends ServiceSupport implements ReviewService {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Review addReview(ReviewFormBean review, Reviewer reviewerRequest)
			throws ServiceException {
		try {
			hasRight(reviewerRequest);
			Review bean = reviewBean(review, reviewerRequest);
			reviewDAO.addReview(bean);
			
			Status status = reviewerRequest.getStatus() ;
			Status newStatus = Status.ANSWERED;
			if(status == Status.PROPAGATED){
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
				Status existingStatus = reviewer.getStatus() ;
				if(existingStatus == Status.ASWERED_FORWARED){
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
	public Review verify(String id) throws ServiceException {
		try {
			Review review = reviewDAO.getReview(id);
			if (review != null) {
				hasRight(review);
				review.setVerified('Y');
				reviewDAO.update(review);
				return review;
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

	private Review reviewBean(ReviewFormBean formBean, Reviewer reviewerRequest) {
		Review review = reviewBeanHelper.formToDB(formBean);
		review.setReviewerRequestId(reviewerRequest.getRequestID());
		review.setItem(reviewerRequest.getRequest().getItem());
		return review;
	}

	private void logErrorAndThrowException(String message, Exception ex)
			throws ServiceException {
		logger.error(message, ex);
		throw new ServiceException(message, ex);
	}

	@Autowired
	private ReviewDAO reviewDAO;

	@Autowired
	private ReviewerDAO reviewerDAO;

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;

	private static Logger logger = LoggerFactory
			.getLogger(ReviewServiceImpl.class);

}
