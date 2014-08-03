package com.system.you.review.user.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.dao.ReviewUserDAO;

public class ReviewUserDAOImpl extends DAOSupport<ReviewUser> implements
		ReviewUserDAO {

	@Override
	public void addUser(ReviewUser user) {
		getSession().save(user);
	}

	@Override
	public ReviewUser getUser(String mailID) {
		return (ReviewUser) getCriteria()
				.add(Restrictions.eq("mailID", mailID)).uniqueResult();
	}

	@Override
	public ReviewUser getUserByProviderId(String providerId) {
		return (ReviewUser) getCriteria().add(
				Restrictions.eq("providerUserId", providerId)).uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewUser> getUsersByProviderIds(List<String> providerIds) {
		return (List<ReviewUser>)getCriteria().add(Restrictions.in("providerUserId", providerIds)).list();
	}

	@Override
	protected Class<ReviewUser> getEntity() {
		return ReviewUser.class;
	}
}
