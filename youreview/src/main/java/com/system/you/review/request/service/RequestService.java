package com.system.you.review.request.service;

import java.util.Collection;
import java.util.List;

import com.system.you.review.core.service.exception.ReviewerAlreadyExist;
import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.form.ReviewForwardFormBean;
import com.system.you.review.web.beans.form.RequestFormBean;

public interface RequestService {

	public Request create(RequestFormBean formBean) throws ServiceException;

	public boolean close(String requestId) throws ServiceException;

	public Request forwardRequest(ReviewForwardFormBean formBean)
			throws ServiceException;

	public List<Request> getAllRequestForUser(ReviewUser user)
			throws ServiceException;

	public Request forwardRequest(ReviewUser user, Request request)
			throws ServiceException;

	public Reviewer removeReviewer(String requestId, String reviewerId)
			throws ServiceException;

	public Request get(String requestId) throws ServiceException;

	public Collection<Reviewer> addReviewers(String requestId, String reviewers)
			throws ServiceException, ReviewerAlreadyExist;

	public Request editDescription(String requestId, String description)
			throws ServiceException;
}
