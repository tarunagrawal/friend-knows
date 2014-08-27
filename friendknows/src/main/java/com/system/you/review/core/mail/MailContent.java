package com.system.you.review.core.mail;

public abstract class MailContent {

	public MailContent(String from, String[] recipient) {
		this.from = from;
		this.recipients = recipient;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

	public abstract String getSubject();

	
	public abstract String getText();

	
	private String from;

	private String[] recipients;
}
