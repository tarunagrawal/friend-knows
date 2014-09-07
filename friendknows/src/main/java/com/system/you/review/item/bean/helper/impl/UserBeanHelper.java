package com.system.you.review.item.bean.helper.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.view.UserViewBean;

@Service
public class UserBeanHelper extends BeanHelper {

	// it binds DB data to view bean
	public UserViewBean dataToView(ReviewUser dbBean) {
		checkNulls(dbBean);
		UserViewBean viewBean = new UserViewBean();
		viewBean.setId(dbBean.getId());
		viewBean.setProviderId(dbBean.getProviderUserId());
		viewBean.setMailId(dbBean.getMailID());
		viewBean.setName(dbBean.getName());
		viewBean.setNickName(nickName(dbBean));
		viewBean.setImageUrl(profileImage(dbBean.getProviderUserId()));
		return viewBean;
	}

	//form bean
	public ReviewUser formBean(Map<String, String> attributes) {
		ReviewUser reviewUser = new ReviewUser();
		reviewUser.setMailID(attributes.get("mail"));
		reviewUser.setName(attributes.get("userName"));
		reviewUser.setProviderId(attributes.get("providerId"));
		reviewUser.setProviderUserId(attributes.get("providerUserId"));
		reviewUser.setProviderUserName(attributes.get("providerUserName"));
		return reviewUser;
	}

	private String nickName(ReviewUser dbBean) {
		return dbBean.getName();
	}
}
