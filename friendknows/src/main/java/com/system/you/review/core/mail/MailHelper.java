package com.system.you.review.core.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class MailHelper {

	public String getRecipientId(String providerId, String defaultMail) {
		ReviewUser reviewUser = userService.getByProviderId(providerId);
		if (reviewUser != null) {
			return reviewUser.getMailID();
		}
		return defaultMail;
	}
	
	
	public MailContent getAssignedMailContent(Request request) {
		String from = MailMessage.FROM;
		String senderName = request.getReviewee().getName();
		String[] recipients = getRecipient(getReviewersProviderIds(request));
		String category = request.getItem().getCategory().getDescription();
		String item = request.getItem().getDescription();
		MailContent mailContent = new RequestAssignmentMailContent(from,
				recipients, senderName, category, item);
		return mailContent;
	}
	
	private String[] getReviewersProviderIds(Request request) {
		String[] recipient = new String[request.getReviewers().size()];
		int count = 0;
		for (Reviewer reviewer : request.getReviewers()) {
			recipient[count++] = reviewer.getReviewerID();
		}
		return recipient;
	}

	private String[] getRecipient(String[] providerIds) {
		String[] recipients = new String[providerIds.length];
		int count = 0;
		for (String providerId : providerIds) {
			recipients[count++] = getRecipientId(providerId,
					getDefaultMail(providerId));
		}
		return recipients;
	}

	private String getDefaultMail(String providerId) {
		return SessionUtils.getRequestor().getFacebookFriend(providerId)
				.getName()
				+ "@facebook.com";
	}
	
	
	@Autowired
	private ReviewUserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
}
