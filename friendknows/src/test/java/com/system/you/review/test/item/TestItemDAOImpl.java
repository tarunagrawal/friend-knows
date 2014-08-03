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
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.dao.ItemDAO;
import com.system.you.review.test.IConstants;
import com.system.you.review.test.ReviewTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=IConstants.TEST_CONFIG_LOCATION)
public class TestItemDAOImpl extends ReviewTestCase{

	@Before
	public void setUp() throws Exception {
		log.info("setup is called");
	}

	@Test
	public void testAdd() {
		saveItem();
	}
	
	private Item saveItem(){
		Item item = new Item();
		item.setCategory(getCategory());
		item.setDescription(BOOK_NAME + getID());
		try{
			itemDAO.add(item);
			assertNotNull(item.getId());
			onSuccess(item.getId());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
		return item ;
	}
	
	public void testGetItem(){
		Item item = saveItem();
		try{
			Item readItem = itemDAO.getItem(item.getId());
			assertNotNull(readItem);
			assertEquals(readItem.getDescription(), item.getDescription());
		}catch(Exception ex){
			ex.printStackTrace();
			fail("should not throw exception");
		}
	}
	
	@Test
	public void testDelete() {
		assertNull(null);
	}
	
	@After
	public void cleanTestData(){
		clearTestData();
	}  
	private Category getCategory(){
		Category category = new Category();
		category.setId(CATEGORY_ID);
		return category;
	}
	
	@Override
	protected void clear(String id) {
		log.info("Item having ID:"+ id +" must be deleted from Item table");
	}
		
	@Autowired
	private ItemDAO itemDAO ;
	private static Logger log = LoggerFactory.getLogger(TestItemDAOImpl.class);
}
