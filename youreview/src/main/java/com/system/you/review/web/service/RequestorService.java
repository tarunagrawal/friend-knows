package com.system.you.review.web.service;

import com.system.you.review.web.domain.Requestor;

public interface RequestorService {

	public Requestor getRequestor(String id);

	public boolean addRequestor(Requestor requestor);

	public boolean removeRequestor(String id);

	public Requestor create(String suggestedName);

	public boolean exist(String id);
}
