package com.system.you.review.web.beans.view;

import java.util.List;

public class NotificationViewBean {
	
	public List<ReviewerViewBean> getAssignedRequest() {
		return assignedRequest;
	}
	
	public void setAssignedRequest(List<ReviewerViewBean> assignedRequest) {
		this.assignedRequest = assignedRequest;
	}
	
	public List<ReviewerViewBean> getAnsweredRequest() {
		return answeredRequest;
	}
	
	public void setAnsweredRequest(List<ReviewerViewBean> answeredRequest) {
		this.answeredRequest = answeredRequest;
	}
	
	private List<ReviewerViewBean> assignedRequest;
	private List<ReviewerViewBean> answeredRequest;
}
