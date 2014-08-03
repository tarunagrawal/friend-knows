package com.system.you.review.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;

import com.system.you.review.test.item.TestCategoryDAOImpl;
import com.system.you.review.test.item.TestItemDAOImpl;
import com.system.you.review.test.request.TestReviewDAOImpl;
import com.system.you.review.test.request.TestReviewRequestDAOImpl;
import com.system.you.review.test.request.TestReviewerRequestDAOImpl;
import com.system.you.review.test.user.TestReviewUserDAOImpl;


@RunWith(Suite.class)
@SuiteClasses({
	TestCategoryDAOImpl.class, 
	TestItemDAOImpl.class,
	TestReviewUserDAOImpl.class,
	TestReviewerRequestDAOImpl.class,
	TestReviewRequestDAOImpl.class,
	TestReviewDAOImpl.class})
@ContextConfiguration(locations=IConstants.TEST_CONFIG_LOCATION)
public class ReviewSystemDAOTestSuite {}
