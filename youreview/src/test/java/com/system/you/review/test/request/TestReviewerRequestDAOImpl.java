package com.system.you.review.test.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.system.you.review.item.bean.Item;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.test.IConstants;
import com.system.you.review.test.ReviewTestCase;
import com.system.you.review.user.bean.ReviewUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=IConstants.TEST_CONFIG_LOCATION)
public class TestReviewerRequestDAOImpl extends ReviewTestCase{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddReviewerRequest() {
		ReviewUser reviewee = testSession.getReviewee();
		Item item = testSession.getItem();
		ReviewUser reviewer = testSession.getReviewer();
		Request reviewRequest = saveReviewRequest(
											getReviewRequest(reviewee, item));
		saveReviewerRequest(reviewRequest,reviewer,Reviewer.Channel.MAIL);
	}
	
	@Test
	public void testGetReviewerRequest(){
		ReviewUser reviewee = testSession.getReviewee();
		Item item = testSession.getItem();
		ReviewUser reviewer = testSession.getReviewer();
		Request reviewRequest = saveReviewRequest(
											getReviewRequest(reviewee, item));
		Reviewer reviewerRequest = 
				saveReviewerRequest(reviewRequest,reviewer,Reviewer.Channel.MAIL);
		try{
			Reviewer persistedReviewerRequest = reviewerRequestDAO
												.getReviewer(reviewerRequest.getRequestID());
			assertNotNull(persistedReviewerRequest);
			assertEquals(reviewerRequest.getRequestID(), 
					persistedReviewerRequest.getRequestID());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
	}  
	
	@Test
	public void testGetReviewerRequestsByReviewer(){
		try {
			ReviewUser reviewee = testSession.getReviewee();
			Item item = testSession.getItem();
			ReviewUser reviewer = createReviewUser(getID() + "@gmail.com");
			Request reviewRequestOne = saveReviewRequest(getReviewRequest(
											reviewee, item));
			Request reviewRequestTwo = saveReviewRequest(getReviewRequest(
											reviewee, item));
			saveReviewerRequest(reviewRequestOne, reviewer,
											Reviewer.Channel.MAIL);
			saveReviewerRequest(reviewRequestTwo, reviewer,
											Reviewer.Channel.MAIL);
			List<Reviewer> list = reviewerRequestDAO.getReviewers(reviewer);
			assertNotNull(list);
			assertEquals(2, list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("should not throw exception");
		}
	}
	
	@Test
	public void testGetReviewerRequestsByReviewRequest(){
		try {
			ReviewUser reviewee = testSession.getReviewee();
			Item item = testSession.getItem();
			Request reviewRequest = saveReviewRequest(getReviewRequest(
					reviewee, item));
			ReviewUser reviewerOne = createReviewUser(getID() + "@gmail.com");
			ReviewUser reviewerTwo = createReviewUser(getID() + "@gmail.com");
			saveReviewerRequest(reviewRequest, reviewerOne,
											Reviewer.Channel.MAIL);
			saveReviewerRequest(reviewRequest, reviewerTwo,
											Reviewer.Channel.MAIL);
			List<Reviewer> list = reviewerRequestDAO.
											getReviewers(reviewRequest);
			assertNotNull(list);
			assertEquals(2, list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("should not throw exception");
		}
	}

	@Test
	public void testGetReviewerRequestsByChannel(){
		try {
			ReviewUser reviewee = testSession.getReviewee();
			Item item = testSession.getItem();
			Request reviewRequest = saveReviewRequest(getReviewRequest(
					reviewee, item));
			ReviewUser reviewerOne = createReviewUser(getID() + "@gmail.com");
			ReviewUser reviewerTwo = createReviewUser(getID() + "@gmail.com");
			List<Reviewer> requests = reviewerRequestDAO
											.getReviewers(Reviewer.Channel.SMS);
			int existingReviewerRequestsViaSMS = (requests != null) ? requests.size() : 0;
			saveReviewerRequest(reviewRequest, reviewerOne,
											Reviewer.Channel.MAIL);
			saveReviewerRequest(reviewRequest, reviewerTwo,
											Reviewer.Channel.SMS);
			saveReviewerRequest(reviewRequest, reviewerTwo,
											Reviewer.Channel.SMS);
			List<Reviewer> list = reviewerRequestDAO.
											getReviewers(Reviewer.Channel.SMS);
			assertEquals(existingReviewerRequestsViaSMS+2, list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("should not throw exception");
		}
	}
	
	private Request saveReviewRequest(Request reviewRequest){
		try{
			assertNull(reviewRequest.getRequestID());
			reviewRequestDAO.save(reviewRequest);
			assertNotNull(reviewRequest.getRequestID());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
		return reviewRequest;
	}
	
	private Reviewer saveReviewerRequest(Request reviewRequest,ReviewUser reviewer,
																		Reviewer.Channel channel){
		Reviewer reviewerRequest = null;
		try{
			reviewerRequest = getReviewerRequest(reviewRequest, reviewer, channel);
			reviewerRequestDAO.addReviewer(reviewerRequest);
			assertNotNull(reviewerRequest.getRequestID());
		}catch(Exception ex){
			fail("should not throw exception");
		}
		return reviewerRequest;
	}

	@Override
	protected void clear(String id) {
		// TODO Auto-generated method stub
	}
	
}
