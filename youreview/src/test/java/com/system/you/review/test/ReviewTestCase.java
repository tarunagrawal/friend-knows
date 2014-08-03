package com.system.you.review.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.you.review.item.bean.Item;
import com.system.you.review.item.dao.ItemDAO;
import com.system.you.review.request.bean.Review;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.dao.RequestDAO;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.dao.ReviewUserDAO;

public abstract class ReviewTestCase {
	
	public ReviewTestCase() {
		testSession = new TestUtil(null);
	}
	
	protected void onSuccess(String id){
		this.addedIds.add(id);
	}

	protected void clearTestData(){
		Iterator<String> iterator = addedIds.iterator();
		while(iterator.hasNext()){
			String addedID = iterator.next();
			clear(addedID);
			iterator.remove();
		}	
	}	
	
	@SuppressWarnings("static-access")
	public long getID(){
		return testSession.getID();
	}
	
	protected ReviewUser createReviewUser(String mailID){
		ReviewUser reviewUser = new ReviewUser() ;
		reviewUser.setMailID(mailID);
		reviewUserDAO.addUser(reviewUser);
		return reviewUser;
	}
	
	protected Request getReviewRequest(ReviewUser reviewee, Item item){
		Request reviewRequest = new Request();
		try{
			assertNotNull(item);
			assertNotNull(reviewee);
			Request.Status status = Request.Status.INITIATED;
			reviewRequest.setItem(item);
			reviewRequest.setReviewee(reviewee);
			reviewRequest.setStatus(status);
			reviewRequest.setParentRequest(null);
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
		return reviewRequest;
	}
	
	
	protected Reviewer getReviewerRequest(Request reviewRequest,ReviewUser reviewer,
																Reviewer.Channel channel){
		Reviewer reviewerRequest = new Reviewer();
		try{
			//reviewerRequest.setReviewer(reviewer);
			reviewerRequest.setRequest(reviewRequest);
			reviewerRequest.setChannel(channel);
			reviewerRequest.setStatus(Request.Status.INITIATED);
			assertNull(reviewerRequest.getRequestID());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
		return reviewerRequest;
	}
	
	protected Review getReview(Reviewer reviewerRequest){
		Review review = new Review();
		try{
			review.setItem(reviewerRequest.getRequest().getItem());
			review.setReviewDescription("Its awesome book, must read. It will transform your life.");
			review.setRating(10);
			//review.setReviewer(reviewerRequest.getReviewer());
			review.setReviewerRequestId(reviewerRequest.getRequestID());
			review.setVerified('N');
			assertNull(review.getReviewID());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
		return review ;
	}
	
	abstract protected void clear(String id);
	
	private List<String> addedIds = new ArrayList<String>();
	
	@Autowired
	protected ReviewUserDAO reviewUserDAO;
	@Autowired
	protected ReviewerDAO reviewerRequestDAO ;
	@Autowired
	protected RequestDAO reviewRequestDAO ;
	@Autowired
	protected ItemDAO itemDAO ;

	protected TestUtil testSession ;
	
	protected static final String REVIEWER = "reviewer@gmail.com";
	protected static final String ITEM = "402882883defe4d5013defe55f3c0000";
	protected static final String REVIEWEE = "review@gmail.com";
	protected static final String BOOK_NAME = "Geeta ! The Devine Book";
	protected static final String CATEGORY_ID = "402882883deb2fea013deb3062240000";
	protected static final String CATEGORY_BOOK = "Books";
}
