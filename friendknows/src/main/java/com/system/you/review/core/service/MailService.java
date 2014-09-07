package com.system.you.review.core.service;

import java.util.Collection;

import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Reviewer;

public interface MailService {

	/**
	 * This method is use to send the message to all reviewers for this request.
	 * The implementation will take care of sending mails
	 * 
	 * @param request
	 * request object for that mails to be sent
	 **/
	public void sendMessage(Request request);

	public void sendMessage(Request request, Collection<Reviewer> reviewers);
	
	public void sendMessage(Review review, Reviewer reviewer);
}
