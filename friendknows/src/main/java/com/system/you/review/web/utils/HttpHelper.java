package com.system.you.review.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class HttpHelper {

	public static String URLSeperartor = "/"; 
	
	public static String getHomeURL(HttpServletRequest aRequest){
		String requestURL = aRequest.getRequestURL().toString();
		String context = aRequest.getContextPath();
		String homeURL = requestURL.substring(0,requestURL.indexOf(context));
		System.out.println(homeURL);
		homeURL = StringUtils.join(new Object[]{homeURL,context});
		return homeURL;
	}
}
