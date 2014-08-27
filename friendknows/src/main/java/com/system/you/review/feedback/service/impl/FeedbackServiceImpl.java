package com.system.you.review.feedback.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.feedback.bean.Feedback;
import com.system.you.review.feedback.bean.FeedbackFormBean;
import com.system.you.review.feedback.bean.helper.FeedbackBeanHelper;
import com.system.you.review.feedback.dao.FeedbackDAO;
import com.system.you.review.feedback.service.FeedbackService;
import com.system.you.review.request.service.ServiceSupport;

@Service
public class FeedbackServiceImpl extends ServiceSupport implements
		FeedbackService {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Feedback add(FeedbackFormBean formBean) throws ServiceException {
		try {
			Feedback feedback = feedbackBeanHelper.formToDB(formBean);
			feedbackDao.add(feedback);
			return feedback;
		} catch (Exception ex) {
			logErrorAndThrowException("error while adding feedback", ex);
		}
		return null;
	}

	@Override
	public List<Feedback> all() throws ServiceException {
		try {
			return feedbackDao.all();
		} catch (Exception ex) {
			logErrorAndThrowException("error while adding feedback", ex);
		}
		return null;
	}

	private void logErrorAndThrowException(String message, Exception ex)
			throws ServiceException {
		LOGGER.error(message, ex);
		if (ex instanceof ServiceException) {
			throw (ServiceException) ex;
		}

		throw new ServiceException(message, ex);
	}

	@Autowired
	private FeedbackDAO feedbackDao;

	@Autowired
	private FeedbackBeanHelper feedbackBeanHelper;

	private static Logger LOGGER = LoggerFactory
			.getLogger(FeedbackServiceImpl.class);
}
