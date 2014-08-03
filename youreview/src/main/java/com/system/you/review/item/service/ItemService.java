package com.system.you.review.item.service;

import com.system.you.review.item.bean.Item;

public interface ItemService {
	
	public Item getDefaultItem();

	public Item createItem(Item item);

	public static String DEFAULT_DESC = "";
}
