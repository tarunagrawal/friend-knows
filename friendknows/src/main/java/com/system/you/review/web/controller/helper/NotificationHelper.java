package com.system.you.review.web.controller.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.NotificationViewBean;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class NotificationHelper extends ControllerHelper {

	public RequestContext<String, NotificationViewBean> getDelta() {
		Date now = new Date();
		Requestor requestor = SessionUtils.getRequestor();
		Date lastRun = requestor.lastClientSync();
		RequestContext<String, NotificationViewBean> requestContext = new RequestContext<String, NotificationViewBean>(
				now.toString());
		try {
			ReviewUser user = requestor.getUser();
			NotificationViewBean viewBean = new NotificationViewBean();
			List<Reviewer> assigned = reviewerService.getReviewers(user,
					lastRun, now);
			List<Reviewer> answered = reviewerService.getAnswered(user,
					lastRun, now);
			viewBean.setAssignedRequest(getAssingedRequests(assigned));
			viewBean.setAnsweredRequest(getAnswered(answered));
			requestContext.setViewBean(viewBean);
			requestor.setLastClientSync(now);
		} catch (Exception e) {
			addSystemErrorMessage(requestContext);
		}

		return requestContext;

	}

	private List<ReviewerViewBean> getAssingedRequests(List<Reviewer> reviewers) {
		return getViewBeans(reviewers);
	}

	private List<ReviewerViewBean> getAnswered(List<Reviewer> reviewers) {
		return getViewBeans(reviewers);
	}

	private List<ReviewerViewBean> getViewBeans(List<Reviewer> reviewers) {
		if (reviewers != null && reviewers.size() > 0) {
			List<ReviewerViewBean> viewBeans = new ArrayList<ReviewerViewBean>();
			for (Reviewer reviewer : reviewers) {
				viewBeans.add(reviewerBeanHelper.dataToView(reviewer));
			}
			return viewBeans;
		}
		return new ArrayList<ReviewerViewBean>();
	}

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;
}
