package com.system.you.review.test.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.dao.CategoryDAO;
import com.system.you.review.test.IConstants;
import com.system.you.review.test.ReviewTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=IConstants.TEST_CONFIG_LOCATION)
public class TestCategoryDAOImpl extends ReviewTestCase{

	@Before
	public void setUp(){
		log.info("setup is called");
	}
	
	@Test
	public void testAddCategory(){
		createCategory(CATEGORY_BOOK+getID());
	}
	
	private Category createCategory(String categoryDesc){
		Category category = new Category();
		category.setDescription(categoryDesc);
		try{
			assertNull(category.getId());
			categoryDAO.add(category);
			assertNotNull(category.getId());
		}catch(Exception ex){
			fail("Should not throw exception");
		}
		return category;
	}
	
	@Test
	public void testGetCategory(){
		Category category = null ;
		try{
			category = createCategory(CATEGORY_BOOK+getID());
			assertNotNull(category.getId());
			Category readCategory =getCategory(category.getId());
			assertNotNull(readCategory.getId());
			assertEquals(readCategory.getDescription(),
									category.getDescription());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("Should not throw exception");
		}
	}
	
	private Category getCategory(String id){
		Category category = null ;
		try{
			category = categoryDAO.getCategory(id);
			assertNotNull(category.getId());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("Should not throw exception");
		}
		return category ;
	}
	
	@After
	public void cleanUp(){
		clearTestData();
	}
	
	@Override
	protected void clear(String id) {
		log.info("Category with id:" + id + " should be deleted");
	}
	
	@Autowired
	private CategoryDAO categoryDAO ;
	private static Logger log = LoggerFactory.getLogger(TestCategoryDAOImpl.class);
}
