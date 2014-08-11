package com.system.you.review.item.bean.helper.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.Item;
import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Request.Status;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.beans.form.ReviewerFormBean;
import com.system.you.review.web.beans.view.ReviewViewBean;
import com.system.you.review.web.beans.view.ReviewerViewBean;

@Service
public class ReviewerBeanHelper extends BeanHelper {

	public Collection<Reviewer> formToData(
			Collection<ReviewerFormBean> formBeans) {
		Collection<Reviewer> dbBeans = new HashSet<Reviewer>();
		for (ReviewerFormBean formBean : formBeans) {
			dbBeans.add(formToData(formBean));
		}
		return dbBeans;
	}

	public Reviewer formToData(ReviewerFormBean formBean) {
		checkNulls(formBean);
		Reviewer reviewer = new Reviewer();
		reviewer.setChannel(Reviewer.Channel.MAIL);
		reviewer.setReviewerID(formBean.getReviewerProviderId());
		reviewer.setCreateDateTime(current());
		reviewer.setUpdateDateTime(current());
		reviewer.setStatus(Request.Status.INITIATED);
		return reviewer;
	}

	public ReviewerFormBean formBean(String providerId) {
		ReviewerFormBean formBean = new ReviewerFormBean();
		formBean.setReviewerProviderId(providerId);
		formBean.setName(name(providerId));
		formBean.setExisting(false);
		formBean.setMailId(mail(providerId));
		formBean.setContactNumber(contactNumber(providerId));
		return formBean;
	}

	public Collection<ReviewerFormBean> stringToForms(String friends) {
		checkBlank(friends);
		Collection<ReviewerFormBean> fromBeans = new HashSet<ReviewerFormBean>();
		Collection<String> ids = friendProviderIds(friends);
		for (String providerId : ids) {
			fromBeans.add(formBean(providerId));
		}
		return fromBeans;
	}

	public ReviewerViewBean dataToView(Reviewer dbBean, boolean onlyVerified) {
		checkNulls(dbBean);
		ReviewerViewBean viewBean = new ReviewerViewBean();
		Item item = dbBean.getRequest().getItem();
		viewBean.setId(dbBean.getRequestID());
		viewBean.setRequestId(dbBean.getRequest().getRequestID());
		viewBean.setStatus(getStatusDescription(dbBean.getStatus()));
		viewBean.setItem(itemBeanHelper.dataToView(item));
		viewBean.setCategory(categoryBeanHelper.dataToView(item.getCategory()));
		ReviewUser user = user(dbBean);
		viewBean.setUser(userBeanHelper.dataToView(user));
		viewBean.setInitiatedUser(userBeanHelper.dataToView(dbBean.getRequest()
				.getReviewee()));
		Set<Review> directReplies = dbBean.getReviews();
		viewBean.setReviews(reviews(directReplies, onlyVerified));
		if (directReplies != null && directReplies.size() > 0) {
			viewBean.setShowReply(false);
		}
		viewBean.setCreateDateTime(date(dbBean.getCreateDateTime()));
		viewBean.setRequestDescription(dbBean.getRequest().getDescription());
		if (dbBean.getStatus().equals(Request.Status.PROPAGATED)) {
			Request request = dbBean.getRequest();
			Request forwardRequest = null;
			try {
				forwardRequest = requestService.forwardRequest(user, request);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (forwardRequest != null) {
				viewBean.setForwardRequest(requestBeanHelper.dataToView(
						forwardRequest, true));
			}
		}

		return viewBean;

	}

	public ReviewerViewBean dataToView(Reviewer dbBean) {
		return dataToView(dbBean, false);
	}

	public String getStatusDescription(Status status) {
		String description = "";
		switch (status) {
		case INITIATED:
			description = "Pending Answer";
			break;

		case PROPAGATED:
			description = "Forwarded to friends";
			break;

		case ANSWERED:
			description = "Answered";
			break;

		case ASWERED_FORWARED:
			description = "Answered and forwared to friends";
			break;
		default:
			description = status.toString();
			break;
		}

		return description;
	}

	private Collection<String> friendProviderIds(String friends) {
		return Arrays.asList(friends.split(","));
	}

	private Collection<ReviewViewBean> reviews(Collection<Review> reviews,
			boolean onlyVerified) {
		Collection<ReviewViewBean> reviewViewBeans = new ArrayList<ReviewViewBean>();
		if (reviews != null && !reviews.isEmpty()) {
			for (Review review : reviews) {
				if (!onlyVerified) {
					reviewViewBeans.add(reviewBeanHelper.dbToView(review));
				} else if (review.getVerified() == 'Y') {
					reviewViewBeans.add(reviewBeanHelper.dbToView(review));
				}
			}
		}
		return reviewViewBeans;
	}

	private ReviewUser user(Reviewer dbBean) {
		ReviewUser user = reviewUserService.getByProviderId(dbBean
				.getReviewerID());
		if (user == null) {
			// means external user.
			user = user(dbBean.getReviewerID());
		}
		return user;
	}

	@Autowired
	private ReviewBeanHelper reviewBeanHelper;

	@Autowired
	private UserBeanHelper userBeanHelper;

	@Autowired
	private ReviewUserService reviewUserService;

	@Autowired
	private ItemBeanHelper itemBeanHelper;

	@Autowired
	private CategoryBeanHelper categoryBeanHelper;

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestBeanHelper requestBeanHelper;

}
