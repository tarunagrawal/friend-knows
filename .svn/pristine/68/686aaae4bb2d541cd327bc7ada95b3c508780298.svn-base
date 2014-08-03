package com.system.you.review.core;


public enum Tag {

	WORKS(0,"Works"), VALUE_FOR_MONEY(1, "Value For Money"), AWESOME(2, "Awesome"), HORRIBLE(3, "Horrible"), WORTH_IT(4, "Worth"), DEFAULT(-1, "Not Tagged");
	
	private Tag(int value, String view){
		this.value = value;
		this.view = view ;
	}

	public int getValue() {
		return value;
	}
	
	public String getName(){
		return this.toString();
	}

	public String getViewName(){
		return view;
	}
	public String getDescription(){
		return this.toString();
	}
	
	public static Tag getTag(int value){
		Tag ratingValue = null ;
		for (Tag rating : values()) {
			if(rating.getValue() == value){
				ratingValue = rating;
				break ;
			}
		}
		return ratingValue;
	}

	private int value ;
	private String view;

}
