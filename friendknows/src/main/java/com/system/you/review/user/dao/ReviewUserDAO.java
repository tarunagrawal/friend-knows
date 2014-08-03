package com.system.you.review.user.dao;

import java.util.List;

import com.system.you.review.user.bean.ReviewUser;

public interface ReviewUserDAO {
	
	public void addUser(ReviewUser user);

	public ReviewUser getUser(String mailID);

	public ReviewUser getUserByProviderId(String providerId);

	public List<ReviewUser> getUsersByProviderIds(List<String> providerId);
}
