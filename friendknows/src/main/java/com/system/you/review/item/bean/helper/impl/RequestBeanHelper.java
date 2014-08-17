package com.system.you.review.item.bean.helper.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.Item;
import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.web.beans.form.RequestFormBean;
import com.system.you.review.web.beans.view.RequestViewBean;
import com.system.you.review.web.beans.view.ReviewerViewBean;

@Service
public class RequestBeanHelper extends BeanHelper {

	public Request formToData(RequestFormBean formBean) {
		Request dbBean = new Request();
		Date current = current();
		dbBean.setParentRequest(null);
		dbBean.setCreateDateTime(current);
		dbBean.setUpdateDateTime(current);
		dbBean.setDescription(formBean.getDescription());
		dbBean.setReviewee(currentUser());
		dbBean.setStatus(Request.Status.INITIATED);
		dbBean.setItem(item(formBean));
		Collection<Reviewer> collection = reviewerBeanHelper
				.formToData(reviewerBeanHelper.stringToForms(formBean
						.getFriends()));
		Set<Reviewer> reviewers = new HashSet<Reviewer>();
		reviewers.addAll(collection);
		dbBean.setReviewers(reviewers);
		return dbBean;
	}

	private Item item(RequestFormBean formBean) {
		return itemBeanHelper.formToData(formBean.getCategory(),
				formBean.getItem());
	}

	public RequestViewBean dataToView(Request dbBean, boolean verifiedReview) {
		checkNulls(dbBean);
		RequestViewBean viewBean = new RequestViewBean();
		viewBean.setId(dbBean.getRequestID());
		viewBean.setCreateDateTime(date(dbBean.getCreateDateTime()));
		viewBean.setUpdateDatetime(date(dbBean.getUpdateDateTime()));
		viewBean.setDescription(applyXSSFilter(dbBean.getDescription()));
		viewBean.setShortDescription(shortDescription(applyXSSFilter(dbBean
				.getDescription())));
		Item item = dbBean.getItem();
		viewBean.setItem(itemBeanHelper.dataToView(item));
		viewBean.setCategory(categoryBeanHelper.dataToView(item.getCategory()));
		Collection<Reviewer> reviewers = dbBean.getReviewers();
		List<ReviewerViewBean> reviewerViewBean = new ArrayList<ReviewerViewBean>();
		if (reviewers != null && !reviewers.isEmpty()) {
			for (Reviewer reviewer : reviewers) {
				reviewerViewBean.add(reviewerBeanHelper.dataToView(reviewer,
						verifiedReview));
			}
		}
		viewBean.setReviewers(reviewerViewBean);
		if (dbBean.getParentRequest() != null) {
			viewBean.setParentRequest(new RequestViewBean());
		}
		return viewBean;
	}

	public String shortDescription(String description) {
		if (StringUtils.isNotBlank(description)) {
			int length = description.length();
			if (shouldTruncate(length)) {
				return truncate(description);
			} else {
				return description;
			}
		}
		return "";
	}

	private String truncate(String description) {
		String chars = description.substring(0, 100);
		chars = chars.substring(0, chars.lastIndexOf(" "));
		return chars + "....";
	}

	private boolean shouldTruncate(int length) {
		return length > 100;
	}

	public RequestViewBean dataToView(Request dbBean) {
		return dataToView(dbBean, false);
	}

	@Autowired
	private ItemBeanHelper itemBeanHelper;

	@Autowired
	private CategoryBeanHelper categoryBeanHelper;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;
}
