package com.system.you.review.core.mail;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.Item;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Review;
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

	public MailContent getAssignedMailContent(Request request,
			Set<Reviewer> reviewers) {
		String[] recipients = getRecipient(getReviewersProviderIds(reviewers));
		if (recipients != null && recipients.length > 0) {
			String from = MailMessage.FROM;
			String description = request.getDescription();
			String category = request.getItem().getCategory().getDescription();
			String item = request.getItem().getDescription();
			String senderName = request.getReviewee().getName();
			MailContent mailContent = new RequestAssignmentMailContent(from,
					recipients, senderName, category, item, description);
			return mailContent;
		}
		return null;
	}

	public MailContent getAssignedMailContent(Request request) {
		return getAssignedMailContent(request, request.getReviewers());
	}

	public MailContent getAnswerredMailContent(Review review, Reviewer reviewer) {
		String[] recipients = getRecipient(new String[] { reviewer.getRequest()
				.getReviewee().getProviderUserId() });
		if (recipients != null && recipients.length > 0) {
			String from = MailMessage.FROM;
			String description = review.getDescription();
			Item itemDB = reviewer.getRequest().getItem();
			String category = itemDB.getCategory().getDescription();
			String item = itemDB.getDescription();
			String senderName = review.getReviewer().getName();
			MailContent mailContent = new RequestAnswerredMailContent(from,
					recipients, senderName, category, item, description);
			return mailContent;
		}

		return null;
	}

	private String[] getReviewersProviderIds(Set<Reviewer> reviewers) {
		String[] recipient = new String[reviewers.size()];
		int count = 0;
		for (Reviewer reviewer : reviewers) {
			recipient[count++] = reviewer.getReviewerID();
		}
		return recipient;
	}

	private String[] getRecipient(String[] providerIds) {
		String[] recipients = new String[providerIds.length];
		int count = 0;
		for (String providerId : providerIds) {
			String mailId = getRecipientId(providerId,
					facebookMailId(providerId));
			if (StringUtils.isNotBlank(mailId)) {
				recipients[count++] = mailId;
			}
		}
		return recipients;
	}

	private String facebookMailId(String providerId) {
		FacebookProfile facebookFriend = SessionUtils.getRequestor()
				.getFacebookFriend(providerId);
		String fbUserName = "unknown";
		if (StringUtils.isNotBlank(fbUserName)) {
			return fbUserName + FB_MAIL;
		}
		return null;
	}

	@Autowired
	private ReviewUserService userService;

	@Autowired
	private JavaMailSender mailSender;

	private static String FB_MAIL = "@facebook.com";

}
