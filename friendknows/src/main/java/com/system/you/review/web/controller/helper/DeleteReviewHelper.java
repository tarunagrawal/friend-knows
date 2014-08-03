package com.system.you.review.web.controller.helper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.request.bean.Review;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.review.ReviewDeleteException;

@Service
public class DeleteReviewHelper extends ControllerHelper {

	public RequestContext<String, String> delete(String reviewId)
			throws ReviewDeleteException {
		RequestContext<String, String> responseBean = new RequestContext<String, String>(
				reviewId);
		try {
			validateInput(reviewId, responseBean);
			if (!responseBean.containsMessage()) {
				Review deletedReview = reviewService.delete(reviewId);
				responseBean.setViewBean("deleted");
				if (deletedReview == null) {
					addSystemErrorMessage(responseBean);
				} 
			}
		} catch (Exception ex) {
			addSystemErrorMessage(responseBean);
		}
		return responseBean;
	}

	private void validateInput(String reviewId,
			RequestContext<String, String> responseBean) {
		if (StringUtils.isBlank(reviewId)) {
			responseBean.addMessage("reviewId",
					getMessage("review.id.field.missing", null));
		}
	}

	@Autowired
	private ReviewService reviewService;

	private static Logger logger = LoggerFactory
			.getLogger(DeleteReviewHelper.class);

}
