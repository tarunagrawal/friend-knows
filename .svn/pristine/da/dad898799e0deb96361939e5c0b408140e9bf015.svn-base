package com.system.you.review.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.you.review.core.UserInterest;
import com.system.you.review.core.service.TagService;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.bean.helper.impl.UserBeanHelper;
import com.system.you.review.item.service.ItemSearchService;
import com.system.you.review.web.beans.view.NameValuePair;
import com.system.you.review.web.beans.view.UserInterestViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Controller
@RequestMapping(value = "item/")
public class ItemController extends ControllerSupport {

	@RequestMapping(value = "search/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection<NameValuePair> search(
			@RequestParam(required = true, value = "term") String item) {
		Collection<Item> results = search.searchDescription(item);
		return toNameValuePairs(item, results);
	}

	@RequestMapping(value = "{itemId}/friends/interested", method = RequestMethod.GET)
	public ModelAndView interestedFriends(@PathVariable String itemId, Model model) {
		List<UserInterest> interestedFriends = tagService.getInterestedFriends(
				itemId, SessionUtils.getRequestor().getFriendsIds());
		List<UserInterestViewBean> viewBeans = viewBean(interestedFriends);
		model.addAttribute("interestedFriends", viewBeans);
		return new ModelAndView("interestedFriends", model.asMap());
	}

	private List<UserInterestViewBean> viewBean(
			List<UserInterest> interestedFriends) {
		List<UserInterestViewBean> viewBeans = new ArrayList<UserInterestViewBean>();
		for (UserInterest userInterest : interestedFriends) {
			UserInterestViewBean userInterestViewBean = new UserInterestViewBean();
			userInterestViewBean.setItemId(userInterest.getItemId());
			userInterestViewBean.setTags(userInterest.getTags());
			userInterestViewBean.setToalReviews(userInterest.getToalReviews());
			userInterestViewBean.setUser(userBeanHelper.dataToView(userInterest
					.getUser()));
			viewBeans.add(userInterestViewBean);
		}
		return viewBeans;
	}

	private Collection<NameValuePair> toNameValuePairs(String term,
			Collection<Item> results) {
		Collection<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(nameValue(defaultItem(term)));
		for (Item item : results) {
			NameValuePair nameValue = nameValue(item);
			list.add(nameValue);
		}
		return list;
	}

	private NameValuePair nameValue(Item item) {
		NameValuePair nameValue = new NameValuePair(item.getDescription(),
				item.getId());
		return nameValue;
	}

	private Item defaultItem(String term) {
		Item item = new Item();
		item.setId("new:" + term);
		item.setDescription(term);
		return item;
	}

	@Autowired
	private ItemSearchService search;

	@Autowired
	private TagService tagService;

	@Autowired
	private UserBeanHelper userBeanHelper;
}
