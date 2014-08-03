package com.system.you.review.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.domain.account.UsernameAlreadyInUseException;

public interface ReviewUserService extends UserDetailsService {

	public String create(String mailID, String providerId,
			String providerUserId) throws UsernameAlreadyInUseException;

	public String create(String mailID, String providerId,
			String providerUserId, String name)
			throws UsernameAlreadyInUseException;

	public ReviewUser getByMailId(String mailID) throws UsernameNotFoundException;

	public boolean isExisting(String mailID);

	public ReviewUser getByProviderId(String providerId);
	
	public List<ReviewUser> getUsersByProviderIds(List<String> friends);
}
