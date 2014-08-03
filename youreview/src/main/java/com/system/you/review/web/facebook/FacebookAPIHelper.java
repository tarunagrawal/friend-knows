package com.system.you.review.web.facebook;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;

import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.domain.impl.SessionUtils;

public class FacebookAPIHelper {

	public static List<FacebookProfile> getFacebookFriends(Facebook facebook) {
		return facebook.friendOperations().getFriendProfiles();
	}

	public static List<ReviewUser> getConnectedFriends(List<String> providerIds) {
		ReviewUserService userService = SessionUtils.getServiceLocator()
				.getUserService();
		try {
			if (userService != null) {
				return userService.getUsersByProviderIds(providerIds);
			}
		} catch (Exception ex) {
			log.error("error occured while retrieving list of connected friend");
		}
		return null;
	}

	private static Logger log = LoggerFactory
			.getLogger(FacebookAPIHelper.class);

}
