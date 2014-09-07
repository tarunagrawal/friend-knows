/**
 * 
 */
package com.system.you.review.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.item.bean.helper.impl.UserBeanHelper;
import com.system.you.review.request.service.ServiceSupport;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.dao.ReviewUserDAO;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.domain.account.UsernameAlreadyInUseException;

public class ReviewUserServiceImpl extends ServiceSupport implements ReviewUserService {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String create(String mailID, String providerId, String providerUserId)
			throws UsernameAlreadyInUseException {
		if (reviewUserDAO.getUser(mailID) != null) {
			throw new UsernameAlreadyInUseException(mailID);
		}
		ReviewUser user = createReviewUser(mailID, providerId, providerUserId);
		reviewUserDAO.addUser(user);
		return user.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ReviewUser create(Map<String, String> attributes)
			throws UsernameAlreadyInUseException {
		ReviewUser user = userBeanHelper.formBean(attributes);
		reviewUserDAO.addUser(user);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExisting(String mailID) {
		boolean isExisting = false;
		try {
			ReviewUser user = getByMailId(mailID);
			isExisting = (user != null);
		} catch (UsernameNotFoundException ex) {
			isExisting = false;
		}
		return isExisting;
	}

	@Override
	@Transactional(readOnly = true)
	public ReviewUser getByMailId(String mailID)
			throws UsernameNotFoundException {
		return reviewUserDAO.getUser(mailID);
	}

	@Override
	@Transactional(readOnly = true)
	public SocialUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return getByMailId(username);
	}

	@Override
	@Transactional(readOnly = true)
	public ReviewUser getByProviderId(String providerId) {
		return reviewUserDAO.getUserByProviderId(providerId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String create(String mailID, String providerId,
			String providerUserId, String name)
			throws UsernameAlreadyInUseException {
		if (reviewUserDAO.getUser(mailID) != null) {
			throw new UsernameAlreadyInUseException(mailID);
		}
		ReviewUser user = createReviewUser(mailID, providerId, providerUserId);
		user.setName(name);
		reviewUserDAO.addUser(user);
		return user.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ReviewUser> getUsersByProviderIds(List<String> providerIds) {
		return reviewUserDAO.getUsersByProviderIds(providerIds);
	}

	private ReviewUser createReviewUser(String mailID, String providerId,
			String providerUserId) {
		ReviewUser user = new ReviewUser();
		user.setMailID(mailID);
		user.setProviderId(providerId);
		user.setProviderUserId(providerUserId);
		return user;
	}

	@Autowired
	private ReviewUserDAO reviewUserDAO;

	@Autowired
	private UserBeanHelper userBeanHelper;

}
