package com.system.you.review.database;

public interface IConstants {
	public static String GENERATOR_NAME = "test-uuid";
	public static String GENERATOR_STRATEGY = "uuid";

	static interface ITable {
		static interface INames {
			public static String ITEM_TABLE = "Items";
			public static String CATEGORY_TABLE = "Category";
			public static String REVIEW_USER_TABLE = "ReviewUsers";
			public static String REVIEW_REQUEST_TABLE = "ReviewRequests";
			public static String FEEDBACK_TABLE = "feedback";

			public static String REVIEWER_REQUEST_TABLE = "ReviewerRequests";
			public static String EXTERNAL_REVIEWER_TABLE = "ExternalReviewers";
			public static String REVIEW_TABLE = "Reviews";
		}

		static interface IItem {
			public static String ITEM_ID = "itemID";
			public static String CATEGORY = "category";
		}

		static interface ICategory {
			public static String ID = "id";
		}

		static interface IReviewUser {
			public static String ID = "id";
		}

		static interface IReviewRequest {
			public static String ID = "requestID";
			public static String ITEM_ID = "itemID";
			public static String REVIEWEE_ID = "revieweeID";
			public static String PARENT_REQ_ID = "parentRequestID";
		}

		static interface IReviewerRequest {
			public static String ID = "requestID";
			public static String REVIEW_REQUEST_ID = "reviewRequestID";
			public static String REVIEWER_ID = "reviewerID";
		}

		static interface IExternalReviewer {
			public static String ID = "id";
			public static String MAIL_ID = "mailId";
			public static String CONTACT_NO = "contactNo";
			public static String HASH_LINK = "hashLink";
			public static String REVIEWER_REQUEST_ID = "reviewerRequestId";
		}

		static interface IReview {
			public static String ID = "reviewID";
			public static String REVIEWER_REQUEST_ID = "reviewerRequestID";
			public static String ITEM_ID = "itemID";
			public static String REVIEWER_ID = "reviewerID";
		}

		static interface IFeeback {
			public static String ID = "id";
			public static String USER_ID = "userId";
		}
	}
}
