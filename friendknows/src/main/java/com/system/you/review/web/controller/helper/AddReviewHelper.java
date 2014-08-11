package com.system.you.review.web.controller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.ReviewBeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.beans.form.ReviewFormBean;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewViewBean;

@Service
public class AddReviewHelper extends ControllerHelper {

	public RequestContext<ReviewFormBean, ReviewViewBean> submit(
			ReviewFormBean formBean) {
		RequestContext<ReviewFormBean, ReviewViewBean> respBean = new RequestContext<ReviewFormBean, ReviewViewBean>(
				formBean);
		try {
			validate(formBean, respBean);
			if (!respBean.containsMessage()) {
				Reviewer reviewerRecord = reviewerService.getReviewer(
						formBean.getReviewerRequestId(), true);
				if (reviewerRecord != null) {
					Review review = reviewService.addReview(formBean,
							reviewerRecord);
					respBean.setViewBean(reviewBeanHelper.dbToView(review));
				} else {
					addSystemErrorMessage(respBean);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("exception occured while submitting review", ex);
			addSystemErrorMessage(respBean);
		}

		return respBean;
	}

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;

	private static Logger LOGGER = LoggerFactory
			.getLogger(AddReviewHelper.class);
}
