package com.system.you.review.item.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.system.you.review.database.DAOSupport;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.dao.ItemDAO;

@SuppressWarnings("unchecked")
public class ItemDAOImpl extends DAOSupport<Item> implements ItemDAO {

	@Override
	public void add(Item item) {
		getSession().save(item);
	}

	@Override
	public Item getItem(String id) {
		return (Item) getSession().get(Item.class, id);
	}

	@Override
	public Item getItemByDescription(String description) {
		return (Item) getCriteria().add(
				Restrictions.eq("description", description)).uniqueResult();

	}

	@Override
	public List<Item> loadItemsWithHash(String hash) {
		return getCriteria().add(
				Restrictions.ilike("hashTag", hash, MatchMode.ANYWHERE)).list();
	}

	@Override
	public Class<Item> getEntity(){
		return Item.class;
	}

}
