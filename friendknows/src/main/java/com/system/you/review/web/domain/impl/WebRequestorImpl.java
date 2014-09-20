package com.system.you.review.web.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.security.SocialAuthenticationToken;

import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.facebook.FacebookAPIHelper;
import com.system.you.review.web.utils.HttpHelper;

public class WebRequestorImpl implements Requestor {

	public WebRequestorImpl(String id) {
		this.id = id;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public ReviewUser getUser() {
		return reviewUser;
	}

	public ReviewUser getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(ReviewUser reviewUser) {
		this.reviewUser = reviewUser;
	}

	@Override
	public boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication()
				.isAuthenticated();
	}

	@Override
	public List<FacebookProfile> getFriends() {
		return friends;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public FacebookProfile getFacebookFriend(String providerId) {
		FacebookProfile facebookProfile = null;
		if (StringUtils.isNotBlank(providerId)) {
			for (FacebookProfile profile : friends) {
				if (profile.getId().equalsIgnoreCase(providerId)) {
					facebookProfile = profile;
					break;
				}
			}
		}
		return facebookProfile;
	}

	public static Requestor createRequestor(String id) {
		WebRequestorImpl requestor = new WebRequestorImpl(id);
		return requestor;
	}

	@Override
	public boolean isDestroyed() {
		return this.destroy;
	}

	@Override
	public void destroy() {
		this.destroy = true;
	}

	@Override
	public List<String> getFriendsIds() {
		return connectedFriendIds;
	}

	public void authenticateRequestor(Authentication authentication) {
		this.facebook = getFacebookApi(authentication);
		this.friends = FacebookAPIHelper.getFacebookFriends(facebook);
		this.reviewUser = (ReviewUser) authentication.getPrincipal();
		this.contextPath = getHomeURL();
		this.locale = SessionUtils.getCurrentRequest().getLocale();
		this.connectedFriendIds = getConnectedFriendsIds();
		this.connectedFriendIds.add(reviewUser.getProviderUserId());
		this.connectedFriends = getConnectedFacebookFriends();
		this.connectedFriends.add(this.reviewUser);
		setLastClientSync(new Date());
	}

	private Map<String, List<FacebookProfile>> friendLocations() {
		Map<String, List<FacebookProfile>> locations = new HashMap<String, List<FacebookProfile>>();
		locations.put("", null);
		locations.put("General", null);
		for (FacebookProfile friend : this.friends) {
			Reference location = friend.getHometown();
			if (location != null) {
				if (locations.containsKey(location.getName())) {
					locations.get(location.getName()).add(friend);
				} else {
					List<FacebookProfile> list = new ArrayList<FacebookProfile>();
					list.add(friend);
					locations.put(location.getName(), list);
				}
			}
			location = friend.getLocation();
			if (location != null) {
				if (locations.containsKey(location.getName())) {
					locations.get(location.getName()).add(friend);
				} else {
					List<FacebookProfile> list = new ArrayList<FacebookProfile>();
					list.add(friend);
					locations.put(location.getName(), list);
				}
			}
		}
		return locations;
	}

	public boolean isFriend(String providerId) {
		return this.connectedFriendIds.contains(providerId);
	}

	private String getHomeURL() {
		return HttpHelper.getHomeURL(SessionUtils.getCurrentRequest());
	}

	private List<ReviewUser> getConnectedFacebookFriends() {
		return FacebookAPIHelper.getConnectedFriends(getConnectedFriendsIds());
	}

	private PagedList<String> getConnectedFriendsIds() {
		return facebook.friendOperations().getFriendIds();
	}

	private Facebook getFacebookApi(Authentication authentication) {
		return ((Facebook) ((SocialAuthenticationToken) authentication)
				.getConnection().getApi());
	}

	@Override
	public List<ReviewUser> getConnectedFriends() {
		return connectedFriends;
	}

	@Override
	public Date lastClientSync() {
		return lastClientSync;
	}

	@Override
	public void setLastClientSync(Date date) {
		this.lastClientSync = date;
	}

	@Override
	public void newUserSession(boolean session) {
		newUserSession = session;

	}

	@Override
	public boolean isNewUserSession() {
		return newUserSession;
	}

	@Override
	public Set<String> getFriendLocations() {
		if (friendLocations == null) {
			friendLocations = friendLocations();
		}
		return friendLocations.keySet();
	}

	@Override
	public List<ReviewUser> getFriendsWithLocation(String location) {
		List<ReviewUser> friends = new ArrayList<ReviewUser>();
		// ideally this should not happen.
		if (friendLocations == null) {
			friendLocations = friendLocations();
		}
		Set<String> locations = friendLocations.keySet();
		for (String place : locations) {
			if (location.equalsIgnoreCase(place)) {
				List<FacebookProfile> profiles = friendLocations.get(place);
				for (FacebookProfile facebookProfile : profiles) {
					friends.add(SessionUtils.getServiceLocator()
							.getUserBeanHelper()
							.socialToDB(facebookProfile.getId()));
				}
			}
		}
		return friends;
	}

	private String id;
	private Locale locale;
	private ReviewUser reviewUser;
	private List<FacebookProfile> friends;
	private List<ReviewUser> connectedFriends;
	private List<String> connectedFriendIds;
	private String contextPath;
	private Facebook facebook;
	private boolean destroy;
	private Date lastClientSync;
	private boolean newUserSession;
	private Map<String, List<FacebookProfile>> friendLocations;

}
