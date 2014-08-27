package com.system.you.review.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.request.service.RequestService;
import com.system.you.review.request.service.ReviewerService;
import com.system.you.review.web.domain.impl.SessionUtils;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends ControllerSupport {

	@RequestMapping(value = "/")
	public ModelAndView dashboard(Model model) {
		int totalRequest = -1;
		int pendingAnswer = -1;
		try {
			totalRequest = requestService.totalRequest(getLoggedInUser());
			pendingAnswer = reviewerService
					.getPendingAnswerCount(getLoggedInUser());
			boolean newUserSession = newUserSession();
			model.addAttribute("newUser", newUserSession);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		model.addAttribute("totalRequest", totalRequest);
		model.addAttribute("pendingAnswer", pendingAnswer);
		return new ModelAndView("dashboard", model.asMap());
	}

	private boolean newUserSession() {
		boolean newUserSession = false;
		if (SessionUtils.getRequestor().isNewUserSession()) {
			newUserSession = true;
			SessionUtils.getRequestor().newUserSession(false);
		}
		return newUserSession;
	}

	@Autowired
	private RequestService requestService;

	@Autowired
	private ReviewerService reviewerService;
}
