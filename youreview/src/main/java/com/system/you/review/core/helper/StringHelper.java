package com.system.you.review.core.helper;

import org.apache.commons.lang.StringUtils;

public class StringHelper {

	public static boolean isBlank(String... values) {
		if (values == null || values.length == 0) {
			return true;
		}

		for (String string : values) {
			if (!StringUtils.isBlank(string)) {
				break;
			}
		}

		return false;
	}
}
