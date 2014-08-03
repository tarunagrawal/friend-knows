package com.system.you.review.request.bean;

import java.util.Set;

import org.springframework.core.style.ToStringCreator;

import com.system.you.review.user.bean.ReviewUser;

public class PropagatationChain {

	public String getReviewerRequestId() {
		return reviewerRequestId;
	}

	public void setReviewerRequestId(String reviewerRequestId) {
		this.reviewerRequestId = reviewerRequestId;
	}

	public String getPropagatedRequestId() {
		return propagatedRequestId;
	}

	public void setPropagatedRequestId(String propagatedRequestId) {
		this.propagatedRequestId = propagatedRequestId;
	}

	public ReviewUser getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(ReviewUser reviewUser) {
		this.reviewUser = reviewUser;
	}

	public Set<PropagatationChain> getChain() {
		return chain;
	}

	public void setChain(Set<PropagatationChain> chain) {
		this.chain = chain;
	}

	public String toString() {
		String user = new ToStringCreator(this).append("user",
				(reviewUser == null) ? "null" : reviewUser.getName())
				.toString()
				+ "->";
		if (chain != null) {
			for (PropagatationChain node : chain) {
				user = user + node.toString();
			}
		}
		return user;
	}

	private String reviewerRequestId;
	private String propagatedRequestId;
	private ReviewUser reviewUser;
	private Set<PropagatationChain> chain;
}
