package com.system.you.review.item.bean.helper.impl;

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

	private String nickName(ReviewUser dbBean) {
		return dbBean.getName();
	}
}
