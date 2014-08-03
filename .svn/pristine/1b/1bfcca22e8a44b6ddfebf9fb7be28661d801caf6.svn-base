package com.system.you.review.request.service;

import java.util.List;

import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.user.bean.ReviewUser;

public interface ReviewerService {

	public List<Reviewer> getReviewers(ReviewUser user) throws ServiceException;

	public Reviewer getReviewer(String id) throws ServiceException;

	public Reviewer getReviewer(String id, boolean onlyForCurrentUser)
			throws ServiceException;

	public Reviewer updateReviewer(Reviewer reviewer) throws ServiceException;
}
