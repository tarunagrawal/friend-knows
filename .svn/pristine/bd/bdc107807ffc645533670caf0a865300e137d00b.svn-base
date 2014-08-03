package com.system.you.review.web.security;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.system.you.review.user.service.ReviewUserService;

public class SimpleSocialUsersDetailService implements SocialUserDetailsService {

	private ReviewUserService reviewUserSerice;

	@Inject
	public SimpleSocialUsersDetailService(ReviewUserService reviewUserSerice) {
		this.reviewUserSerice = reviewUserSerice;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		return (SocialUserDetails)reviewUserSerice.loadUserByUsername(userId);
	}
	
}
