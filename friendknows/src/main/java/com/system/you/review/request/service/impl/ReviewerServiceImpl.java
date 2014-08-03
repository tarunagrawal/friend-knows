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
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.request.service.ServiceSupport;
import com.system.you.review.user.bean.ReviewUser;

@Service
public class ReviewerServiceImpl extends ServiceSupport implements
		ReviewerService {

	@Override
	@Transactional(readOnly = true)
	public List<Reviewer> getReviewers(ReviewUser user) throws ServiceException {
		try {
			return reviewerDAO.getReviewers(user);
		} catch (Exception ex) {
			logErrorAndThrowException(
					"Error occurred while retrieving reviewers list for user "
							+ user.getUsername(), ex);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Reviewer getReviewer(String id) throws ServiceException {
		try {
			return reviewerDAO.getReviewer(id);
		} catch (Exception ex) {
			logErrorAndThrowException(
					"Error occurred while retriving the reviewer with id " + id,
					ex);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Reviewer getReviewer(String id, boolean onlyForCurrentUser)
			throws ServiceException {
		Reviewer reviewer = getReviewer(id);
		if (reviewer != null) {
			hasRight(reviewer);
			return reviewer;
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Throwable.class })
	public Reviewer updateReviewer(Reviewer reviewer) throws ServiceException {
		try {
			reviewer.setUpdateDateTime(new Date());
			reviewerDAO.update(reviewer);
			return reviewer;
		} catch (Exception ex) {
			logErrorAndThrowException("erorr occured while updating reviewer "
					+ reviewer.getRequestID(), ex);
		}

		return null;
	}

	private void logErrorAndThrowException(String message, Exception ex)
			throws ServiceException {
		logger.error(message, ex);
		throw new ServiceException(message, ex);
	}

	@Autowired
	private ReviewerDAO reviewerDAO;

	private static Logger logger = LoggerFactory
			.getLogger(ReviewerServiceImpl.class);

}
