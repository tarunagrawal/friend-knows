package com.system.you.review.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends ControllerSupport {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String homeOrProfile(ModelAndView modelAndView) {
		String viewName = viewName();
		return viewName;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	private String viewName() {
		return (getRequestor() == null) ? "home" : ((getRequestor()
				.isAuthenticated()) ? "redirect:/profile" : "home");
	}

}
