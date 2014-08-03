package com.system.you.review.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.you.review.item.bean.Item;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.dao.ReviewUserDAO;

public class TestUtil {

	public TestUtil(ReviewUserDAO aReviewUserDAO){
		initialize(aReviewUserDAO);
	}
	
	private void initialize(ReviewUserDAO aReviewUserDAO){
		session = new TestSession();
	}
	
	public ReviewUser getReviewee(){
		return session.getReviewee();
	}
	
	public ReviewUser getReviewer(){
		return session.getReviewer();
	}
	
	public Item getItem(){
		return session.getItem();
	}
	
	public static long getID(){
		long currentMilliSecond = System.currentTimeMillis();
		if(currentMilliSecond <= mLastID){
			++currentMilliSecond;
		}
		mLastID = currentMilliSecond;
		return currentMilliSecond;
	}
		
	private static long mLastID  ;
	//private static boolean initialized ;
	//private static TestUtil instance ;
	
	@Autowired
	private TestSession session ;
}
