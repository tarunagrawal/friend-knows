package com.system.you.review.core.mail;

public class RequestAssignmentMailContent extends MailContent {

	public RequestAssignmentMailContent(String from, String[] recipient,
			String senderName, String category, String item) {
		super(from, recipient);
		this.category = category;
		this.item = item;
		this.senderName = senderName;
	}

	public RequestAssignmentMailContent(String from, String[] recipient,
			String senderName, String category, String item, String description) {
		super(from, recipient);
		this.category = category;
		this.item = item;
		this.senderName = senderName;
		this.description = description;
	}

	@Override
	public String getSubject() {
		return senderName + subjectText();
	}

	@Override
	public String getText() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hi Friend, ");
		builder.append("\n");
		builder.append("\n");
		builder.append("I am seeking your opinion for " + getItem()
				+ " through friendknows.com");
		builder.append("\n");
		builder.append("\n");
		builder.append(" \"");
		builder.append(description);
		builder.append(" \"");
		builder.append("\n");
		builder.append("\n");
		builder.append("Please let me know your views !");
		builder.append("\n");
		builder.append("\n");
		builder.append("login to friendknows.com");
		builder.append("\n");
		builder.append("\n");
		builder.append("\n");
		builder.append("Regards");
		builder.append("\n");
		builder.append("Your friend");
		builder.append("\n");
		return builder.toString();
	}

	protected String getCategory() {
		return this.category;
	}

	protected String getItem() {
		return this.item;
	}

	private String subjectText() {
		return " is looking for your help for " + item ;
	}

	private String senderName;

	private String category;

	private String item;

	private String description;
}
