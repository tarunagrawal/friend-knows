package com.system.you.review.web.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.UserBeanHelper;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.UserViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class SuggestionHelper extends ControllerHelper {

	public RequestContext<String, List<UserViewBean>> locationSuggestions(
			String location) {
		RequestContext<String, List<UserViewBean>> requestContext = new RequestContext<String, List<UserViewBean>>(
				"");
		try {
			if (StringUtils.isNotBlank(location)) {
				List<ReviewUser> friends = SessionUtils.getRequestor()
						.getFriendsWithLocation(location);
				List<UserViewBean> viewBean = new ArrayList<UserViewBean>();
				for (ReviewUser friend : friends) {
					viewBean.add(userBeanHelper.dataToView(friend));
				}
				requestContext.setViewBean(viewBean);
			}
		} catch (Exception ex) {
			addSystemErrorMessage(requestContext);
		}
		return requestContext;
	}

	@Autowired
	private UserBeanHelper userBeanHelper;
}
