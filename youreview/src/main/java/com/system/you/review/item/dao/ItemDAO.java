package com.system.you.review.item.dao;

import java.util.List;

import com.system.you.review.item.bean.Item;

public interface ItemDAO {

	public void add(Item item);
	public Item getItem(String id);
	public Item getItemByDescription(String description);
	public List<Item> loadItemsWithHash(String hash);
	
}
