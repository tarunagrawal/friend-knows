package com.system.you.review.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.you.review.item.bean.Item;
import com.system.you.review.user.bean.ReviewUser;

public class TestSession {
	
	public ReviewUser getReviewee() {
		return reviewee;
	}
	
	public ReviewUser getReviewer() {
		return reviewer;
	}
	
	public Item getItem(){
		return item ;
	}
	
	public TestSession() {
	
	}
	
	private ReviewUser reviewee ;
	private ReviewUser reviewer ;
	private Item item;
	
	@Autowired
	private Database database; 
}
