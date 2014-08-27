package com.system.you.review.core.mail;

public class RequestAssignmentMailContent extends MailContent {

	public RequestAssignmentMailContent(String from, String[] recipient,
			String senderName, String category, String item) {
		super(from, recipient);
		this.category = category;
		this.item = item;
		this.senderName = senderName;
	}

	@Override
	public String getSubject() {
		return senderName + subjectText();
	}

	@Override
	public String getText() {
		return "Hi This is Text ";
	}

	private String subjectText() {
		return category + " " + item + " is looking for your help !";
	}

	private String senderName;

	private String category;

	private String item;
}
