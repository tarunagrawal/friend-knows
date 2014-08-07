package com.system.you.review.item.bean.helper;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.util.Assert;

import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.web.domain.impl.SessionUtils;

public class BeanHelper {

	protected void checkNulls(Object... beans) {
		Assert.notNull(beans);
		Assert.notEmpty(beans);
		for (Object bean : beans) {
			Assert.notNull(bean);
		}
	}

	protected void checkBlank(String... beans) {
		Assert.notNull(beans);
		for (String bean : beans) {
			Assert.isTrue(StringUtils.isNotBlank(bean));
		}
	}

	protected String date(Date date) {
		String dateFormat = "";
		Calendar nowCalendar = getCurrentCalendar();
		Calendar dateCalendar = getCalendar(date);
		int currentYear = nowCalendar.get(Calendar.YEAR);
		int dateYear = dateCalendar.get(Calendar.YEAR);
		if (isMoreThanAYear(currentYear, dateYear)) {
			dateFormat = (currentYear - dateYear) + " year ago";
		} else {
			int currentDay = nowCalendar.get(Calendar.DAY_OF_YEAR);
			int dateDay = dateCalendar.get(Calendar.DAY_OF_YEAR);
			if (moreThanADay(currentDay, dateDay)) {
				dateFormat = (currentDay - dateDay) + " day(s) ago";
			} else {
				int currentHourOfTheDay = nowCalendar.get(Calendar.HOUR_OF_DAY);
				int dateHourOfTheDay = dateCalendar.get(Calendar.HOUR_OF_DAY);
				if (isMoreThanAHour(currentHourOfTheDay, dateHourOfTheDay)) {
					dateFormat = (currentHourOfTheDay - dateHourOfTheDay) + " hour(s) ago";
				} else {
					int currentMinutes = nowCalendar.get(Calendar.MINUTE);
					int minutesOfthehours = dateCalendar.get(Calendar.MINUTE);
					int minutes = ((currentMinutes - minutesOfthehours) > 0) ? (currentMinutes - minutesOfthehours)
							: 1;
					dateFormat = minutes+ " minute(s) ago";
				}
			}
		}

		return (date == null) ? new Date().toString() : dateFormat;
	}

	private boolean isMoreThanAHour(int currentDay, int dateHourOfTheDay) {
		return currentDay - dateHourOfTheDay > 1;
	}

	private boolean isMoreThanAYear(int currentYear, int dateYear) {
		return isMoreThanAHour(currentYear, dateYear);
	}

	private boolean moreThanADay(int currentDay, int dateDay) {
		return isMoreThanAYear(currentDay, dateDay);
	}

	private Calendar getCurrentCalendar() {
		Date now = new Date();
		return getCalendar(now);
	}

	private Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	protected Date current() {
		return new Date();
	}

	protected ReviewUser currentUser() {
		return SessionUtils.getRequestor().getUser();
	}

	protected String name(String providerId) {
		FacebookProfile profile = SessionUtils.getRequestor().getFacebookFriend(providerId);
		if (profile != null) {
			return profile.getName();
		}
		return "";
	}

	protected String mail(String providerId) {
		FacebookProfile profile = SessionUtils.getRequestor().getFacebookFriend(providerId);
		if (profile != null) {
			return profile.getUsername() + "@facebook.com";
		}
		return "";
	}

	protected String profileImage(String providerId) {
		String url = "http://graph.facebook.com/" + providerId + "/picture/";
		return url;
	}

	protected ReviewUser user(String providerId) {
		FacebookProfile profile = SessionUtils.getRequestor().getFacebookFriend(providerId);
		ReviewUser user = new ReviewUser();
		user.setExternal(true);
		user.setProviderUserId(providerId);

		if (profile != null) {
			user.setMailID(mail(providerId));
			user.setName(profile.getName());
			user.setProviderId("facebook");
		} else {
			System.out.println("error: provider id is not a friend:" + providerId);
			user.setMailID("not avl");
			user.setName("not avl");
			user.setProviderId("facebook");
		}

		return user;
	}

	protected String contactNumber(String providerId) {
		return "91999999999";
	}

	protected String applyXSSFilter(String value) {
		//return ESAPI.encoder().encodeForHTML(value);
		return value;
	}

}
