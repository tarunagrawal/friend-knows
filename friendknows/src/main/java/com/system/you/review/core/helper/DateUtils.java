package com.system.you.review.core.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.system.you.review.ApplicationEntity;
import com.system.you.review.web.beans.view.ViewBean;

public class DateUtils {

	public static <T extends ApplicationEntity> void sortByCreateDateTime(
			List<T> collection, SortOrder order) {
		Collections.sort(collection,
				(order == SortOrder.ASC) ? ASC_SORT_BY_ENNITY_CREATE_DATE
						: DSC_SORT_BY_ENTITY_CREATE_DATE);
	}

	public static enum SortOrder {
		ASC, DESC
	};

	public static Comparator<ApplicationEntity> ASC_SORT_BY_ENNITY_CREATE_DATE = new Comparator<ApplicationEntity>() {
		@Override
		public int compare(ApplicationEntity o1, ApplicationEntity o2) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			String o1Date = o1.getCreateDateTime().toString();
			String o2Date = o2.getCreateDateTime().toString();
			try {
				return dateFormat.parse(o1Date).compareTo(
						dateFormat.parse(o2Date));
			} catch (Exception ex) {
				LOGGER.error(
						"error occured while sorting ASC_SORT_BY_CREATE_DATE",
						ex);
				return o1Date.compareTo(o2Date);
			}
		}
	};

	public static Comparator<ApplicationEntity> DSC_SORT_BY_ENTITY_CREATE_DATE = new Comparator<ApplicationEntity>() {
		@Override
		public int compare(ApplicationEntity o1, ApplicationEntity o2) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			String o1Date = o1.getCreateDateTime().toString();
			String o2Date = o2.getCreateDateTime().toString();
			try {
				return dateFormat.parse(o2Date).compareTo(
						dateFormat.parse(o1Date));
			} catch (Exception ex) {
				LOGGER.error(
						"error occured while sorting DSC_SORT_BY_CREATE_DATE",
						ex);
				return o1Date.compareTo(o2Date);
			}
		}
	};

	public static Comparator<String> DSC_SORT_BY_STRING_DATE = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			String o1Date = o1;
			String o2Date = o2;
			try {
				return dateFormat.parse(o2Date).compareTo(
						dateFormat.parse(o1Date));
			} catch (Exception ex) {
				LOGGER.error(
						"error occured while sorting DSC_SORT_BY_STRING_DATE",
						ex);
				return o1Date.compareTo(o2Date);
			}
		}
	};
	
	
	
	
	public static class AscSortByViewBeanCreateDate<T extends ViewBean> implements Comparator<T>{

		public int compare(T o1, T o2) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			String o1Date = o1.getCreateDateTime();
			String o2Date = o2.getCreateDateTime();
			try {
				return dateFormat.parse(o1Date).compareTo(
						dateFormat.parse(o2Date));
			} catch (Exception ex) {
				LOGGER.error(
						"error occured while sorting DSC_SORT_BY_STRING_DATE",
						ex);
				return o1Date.compareTo(o2Date);
			}
		}

	}

	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	private static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

}
