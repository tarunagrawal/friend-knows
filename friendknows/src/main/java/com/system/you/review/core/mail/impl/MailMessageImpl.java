package com.system.you.review.core.mail.impl;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.MimeMessagePreparator;

import com.system.you.review.core.mail.MailContent;
import com.system.you.review.core.mail.MailMessage;

public class MailMessageImpl implements MailMessage {

	public MailMessageImpl(MailContent mail) {
		this.mailContent = mail;
	}

	@Override
	public MimeMessagePreparator getMessage() {
		return new MimeMessagePreparator() {
			@Override
			public void prepare(final MimeMessage mimeMessage) throws Exception {
				message(mimeMessage);
			}
		};
	}

	private void message(final MimeMessage mimeMessage) throws Exception {
		mimeMessage.setFrom(getAddress(mailContent.getFrom()));
		mimeMessage.setSubject(mailContent.getSubject());
		mimeMessage.setRecipients(RecipientType.BCC, getRecipientAddress());
		mimeMessage.setText(mailContent.getText());
	}

	private Address[] getRecipientAddress() throws Exception {
		Address[] recipients = new InternetAddress[mailContent.getRecipients().length];
		int count = 0;
		for (String mail : mailContent.getRecipients()) {
			recipients[count++] = getAddress(mail);
		}
		return recipients;
	}

	private Address getAddress(String mail) throws Exception {
		return new InternetAddress(mail);
	}

	private MailContent mailContent;

}
