package com.lander.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	/**
	 * 在当前时间的基础上加或减去几月
	 *
	 * @param month
	 * @return
	 */
	public static Date month(int month) {
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(new Date());
		Cal.add(Calendar.MONTH, month);
		return Cal.getTime();
	}

	/**
	 * 在指定的时间上加或减去几月
	 *
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addmonth(Date date, int month) {
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(date);
		Cal.add(java.util.Calendar.MONTH, month);
		return Cal.getTime();
	}

	public static String format(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	public static String format(Date date,String formatStr) {
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		return df.format(date);
	}
	/**
	 * 获取前月的第一天 @Title: firstdayofmonth @Description: @param @param
	 * date @param @return @return Date @throws
	 */
	public static Date firstdayofmonth(Date date) {
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(date);

		
		Cal.add(Calendar.MONTH, 0);
		Cal.set(Calendar.DAY_OF_MONTH, 1);
		return Cal.getTime();
	}

	public static Date lastdayofmonth(Date date) {
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(date);
		Cal.add(Calendar.MONTH, 1);
		Cal.set(Calendar.DAY_OF_MONTH, 0);
		return Cal.getTime();
	}
}
