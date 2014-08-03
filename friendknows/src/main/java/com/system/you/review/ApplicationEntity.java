package com.system.you.review;

import java.util.Date;

public interface ApplicationEntity {

	public String getId();

	public String getDescription();

	public String getOwnerId();
	
	public String getName();

	public Date getCreateDateTime();
	
	public Date getUpdateDateTime();
	
	public static enum OWNER {
		SYSTEM("*");
		private String owner = null;

		private OWNER(String onwer) {
			this.owner = onwer;
		}

		public String getValue() {
			return owner;
		}
	}
}
