package com.system.you.review.web;

import org.apache.commons.lang.StringUtils;

import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.domain.impl.SessionUtils;

public class URLGenerator {

	public static String getURL(String controllerURL, String viewURL){
		Requestor requestor =	SessionUtils.getRequestor();
		return StringUtils.join(new String[]{requestor.getContextPath(),controllerURL,viewURL});
	}
}
