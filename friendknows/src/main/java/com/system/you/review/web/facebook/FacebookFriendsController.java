package com.system.you.review.web.facebook;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.system.you.review.web.beans.view.NameValuePair;
import com.system.you.review.web.controller.ControllerSupport;

@Controller
@RequestMapping(value = "/facebook")
public class FacebookFriendsController extends ControllerSupport {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody
	Collection<NameValuePair> search(
			@RequestParam(required = true, value = "term") String search) {
		Collection<FacebookProfile> results = new ArrayList<FacebookProfile>();
		for (FacebookProfile facebookProfile : getRequestor().getFriends()) {
			System.out.println("HomeTown" + ((facebookProfile.getHometown() != null) ?facebookProfile.getHometown().getName(): "UNKOWNN"));
			System.out.println("Location" +  ((facebookProfile.getLocation() != null) ?facebookProfile.getLocation().getName(): "UNKOWNN"));
			if (facebookProfile.getName().toLowerCase()
					.contains(search.trim().toLowerCase())) {
				results.add(facebookProfile);
			}
		}
		return toNameValuePairs(results);
	}

	private Collection<NameValuePair> toNameValuePairs(
			Collection<FacebookProfile> results) {
		Collection<NameValuePair> list = new ArrayList<NameValuePair>();
		for (FacebookProfile facebook : results) {
			NameValuePair nameValue = new NameValuePair(facebook.getName(),
					facebook.getId());
			list.add(nameValue);
		}
		return list;
	}

}
