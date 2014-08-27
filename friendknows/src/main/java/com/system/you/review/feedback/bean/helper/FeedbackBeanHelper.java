package com.system.you.review.feedback.bean.helper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.feedback.bean.Feedback;
import com.system.you.review.feedback.bean.FeedbackFormBean;
import com.system.you.review.feedback.bean.FeedbackViewBean;
import com.system.you.review.item.bean.helper.BeanHelper;
import com.system.you.review.item.bean.helper.impl.UserBeanHelper;

@Service
public class FeedbackBeanHelper extends BeanHelper {

	public Feedback formToDB(FeedbackFormBean formBean) {
		Feedback feedback = new Feedback();
		Date now = new Date();
		feedback.setCreateDateTime(now);
		feedback.setUpdateDateTime(now);
		feedback.setCategory(formBean.getCategory());
		feedback.setDescription(formBean.getDescription());
		feedback.setReviewUser(currentUser());
		return feedback;
	}

	public FeedbackViewBean dbToView(Feedback feedback) {
		FeedbackViewBean viewBean = new FeedbackViewBean();
		viewBean.setCategory(applyXSSFilter(feedback.getCategory()));
		viewBean.setDescription(applyXSSFilter(feedback.getDescription()));
		viewBean.setCreateDateTime(date(feedback.getCreateDateTime()));
		viewBean.setUpdateDateTime(date(feedback.getUpdateDateTime()));
		viewBean.setReviewUser(userBeanHelper.dataToView(feedback
				.getReviewUser()));
		return viewBean;
	}

	@Autowired
	private UserBeanHelper userBeanHelper;
}
