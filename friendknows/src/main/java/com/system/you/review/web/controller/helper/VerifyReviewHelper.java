package com.system.you.review.web.controller.helper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.request.service.ReviewService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.review.ReviewVerifyException;

@Service
public class VerifyReviewHelper extends ControllerHelper {

	public RequestContext<String, String> verify(String reviewId)
			throws ReviewVerifyException {
		RequestContext<String, String> requestContext = new RequestContext<String, String>(
				reviewId);
		try {
			validateInput(requestContext);
			if (!requestContext.containsMessage()) {
				if (reviewService.verify(reviewId) == null) {
					requestContext.addMessage("page",
							getMessage("review.verify.error", null));
				}
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}
		return requestContext;
	}

	private void validateInput(RequestContext<String, String> requestContext) {
		if (StringUtils.isBlank(requestContext.getFormBean())) {
			requestContext.addMessage("reviewId",
					getMessage("review.id.field.missing", null));
		}
	}

	@Autowired
	private ReviewService reviewService;

	private static Logger logger = LoggerFactory
			.getLogger(DeleteReviewHelper.class);
}
