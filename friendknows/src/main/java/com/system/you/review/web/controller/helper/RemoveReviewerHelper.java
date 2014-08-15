package com.system.you.review.web.controller.helper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.exception.AttemptToRemoveOnlyReviewerException;
import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.reviewer.exception.RemoveReviewerException;

@Service
public class RemoveReviewerHelper extends ControllerHelper {

	public RequestContext<String[], String> removeReviewer(String requestId,
			String reviewerId) throws RemoveReviewerException {
		RequestContext<String[], String> responseBean = getResponseBean(
				requestId, reviewerId);
		validate(responseBean);
		if (!responseBean.containsMessage()) {
			try {
				Request request = requestService.get(requestId);
				if (request != null) {
					try {
						requestService.removeReviewer(requestId, reviewerId);
					} catch (AttemptToRemoveOnlyReviewerException ex) {
						responseBean.addMessage("page",
								getMessage("friend.last.error", null));
					} catch (Exception ex) {
						addSystemErrorMessage(responseBean);
					}
				}
			} catch (Exception ex) {
				addSystemErrorMessage(responseBean);
			}
		}
		return responseBean;
	}

	public RequestContext<String[], ReviewerViewBean> removeForwaredReviewer(
			String reviewerId, String requestId, String fwdReviewerId)
			throws RemoveReviewerException {
		RequestContext<String[], ReviewerViewBean> responseBean = new RequestContext<String[], ReviewerViewBean>(
				new String[] { requestId, reviewerId });
		validate(responseBean);
		if (!responseBean.containsMessage()) {
			try {
				Request request = requestService.get(requestId);
				if (request != null) {
					try {
						requestService.removeFwdReviewer(reviewerId, requestId,
								fwdReviewerId);
						Reviewer reviewer = reviewerService
								.getReviewer(reviewerId);
						responseBean.setViewBean(reviewerBeanHelper
								.dataToView(reviewer));
					} catch (Exception ex) {
						ex.printStackTrace();
						addSystemErrorMessage(responseBean);
					}
				}
			} catch (Exception ex) {
				addSystemErrorMessage(responseBean);
			}
		}
		return responseBean;
	}

	private RequestContext<String[], String> getResponseBean(String requestId,
			String reviewerId) {
		return new RequestContext<String[], String>(new String[] { requestId,
				reviewerId }, "");
	}

	private void validate(RequestContext<String[], ?> responseBean) {
		String requestId = responseBean.getFormBean()[0];
		String friends = responseBean.getFormBean()[1];
		// validate input for null/blank check
		if (StringUtils.isBlank(requestId)) {
			responseBean.addMessage("requestId",
					getMessage("request.id.field.missing", null));
		}
		// validate input for null/blank check
		if (StringUtils.isBlank(friends)) {
			responseBean.addMessage("friends",
					getMessage("friends.field.missing", null));
		}
	}

	@Autowired
	private RequestService requestService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	@Autowired
	private ReviewerService reviewerService;
}
