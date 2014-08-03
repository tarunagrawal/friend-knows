package com.system.you.review.core.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.system.you.review.ApplicationEntity;

public class DateUtils {

	public static <T extends ApplicationEntity> void sortByCreateDateTime(List<T> collection,
			SortOrder order) {
		Collections.sort(collection, (order == SortOrder.ASC) ? ASC_SORT_BY_CREATE_DATE
				: DSC_SORT_BY_CREATE_DATE);
	}

	public static enum SortOrder {
		ASC, DESC
	};

	private static Comparator<ApplicationEntity> ASC_SORT_BY_CREATE_DATE = new Comparator<ApplicationEntity>() {
		@Override
		public int compare(ApplicationEntity o1, ApplicationEntity o2) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			String o1Date = o1.getCreateDateTime().toString();
			String o2Date = o2.getCreateDateTime().toString();
			try {
				return dateFormat.parse(o1Date).compareTo(dateFormat.parse(o2Date));
			} catch (Exception ex) {
				ex.printStackTrace();
				return o1Date.compareTo(o2Date);
			}
		}
	};

	private static Comparator<ApplicationEntity> DSC_SORT_BY_CREATE_DATE = new Comparator<ApplicationEntity>() {
		@Override
		public int compare(ApplicationEntity o1, ApplicationEntity o2) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			String o1Date = o1.getCreateDateTime().toString();
			String o2Date = o2.getCreateDateTime().toString();
			try {
				return dateFormat.parse(o2Date).compareTo(dateFormat.parse(o1Date));
			} catch (Exception ex) {
				ex.printStackTrace();
				return o1Date.compareTo(o2Date);
			}
		}
	};

	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

}
