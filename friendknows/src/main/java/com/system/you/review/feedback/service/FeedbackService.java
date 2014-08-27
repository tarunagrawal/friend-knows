package com.system.you.review.feedback.service;

import java.util.List;

import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.feedback.bean.Feedback;
import com.system.you.review.feedback.bean.FeedbackFormBean;

public interface FeedbackService {

	public Feedback add(FeedbackFormBean formBean) throws ServiceException;

	public List<Feedback> all() throws ServiceException;
}
