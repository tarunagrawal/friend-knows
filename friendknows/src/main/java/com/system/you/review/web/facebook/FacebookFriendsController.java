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
			if (facebookProfile.getName().toLowerCase()
					.contains(search.trim().toLowerCase())) {
				results.add(facebookProfile);
			}
		}
		return toNameValuePairs(results);
	}

	@RequestMapping(value = "/location/search", method = RequestMethod.GET)
	public @ResponseBody
	Collection<NameValuePair> location(
			@RequestParam(required = true, value = "term") String search) {
		Collection<String> results = new ArrayList<String>();
		for (String location : getRequestor().getFriendLocations()) {
			if (location.toLowerCase().contains(search)) {
				results.add(location);
			}
		}
		return nameValue(results);
	}

	private Collection<NameValuePair> nameValue(Collection<String> results) {
		Collection<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String location : results) {
			NameValuePair nameValue = new NameValuePair(location, location);
			list.add(nameValue);
		}
		
		if(list.isEmpty()){
			NameValuePair nameValue = new NameValuePair("unknown", "unknown");
			list.add(nameValue);
		}
		
		return list;
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
