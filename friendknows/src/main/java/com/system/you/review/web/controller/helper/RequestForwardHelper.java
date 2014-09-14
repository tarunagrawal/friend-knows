package com.system.you.review.web.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.UserInterest;
import com.system.you.review.core.service.MailService;
import com.system.you.review.core.service.TagService;
import com.system.you.review.core.service.exception.ReviewerAlreadyExist;
import com.system.you.review.item.bean.helper.impl.RequestBeanHelper;
import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.item.bean.helper.impl.UserBeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.beans.form.ReviewForwardFormBean;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.beans.view.UserInterestViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class RequestForwardHelper extends ControllerHelper {

	public RequestContext<String[], List<UserInterestViewBean>> form(
			String requestId, String reviewerId) {
		RequestContext<String[], List<UserInterestViewBean>> requestContext = new RequestContext<String[], List<UserInterestViewBean>>(
				new String[] { requestId, reviewerId });
		try{
			Request request = requestService.get(requestId);
			List<UserInterest> interestedFriends = tagService.getInterestedFriends(
					request.getItem().getId(), SessionUtils.getRequestor().getFriendsIds());
			List<UserInterestViewBean> viewBeans = viewBean(interestedFriends);
			requestContext.setViewBean(viewBeans);
		}catch(Exception ex){
			addSystemErrorMessage(requestContext);
		}
		return requestContext;
	}

	public RequestContext<ReviewForwardFormBean, ReviewerViewBean> submit(
			ReviewForwardFormBean formBean) {
		RequestContext<ReviewForwardFormBean, ReviewerViewBean> requestContext = new RequestContext<ReviewForwardFormBean, ReviewerViewBean>(
				formBean);
		validate(formBean, requestContext);
		if (!requestContext.containsMessage()) {
			Request fwdRequest = null;
			try {
				fwdRequest = requestService.forwardRequest(formBean);
				if (fwdRequest != null) {
					// retrieving the request again as it should have all the
					// nested object
					// fwdRequest = requestService.get(fwdRequest.getId());
					Reviewer reviewer = reviewerService.getReviewer(formBean
							.getReviewerRequestId());
					ReviewerViewBean viewBean = getViewBean(reviewer);
					requestContext.setViewBean(viewBean);
					mailService.sendMessage(fwdRequest);
				} else {
					addSystemErrorMessage(requestContext);
				}
			} catch (ReviewerAlreadyExist ex) {
				addFriendAlreadyExistMessage(requestContext, ex);
			} catch (Exception ex) {
				addSystemErrorMessage(requestContext);
				LOGGER.error("error while forwarding request", ex);
			}
		}
		return requestContext;
	}

	private List<UserInterestViewBean> viewBean(
			List<UserInterest> interestedFriends) {
		List<UserInterestViewBean> viewBeans = new ArrayList<UserInterestViewBean>();
		for (UserInterest userInterest : interestedFriends) {
			UserInterestViewBean userInterestViewBean = new UserInterestViewBean();
			userInterestViewBean.setItemId(userInterest.getItemId());
			userInterestViewBean.setTags(userInterest.getTags());
			userInterestViewBean.setToalReviews(userInterest.getToalReviews());
			userInterestViewBean.setUser(userBeanHelper.dataToView(userInterest
					.getUser()));
			viewBeans.add(userInterestViewBean);
		}
		return viewBeans;
	}
	
	private ReviewerViewBean getViewBean(Reviewer reviewer) {
		return reviewerBeanHelper.dataToView(reviewer);
	}

	private void addFriendAlreadyExistMessage(
			RequestContext<ReviewForwardFormBean, ReviewerViewBean> requestContext,
			ReviewerAlreadyExist ex) {
		requestContext.addMessage(
				"friend.already.exist",
				getMessage("friend.already.exist",
						new Object[] { getFriendName(ex) }));
	}

	private String getFriendName(ReviewerAlreadyExist ex) {
		return SessionUtils.getRequestor()
				.getFacebookFriend(ex.getProviderId()).getName();
	}

	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserBeanHelper userBeanHelper;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestBeanHelper requestBeanHelper;

	@Autowired
	private ReviewerService reviewerService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	private static Logger LOGGER = LoggerFactory
			.getLogger(RequestForwardHelper.class);

}
