package com.system.you.review.web.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.feedback.bean.Feedback;
import com.system.you.review.feedback.bean.FeedbackFormBean;
import com.system.you.review.feedback.bean.FeedbackViewBean;
import com.system.you.review.feedback.bean.helper.FeedbackBeanHelper;
import com.system.you.review.feedback.service.FeedbackService;
import com.system.you.review.web.beans.response.RequestContext;

@Service
public class FeedbackHelper extends ControllerHelper {

	public RequestContext<FeedbackFormBean, FeedbackViewBean> add(
			FeedbackFormBean formBean) {
		RequestContext<FeedbackFormBean, FeedbackViewBean> requestContext = new RequestContext<FeedbackFormBean, FeedbackViewBean>(
				formBean);
		validate(formBean, requestContext);
		if (!requestContext.containsMessage()) {
			try {
				Feedback feedback = feedbackService.add(formBean);
				if (feedback != null) {
					FeedbackViewBean viewBean = feedbackBeanHelper
							.dbToView(feedback);
					requestContext.setViewBean(viewBean);
				} else {
					addSystemErrorMessage(requestContext);
				}
			} catch (Exception ex) {
				addSystemErrorMessage(requestContext);
			}
		}

		return requestContext;
	}

	public RequestContext<String, List<FeedbackViewBean>> all() {
		RequestContext<String, List<FeedbackViewBean>> requestContext = new RequestContext<String, List<FeedbackViewBean>>(
				"All");
		try {
			List<Feedback> all = feedbackService.all();
			if (all != null) {
				List<FeedbackViewBean> viewBeans = new ArrayList<FeedbackViewBean>();
				for (Feedback feedback : all) {
					FeedbackViewBean viewBean = feedbackBeanHelper
							.dbToView(feedback);
					viewBeans.add(viewBean);
				}
				requestContext.setViewBean(viewBeans);
			} else {
				addSystemErrorMessage(requestContext);
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}

		return requestContext;
	}

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private FeedbackBeanHelper feedbackBeanHelper;
}
