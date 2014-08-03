package com.system.you.review.web.domain;

import java.util.List;
import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.social.facebook.api.FacebookProfile;

import com.system.you.review.user.bean.ReviewUser;

public interface Requestor {

	public String getId();

	public Locale getLocale();

	public ReviewUser getUser();

	public boolean isAuthenticated();

	public List<FacebookProfile> getFriends();

	public List<ReviewUser> getConnectedFriends();

	public String getContextPath();

	public FacebookProfile getFacebookFriend(String providerId);

	public void authenticateRequestor(Authentication authentication);

	public boolean isDestroyed();

	public void destroy();

	public List<String> getFriendsIds();

	public static String REQUESTOR_ID = "requestorID";

	public static String NEW_REQUESTOR = "";
}
