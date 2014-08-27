package com.system.you.review.feedback.dao;

import java.util.List;

import com.system.you.review.feedback.bean.Feedback;

public interface FeedbackDAO {

	public void add(Feedback feedback);
	
	public List<Feedback> all() ;
}
