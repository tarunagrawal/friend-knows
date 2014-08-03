package com.system.you.review.web.signin;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;

import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.domain.account.UsernameAlreadyInUseException;

public class ConnectionSignupImpl implements ConnectionSignUp {

	@Inject
	public ConnectionSignupImpl(ReviewUserService reviewUserService){
		this.reviewUserService = reviewUserService;
	}
	
	@Override
	public String execute(Connection<?> connection) {
		String mailId = null;	
		try {
			mailId = getAccountMailID(connection);
			if (!StringUtils.isBlank(mailId)) {
				if(logger.isDebugEnabled()){
					logger.debug("Account is getting registerred with mail id:" + mailId);
				}
				ConnectionKey connectionKey = connection.getKey();
				String providerId = connectionKey.getProviderId();
				String providerUserId = connectionKey.getProviderUserId();
				String name = getAccountName(connection);
				String id = reviewUserService.create(mailId,providerId,providerUserId,name);
				return id != null ? mailId : null;
			}
		} catch (UsernameAlreadyInUseException e) {
			logger.error("Account with mail id " + mailId
					+ " already exists");
			return mailId;
		} catch (Exception ex) {
			logger.error("Exception occured while registering the Account with mail id " + mailId,
					ex);
			return null;
		}
		return null ;
	}

	//based on account type, fetch mail id
	private String getAccountMailID(Connection<?> connection){
		Object api = connection.getApi();
		String mailID = null;
		if(api instanceof Facebook){
			Facebook facebook = (Facebook)api;
			FacebookProfile profile = facebook.userOperations().getUserProfile();
			mailID = profile.getEmail();
		}	
		return mailID;
	}
	
	//based on account type, fetch mail id
		private String getAccountName(Connection<?> connection){
			Object api = connection.getApi();
			String name = null;
			if(api instanceof Facebook){
				Facebook facebook = (Facebook)api;
				FacebookProfile profile = facebook.userOperations().getUserProfile();
				name = profile.getName();
			}	
			return name;
		}
	
	private final ReviewUserService reviewUserService;
	private final Logger logger  = LoggerFactory.getLogger(ConnectionSignupImpl.class);
}
