package com.system.you.review.web.search;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.you.review.category.bean.Category;
import com.system.you.review.item.bean.Item;

@Controller
@RequestMapping(value = "search/")
public class SearchController {

	@RequestMapping("")
	public String search() {
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			FullTextSession fullTextSession = Search
					.getFullTextSession(session);
			fullTextSession.createIndexer(Item.class).startAndWait();
			fullTextSession.createIndexer(Category.class).startAndWait();
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			txn.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "redirect:home";
	}

	@Autowired
	private SessionFactory sessionFactory;
}
