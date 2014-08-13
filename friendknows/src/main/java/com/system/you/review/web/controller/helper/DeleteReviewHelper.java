package com.system.you.review.web.controller.helper;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.review.ReviewDeleteException;

@Service
public class DeleteReviewHelper extends ControllerHelper {

	public RequestContext<String, ReviewerViewBean> delete(String reviewId)
			throws ReviewDeleteException {
		RequestContext<String, ReviewerViewBean> responseBean = new RequestContext<String, ReviewerViewBean>(
				reviewId);
		try {
			validateInput(reviewId, responseBean);
			if (!responseBean.containsMessage()) {
				Review deletedReview = reviewService.delete(reviewId);
				if (deletedReview != null) {
					Reviewer reviewer = reviewerService
							.getReviewer(deletedReview.getReviewerRequestId());
					// this is hook to cleanup data in same http interaction as
					// caches are not getting evicted
					cleanReviewerData(reviewId, reviewer);
					ReviewerViewBean viewBean = reviewerBeanHelper
							.dataToView(reviewer);
					responseBean.setViewBean(viewBean);
				} else {
					addSystemErrorMessage(responseBean);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("exception occured while deleting a review");
			addSystemErrorMessage(responseBean);
		}
		return responseBean;
	}

	private void cleanReviewerData(String reviewId, Reviewer reviewer) {
		Iterator<Review> iterator = reviewer.getReviews()
				.iterator();
		while (iterator.hasNext()) {
			Review bean = iterator.next();
			if (bean.getId().equalsIgnoreCase(reviewId)) {
				iterator.remove();
			}
		}
	}

	private void validateInput(String reviewId,
			RequestContext<String, ReviewerViewBean> responseBean) {
		if (StringUtils.isBlank(reviewId)) {
			responseBean.addMessage("reviewId",
					getMessage("review.id.field.missing", null));
		}
	}

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	private static Logger LOGGER = LoggerFactory
			.getLogger(DeleteReviewHelper.class);

}
