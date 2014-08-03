package com.system.you.review.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.web.beans.view.ReviewerViewBean;

@Controller
@RequestMapping("/request")
public class PendingRequestController extends ControllerSupport {

	@RequestMapping("/assigned")
	@Secured(value = "USER_ROLE")
	public String assignedRequest(Model model) {
		model.addAttribute("pending", pending(helper.assignedRequests()));
		return "pendingRequests";
	}

	private Object pending(Iterable<Reviewer> assignedRequests) {
		Collection<ReviewerViewBean> pending = new ArrayList<ReviewerViewBean>();
		if (assignedRequests != null) {
			for (Reviewer dbBean : assignedRequests) {
				pending.add(reviewerBeanHelper.dataToView(dbBean));
			}
		}
		return pending;
	}

	@Autowired
	private ProfileHelper helper;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

}
