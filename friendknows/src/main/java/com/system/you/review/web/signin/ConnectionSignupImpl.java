package com.system.you.review.web.signin;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;

import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.domain.account.UsernameAlreadyInUseException;
import com.system.you.review.web.domain.impl.SessionUtils;

public class ConnectionSignupImpl implements ConnectionSignUp {

	@Inject
	public ConnectionSignupImpl(ReviewUserService reviewUserService) {
		this.reviewUserService = reviewUserService;
	}

	@Override
	public String execute(Connection<?> connection) {
		String mailId = null;
		try {
			mailId = getAccountMailID(connection);
			if (!StringUtils.isBlank(mailId)) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Account is getting registerred with mail id:"
							+ mailId);
				}
				if (!alreadyPresent(mailId)) {
					return createNewUser(connection);
				} else {
					// user is already created in the system
					return mailId;
				}
			}
		} catch (UsernameAlreadyInUseException e) {
			LOGGER.error("Account with mail id " + mailId + " already exists");
			return mailId;
		} catch (Exception ex) {
			LOGGER.error(
					"Exception occured while registering the Account with mail id "
							+ mailId, ex);
			return null;
		}
		return null;
	}

	private boolean alreadyPresent(String mailId) {
		return reviewUserService.isExisting(mailId);
	}
	
	
	private String createNewUser(Connection<?> connection)
			throws UsernameAlreadyInUseException {
		ReviewUser reviewUser= reviewUserService.create(getAttributes(connection));
		SessionUtils.getRequestor().newUserSession(true);
		return StringUtils.isBlank(reviewUser.getId()) ? null : reviewUser.getMailID();
	}
	
	private Map<String,String> getAttributes(Connection<?> connection){
		Map<String, String> attributes = new HashMap<String, String>();
		FacebookProfile profile = getFacebookProfile(connection);
		attributes.put("providerUserName", profile.getUsername());
		attributes.put("mail", profile.getEmail());
		attributes.put("userName", profile.getName());
		attributes.put("providerUserId", connection.getKey().getProviderUserId());
		attributes.put("providerId", connection.getKey().getProviderId());
		return attributes; 
	}
	
	
	private FacebookProfile getFacebookProfile(Connection<?> connection){
		Object api = connection.getApi();
		if (api instanceof Facebook) {
			Facebook facebook = (Facebook) api;
			FacebookProfile profile = facebook.userOperations()
					.getUserProfile();
			return profile;
		}
		return null ;
	}
	
	// based on account type, fetch mail id
	private String getAccountMailID(Connection<?> connection) {
		Object api = connection.getApi();
		String mailID = null;
		if (api instanceof Facebook) {
			Facebook facebook = (Facebook) api;
			FacebookProfile profile = facebook.userOperations()
					.getUserProfile();
			mailID = profile.getEmail();
		}
		return mailID;
	}

	private final ReviewUserService reviewUserService;
	
	private final Logger LOGGER = LoggerFactory
			.getLogger(ConnectionSignupImpl.class);
}
