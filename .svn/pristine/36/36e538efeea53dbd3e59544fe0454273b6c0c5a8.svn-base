package com.system.you.review.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.system.you.review.category.bean.Category;
import com.system.you.review.category.service.CategorySearchService;
import com.system.you.review.web.beans.view.NameValuePair;

@Controller
@RequestMapping(value = "category/")
public class CategoryController extends ControllerSupport {

	@RequestMapping(value = "search/", method = RequestMethod.GET)
	public @ResponseBody
	Collection<NameValuePair> search(
			@RequestParam(required = true, value = "term") String item) {
		Collection<Category> results = search.search(item);
		return toNameValuePairs(results);
	}

	private Collection<NameValuePair> toNameValuePairs(Collection<Category> results) {
		Collection<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Category category : results) {
			NameValuePair nameValue = new NameValuePair(category.getDescription(),
					category.getId());
			list.add(nameValue);
		}
		return list;
	}
	
	@Autowired
	private CategorySearchService search;

}
