package com.system.you.review.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.you.review.request.dao.ReviewDAO;
import com.system.you.review.request.dao.RequestDAO;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.user.dao.ReviewUserDAO;

public class Database {

	public ReviewUserDAO getReviewUserDAO() {
		return reviewUserDAO;
	}
	public void setReviewUserDAO(ReviewUserDAO reviewUserDAO) {
		this.reviewUserDAO = reviewUserDAO;
	}
	public ReviewDAO getReviewDAO() {
		return reviewDAO;
	}
	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}
	public ReviewerDAO getReviewerRequestDAO() {
		return reviewerRequestDAO;
	}
	public void setReviewerRequestDAO(ReviewerDAO reviewerRequestDAO) {
		this.reviewerRequestDAO = reviewerRequestDAO;
	}
	public RequestDAO getReviewRequestDAO() {
		return reviewRequestDAO;
	}
	public void setReviewRequestDAO(RequestDAO reviewRequestDAO) {
		this.reviewRequestDAO = reviewRequestDAO;
	}
	@Autowired
	private ReviewUserDAO reviewUserDAO;
	@Autowired
	private ReviewDAO reviewDAO ;
	@Autowired
	private ReviewerDAO reviewerRequestDAO;
	@Autowired
	private RequestDAO reviewRequestDAO;
}
