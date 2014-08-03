package com.system.you.review.core.lock;

public class Resource {

	private Resource(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	public static Resource getResource(String identity) {
		return new Resource(identity);
	}

	private String identity;
}
