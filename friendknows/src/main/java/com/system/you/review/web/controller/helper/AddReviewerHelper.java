package com.system.you.review.web.controller.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.MailService;
import com.system.you.review.core.service.exception.ReviewerAlreadyExist;
import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.ReviewerViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class AddReviewerHelper extends ControllerHelper {

	public RequestContext<String[], Set<ReviewerViewBean>> addReviewers(String requestId,
			String friends) {
		Set<ReviewerViewBean> reviewers = new HashSet<ReviewerViewBean>();
		RequestContext<String[], Set<ReviewerViewBean>> responseBean = getResponseBean(
				new String[] { requestId, friends }, reviewers);
		validateInputForBlank(requestId, friends, responseBean);
		if (!responseBean.containsMessage()) {
			try {
				Collection<Reviewer> newReviewers = requestService.addReviewers(requestId, friends);
				if (newReviewers != null && !newReviewers.isEmpty()) {
					// create view beans
					for (Reviewer reviewer : newReviewers) {
						reviewers.add(getViewBean(reviewer));
					}
					//send mail 
					mailService.sendMessage(requestService.get(requestId), newReviewers);
				} else {
					// some problem as reviewers are null- add error message
					addSystemErrorMessage(responseBean);
					log.error("error occured while adding reviewers (" + friends + ") to request "
							+ requestId);
				}
			} catch (ReviewerAlreadyExist ex) {
				addFriendAlreadyExistMessage(responseBean, ex);
			} catch (Exception ex) {
				addSystemErrorMessage(responseBean);
			}
		}
		return responseBean;
	}

	private RequestContext<String[], Set<ReviewerViewBean>> getResponseBean(String[] formBean,
			Set<ReviewerViewBean> reviewers) {
		return new RequestContext<String[], Set<ReviewerViewBean>>(formBean, reviewers);
	}

	private void addFriendAlreadyExistMessage(
			RequestContext<String[], Set<ReviewerViewBean>> responseBean, ReviewerAlreadyExist ex) {
		responseBean.addMessage("friend.already.exist",
				getMessage("friend.already.exist", new Object[] { getFriendName(ex) }));
	}

	private ReviewerViewBean getViewBean(Reviewer reviewer) {
		return reviewerBeanHelper.dataToView(reviewer);
	}

	private String getFriendName(ReviewerAlreadyExist ex) {
		return SessionUtils.getRequestor().getFacebookFriend(ex.getProviderId()).getName();
	}

	private void validateInputForBlank(String requestId, String friends,
			RequestContext<?, ?> responseBean) {
		// validate input for null/blank check
		if (StringUtils.isBlank(requestId)) {
			responseBean.addMessage("requestId", getMessage("request.id.field.missing", null));
		}
		// validate input for null/blank check
		if (StringUtils.isBlank(friends)) {
			responseBean.addMessage("friends", getMessage("friends.field.missing", null));
		}
	}

	@Autowired
	private MailService mailService ;
	
	@Autowired
	private RequestService requestService;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	private static Logger log = LoggerFactory.getLogger(AddReviewerHelper.class);
}
