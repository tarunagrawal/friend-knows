package com.system.you.review.web.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.ReviewBeanHelper;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class UserReviewHelper extends ControllerHelper {

	public RequestContext<String, List<ReviewViewBean>> currentUserReviews() {
		RequestContext<String, List<ReviewViewBean>> requestContext = new RequestContext<String, List<ReviewViewBean>>(
				"");
		try {
			List<Review> reviews = reviewService.getReviews(SessionUtils
					.getRequestor().getUser());
			if (reviews != null && !reviews.isEmpty()) {
				List<ReviewViewBean> viewBean = new ArrayList<ReviewViewBean>();
				for (Review review : reviews) {
					viewBean.add(reviewBeanHelper.dbToView(review, true));
				}
				requestContext.setViewBean(viewBean);
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}

		return requestContext;
	}

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;
}
