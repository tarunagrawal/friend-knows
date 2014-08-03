package com.system.you.review.test.user;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.system.you.review.test.IConstants;
import com.system.you.review.test.ReviewTestCase;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.dao.ReviewUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=IConstants.TEST_CONFIG_LOCATION)
public class TestReviewUserDAOImpl extends ReviewTestCase {

	@Before
	public void setup(){
		log.info("setUp is called");
	}
	
	@Test
	public void testAddUser(){
		ReviewUser reviewUser = new ReviewUser();
		reviewUser.setMailID(getID() +"@gmail.com");
		try{
			assertNull(reviewUser.getId());
			reviewUserDAO.addUser(reviewUser);
			assertNotNull(reviewUser.getId());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
	}
	
	public void clear(){
		log.info("clear is called");
	}
	@Override
	protected void clear(String id) {
		log.info("Review User with id:"+ id + " should be deleted");
	}

	@Autowired
	private ReviewUserDAO reviewUserDAO ;
	private static Logger log = LoggerFactory.getLogger(TestReviewUserDAOImpl.class);
}
