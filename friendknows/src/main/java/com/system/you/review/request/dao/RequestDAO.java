package com.system.you.review.request.dao;

import java.util.List;

import com.system.you.review.item.bean.Item;
import com.system.you.review.request.bean.Request;
import com.system.you.review.user.bean.ReviewUser;

public interface RequestDAO {

	public void save(Request reviewRequest);

	public boolean close(String id);

	public Request get(String id);

	public List<Request> get(ReviewUser reviewee);

	public List<Request> get(Item item);

	public Request get(ReviewUser reviewee, Request parentRequest);

	public List<Request> all();
	
	public int totalRequest(ReviewUser user) ;
}
