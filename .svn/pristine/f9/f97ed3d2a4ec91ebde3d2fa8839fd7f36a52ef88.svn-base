package com.system.you.review.web.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.request.service.ReviewerService;

@Service
public class ProfileHelper extends ControllerSupport {

	public Collection<Request> reviewInitiated() {
		try {
			Collection<Request> reviewRequests = reviewRequestService
					.getAllRequestForUser(getLoggedInUser());
			return reviewRequests;
		} catch (Exception ex) {
			logger.error("Error while retriving initiated request", ex);
		}
		return null;
	}

	public Collection<Reviewer> assignedRequests() {
		try {
			Collection<Reviewer> reviewerRequests = reviewerRequestService
					.getReviewers(getLoggedInUser());
			return reviewerRequests;
		} catch (Exception ex) {
			logger.error("Error while retriving assgined request", ex);
		}
		return null;
	}

	@Autowired
	private RequestService reviewRequestService;
	
	@Autowired
	private ReviewerService reviewerRequestService;

	private static Logger logger = LoggerFactory.getLogger(ProfileHelper.class);
}
