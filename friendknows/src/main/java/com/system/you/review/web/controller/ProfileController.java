package com.system.you.review.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.item.bean.helper.impl.RequestBeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.service.ReviewService;
import com.system.you.review.web.beans.view.RequestViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

/**
 * Handles requests for the application profile page.
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends ControllerSupport {

	@RequestMapping("")
	@Secured(value = "USER_ROLE")
	public String profile(Model model) {
		model.addAttribute("user", SessionUtils.getRequestor().getUser());
		model.addAttribute("initiated", initiated(helper.reviewInitiated()));
		return "profile";
	}

	@RequestMapping("/reviews")
	@Secured(value = "USER_ROLE")
	public ModelAndView myReviews(Model model) {
		return new ModelAndView("myReviews");
	}

	private Collection<RequestViewBean> initiated(Iterable<Request> dbRequests) {
		List<RequestViewBean> viewBean = new ArrayList<RequestViewBean>();
		if (dbRequests != null) {
			for (Request dbRequest : dbRequests) {
				// null check to filter only initiated requests (not forward
				// one)
				if (dbRequest.getParentRequest() == null) {
					viewBean.add(requestBeanHelper.dataToView(dbRequest));
				}
			}
		}
		return viewBean;
	}

	@Autowired
	private ProfileHelper helper;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private RequestBeanHelper requestBeanHelper;
}
