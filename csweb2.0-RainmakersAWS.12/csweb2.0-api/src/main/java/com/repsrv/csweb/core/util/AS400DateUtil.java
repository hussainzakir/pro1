package com.repsrv.csweb.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AS400DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(AS400DateUtil.class);
	/**
	 * Take in a date with format yyyymmdd and return formatted string date in the
	 * format of mm/dd/yyyy
	 * 
	 * @param as400Date
	 * @return
	 */
	public static String format8DigitDate(String as400Date) {
		String dateFormatted = as400Date;
		if (StringUtils.isNotBlank(as400Date) && as400Date.trim().length() == 8)
			dateFormatted = StringUtils.substring(as400Date, 4, 6) + "/" + StringUtils.substring(as400Date, 6, 8) + "/"
					+ StringUtils.substring(as400Date, 0, 4);

		return dateFormatted;
	}

	/**
	 * Take in a date with format yyyy-mm-dd and return formatted string date in the
	 * format of mm/dd/yy
	 * 
	 * @param as400Date
	 * @return
	 */
	public static String format10DigitDate(String as400Date) {
		String dateFormatted = as400Date;
		if (StringUtils.isNotBlank(as400Date) && as400Date.trim().length() == 10)
			dateFormatted = StringUtils.substring(as400Date, 5, 7) + "/" + StringUtils.substring(as400Date, 8, 10) + "/"
					+ StringUtils.substring(as400Date, 2, 4);

		return dateFormatted;
	}

	/**
	 * Take in a date with format yyyy-mm-dd and return formatted string date in the
	 * format of mm/dd/yyyy
	 * 
	 * @param as400Date
	 * @return
	 */
	public static String formatDigitDate(String as400Date) {
		String dateFormatted = as400Date;
		if (StringUtils.isNotBlank(as400Date) && as400Date.trim().length() == 10)
			dateFormatted = StringUtils.substring(as400Date, 5, 7) + "/" + StringUtils.substring(as400Date, 8, 10) + "/"
					+ StringUtils.substring(as400Date, 0, 4);

		return dateFormatted;
	}

	public static String toYearMonthDate(Date date) {
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd");
		try {
			return timeFormatter.format(date);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * Take in a date with format yyyyMMdd and return formatted string date in the
	 * format of MM/dd/yy
	 * 
	 * @param as400Date
	 * @return
	 */
	public static String format8DigitDateTo6DigitDate(String as400Date) {
		String dateFormatted = as400Date;
		if (StringUtils.isNotBlank(as400Date) && as400Date.trim().length() == 8)
			dateFormatted = StringUtils.substring(as400Date, 4, 6) + "/" + StringUtils.substring(as400Date, 6, 8) + "/"
					+ StringUtils.substring(as400Date, 2, 4);

		return dateFormatted;
	}

	/**
	 * Take in a date with format yyyyMMdd and return formatted string date in the
	 * format of MM/dd/yy
	 * 
	 * @param as400Date
	 * @return
	 */
	public static String formatTime(String as400Time) {
		String timeFormatted = as400Time;
		if (StringUtils.isNotBlank(as400Time)) {
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
			Date timeField = null;
				try {
					timeField = timeFormat.parse(timeFormatted);
				} catch (ParseException e) {
					logger.info("Parse Exception", e);
				}
			timeFormat.applyPattern("hh:mm: a");
			timeFormatted = timeFormat.format(timeField);
		}
		return timeFormatted;
	}
}
