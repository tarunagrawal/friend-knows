package com.system.you.review.core;

public class RatingTags {

	public RatingTags(String rankOne, String rankTwo,String rankThree){
		this.rankOne = rankOne;
		this.rankTwo = rankTwo;
		this.rankThree = rankThree;
	}
	
	public String getRankOne() {
		return rankOne;
	}

	public String getRankTwo() {
		return rankTwo;
	}

	public String getRankThree() {
		return rankThree;
	}

	private String rankOne ;
	private String rankTwo ;
	private String rankThree;
}
