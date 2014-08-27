package com.system.you.review.feedback.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.feedback.bean.Feedback;
import com.system.you.review.feedback.dao.FeedbackDAO;

@Service
public class FeedbackDAOImpl extends DAOSupport<Feedback> implements FeedbackDAO {

	@Override
	public void add(Feedback feedback) {
		getSession().saveOrUpdate(feedback);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> all() {
		return getCriteria().list();
	}

	@Override
	protected Class<Feedback> getEntity() {
		return Feedback.class;
	}

}
