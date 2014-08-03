package com.system.you.review.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.category.service.CategoryService;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.dao.ItemDAO;
import com.system.you.review.item.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Item createItem(Item item) {
		if (item.getCategory() == null) {
			item.setCategory(categoryService.getDefaultCategory());
		}
		itemDAO.add(item);
		return item;
	}

	@Override
	@Transactional(readOnly = true)
	public Item getDefaultItem() {
		synchronized (this) {
			if (defaultItem == null) {
				Item item = itemDAO.getItemByDescription(DEFAULT_DESC);
				if (item == null) {
					item = new Item();
					item.setDescription(DEFAULT_DESC);
					item.setHashTag("default");
					item.setCategory(categoryService.getDefaultCategory());
					itemDAO.add(item);
				}
				defaultItem = item;
			}
		}
		return defaultItem;
	}

	public Item loadItemWithHash(String itemDescription) {
		Item item = null;
		String itemHashTag = null;
		if (itemDescription == null || itemDescription.isEmpty()) {
			return null;
		} else {
			// try to load already present item
			itemHashTag = getItemHashTag(itemDescription);
			if (itemHashTag != null && !itemHashTag.isEmpty()) {
				item = loadItemWithHashTag(itemHashTag);
				if (item == null) {
					item = createItemForHashTag(itemHashTag);
				}
			}
		}
		return item;
	}

	private Item createItemForHashTag(String itemHashTag) {
		Item item = new Item();
		item.setHashTag(itemHashTag);
		item.setDescription("Default Description");
		item.setCategory(categoryService.getDefaultCategory());
		itemDAO.add(item);
		return item;
	}

	private Item loadItemWithHashTag(String itemHashTag) {
		Item item = null;
		if (itemHashTag != null && !itemHashTag.isEmpty()) {
			List<Item> items = itemDAO.loadItemsWithHash(itemHashTag);
			if (items != null) {
				item = items.get(0);
			}
		}
		return item;
	}

	private String getItemHashTag(String itemDescription) {
		String stringStrartWithHashEndWithSpace = null;
		int indxOfHash = itemDescription.indexOf("#");
		if (indxOfHash > -1) {
			String stringStartsWithHash = itemDescription.substring(indxOfHash);
			stringStrartWithHashEndWithSpace = stringStartsWithHash.substring(
					0, stringStartsWithHash.indexOf(" "));
		}
		return stringStrartWithHashEndWithSpace;
	}

	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private CategoryService categoryService;

	public static Item defaultItem;
}
