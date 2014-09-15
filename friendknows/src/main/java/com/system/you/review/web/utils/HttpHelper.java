package com.system.you.review.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class HttpHelper {

	public static String URLSeperartor = "/";

	public static String getHomeURL(HttpServletRequest aRequest) {
		String requestURL = aRequest.getRequestURL().toString();
		String context = aRequest.getContextPath();
		if (StringUtils.isNotBlank(context)) {
			//This code deals with local development where context path is used.
			return getHomeUrlWithContext(requestURL, context);
		}
		//Now, should reply on request's server 
		System.out.println(aRequest.getServerName());
		return "http://" +aRequest.getServerName();
	}

	private static String getHomeUrlWithContext(String requestURL,
			String context) {
		String homeURL = requestURL.substring(0,
				requestURL.indexOf(context));
		homeURL = StringUtils.join(new Object[] { homeURL, context });
		return homeURL;
	}
}
