package com.system.you.review.core.mail;

public class RequestAnswerredMailContent extends RequestAssignmentMailContent {

	public RequestAnswerredMailContent(String from, String[] recipient,
			String senderName, String category, String item) {
		super(from, recipient, "", category, item);
		this.senderName = senderName;
	}

	public RequestAnswerredMailContent(String from, String[] recipient,
			String senderName, String category, String item, String description) {
		this(from, recipient, senderName, category, item);
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
		builder.append("\n");
		builder.append("I have submitted my opinion on " + getItem()
				+ " through friendknows.com");
		builder.append("\n");
		builder.append("\n");
		builder.append(" \"");
		builder.append(description);
		builder.append(" \"");
		builder.append("\n");
		builder.append("\n");
		builder.append("Please let me know if it helps !");
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

	private String subjectText() {
		return " has answerred your request for " + getItem() + " ";
	}

	private String senderName;

	private String description;
}
