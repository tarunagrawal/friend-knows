package com.system.you.review.database;

import org.hibernate.Session;

public class SessionContext{
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public boolean isExistingSession() {
		return existingSession;
	}
	public void setExistingSession(boolean existingSession) {
		this.existingSession = existingSession;
	}
	private Session session;
	private boolean existingSession;
}