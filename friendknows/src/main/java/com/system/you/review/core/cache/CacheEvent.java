package com.system.you.review.core.cache;

public class CacheEvent {

	private CacheEvent(Object bean, Operation operation){
		this.bean = bean;
		this.operation = operation;
	}
	
	public boolean isAddEvent(){
		if(this.operation == Operation.ADD){
			return true ;
		}
		return false;
	}
	
	public boolean isDeleteEvent(){
		if(this.operation == Operation.DELETE){
			return true ;
		}
		return false;
	}
	
	public boolean isModifyEvent(){
		if(this.operation == Operation.MODIFY){
			return true ;
		}
		return false;
	}
	
	public Object getBean(){
		return bean;
	}
	
	
	public static CacheEvent addEvent(Object bean){
		return new CacheEvent(bean, Operation.ADD);
	}
	
	public static CacheEvent deleteEvent(Object bean){
		return new CacheEvent(bean, Operation.DELETE);
	}
	
	public static CacheEvent modifyEvent(Object bean){
		return new CacheEvent(bean, Operation.MODIFY);
	}
	
	public enum Operation {
		ADD, DELETE, MODIFY;
	}
	
	private Object bean ;
	private Operation operation;
}
