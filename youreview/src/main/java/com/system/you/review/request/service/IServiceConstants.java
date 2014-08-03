package com.system.you.review.request.service;

public interface IServiceConstants {
	 static interface IError{
		 public static String DATABASE_EXCEPTION = "service.dao.database.error";
		 public static String RUNTIME_EXCEPTION = "service.dao.runtime.error";
	 } 
}
