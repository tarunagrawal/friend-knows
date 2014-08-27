package com.system.you.review.core.mail;

import org.springframework.mail.javamail.MimeMessagePreparator;

public interface MailMessage {

	public static String FROM = "mail2tarunagrawal@gmail.com";
	
	public MimeMessagePreparator getMessage();
}
