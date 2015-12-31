package com.loong.util.date;

/**
 * <p>Title: 时间和日期的工具类</p>
 * <p>Description: DateUtil类包含了标准的时间和日期格式，以及这些格式在字符串及日期之间转换的方法</p>
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateUtil {
	private static String datePattern = "yyyy-MM-dd";

	private static String timePattern = datePattern + " HH:MM a";

	public static String getDatePattern() {
		return datePattern;
	}

	public static final String dateToStr(Date aDate) {
		return dateToStr(datePattern, aDate);
	}

	public static final String dateToStr(String pattern, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static final Date convertStringToDate(String pattern, String strDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}

	/**
	 * 获取系统日期
	 */
	public static String getTodayOfString() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);
		return df.format(today);
	}

	/**
	 * 获取系统日期
	 * 
	 * @throws ParseException
	 */
	public static Calendar getTodayOfCalendar() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * 根据默认的规则“yyyy-MM-dd”把日期转换为字符串
	 */
	public static final String convertDateToString(Date aDate) {
		return dateToStr(datePattern, aDate);
	}

	public static Date convertStringToDate(String strDate) throws ParseException {
		return convertStringToDate(datePattern, strDate);
	}

	/**
	 * 日期格式转换成时间戳
	 */
	public static long getTimeStamp(String pattern, String strDate) throws ParseException {
		long returnTimeStamp = 0;
		Date aDate = convertStringToDate(pattern, strDate);
		if (aDate == null) {
			returnTimeStamp = 0;
		} else {
			returnTimeStamp = aDate.getTime();
		}
		return returnTimeStamp;
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static long getNowTimeStamp() throws ParseException {
		long returnTimeStamp = 0;
		Date aDate = null;
		aDate = convertStringToDate("yyyy-MM-dd HH:mm:ss", getNowDateTime());
		if (aDate == null) {
			returnTimeStamp = 0;
		} else {
			returnTimeStamp = aDate.getTime();
		}
		return returnTimeStamp;
	}

	/**
	 * 得到格式化后的系统当前日期
	 *
	 * @param strScheme
	 *            格式模式字符串
	 * @return 格式化后的系统当前时间，如果有异常产生，返回空串""
	 * @see java.util.SimpleDateFormat
	 */
	public static final String getNowDateTime(String pattern) {
		return dateToStr(pattern, new Date());
	}

	public static final String getNowDateTime() {
		return dateToStr(timePattern, new Date());
	}

	/**
	 * 转化日期格式"MM/dd/YY、MM.dd.YY、MM-dd-YY、MM/dd/YY"，并输出为正常的格式yyyy-MM-dd
	 * 
	 * @throws ParseException
	 */
	public static final String convertNormalDate(String strDate) throws ParseException {
		String strReturn = "";
		// 先把传入参数分格符转换成java认识的分格符
		String[] date_arr = strDate.split("\\.|\\/|\\-");
		if (date_arr.length == 3) {
			if (date_arr[2].length() != 4) {
				String nowYear = getNowDateTime("yyyy");
				date_arr[2] = nowYear.substring(0, 2) + date_arr[2];
			}
			strReturn = convertDateToString(convertStringToDate("MM/dd/yyyy", combineStringArray(date_arr, "/")));
		}
		return strReturn;
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * @param delim
	 *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 */
	public static final String combineStringArray(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	/**
	 * 获取星期英文
	 * 
	 * @param strWeek
	 * @return
	 */
	public static final int getWeekNum(String strWeek) {
		if (strWeek == null) {
			return 0;
		}
		int returnValue = 0;
		if (strWeek.equals("Mon")) {
			returnValue = 1;
		} else if (strWeek.equals("Tue")) {
			returnValue = 2;
		} else if (strWeek.equals("Wed")) {
			returnValue = 3;
		} else if (strWeek.equals("Thu")) {
			returnValue = 4;
		} else if (strWeek.equals("Fri")) {
			returnValue = 5;
		} else if (strWeek.equals("Sat")) {
			returnValue = 6;
		} else if (strWeek.equals("Sun")) {
			returnValue = 0;
		}
		return returnValue;
	}

	/**
	 * 获取日期字符串中的中文时间表示字符串
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static final String getSabreTime(String strDate) throws ParseException {
		Date d = convertStringToDate("yyyy-MM-dd HH:mm:ss", strDate.replace("T", ""));
		return dateToStr("hh:mm aaa", d);
	}

	/**
	 * 获取日期字符串中的中文日期表示字符串
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static final String getSabreDate(String strDate) throws ParseException {
		String p = "";
		if (strDate.length() > 10)
			p = "yyyy-MM-dd HH:mm:ss";
		else
			p = "yyyy-MM-dd";

		Date d = convertStringToDate(p, strDate.replace("T", ""));
		return dateToStr("EEE d-MMM", d);
	}

	/**
	 * 获取日期字符串的中文日期时间表示
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static final String getSabreDateTime(String strDate) throws ParseException {
		String p = "";
		if (strDate.length() > 10)
			p = "yyyy-MM-dd HH:mm:ss";
		else
			p = "yyyy-MM-dd";

		Date d = convertStringToDate(p, strDate.replace("T", ""));
		return dateToStr("EEE d-MMM hh:mm aaa", d);
	}

	/**
	 * 获取日期
	 * 
	 * @param timeType
	 *            时间类型，譬如：Calendar.DAY_OF_YEAR
	 * @param timenum
	 *            时间数字，譬如：-1 昨天，0 今天，1 明天
	 * @return 日期
	 */
	public static final Date getDateFromNow(int timeType, int timenum) {
		Calendar cld = Calendar.getInstance();
		cld.set(timeType, cld.get(timeType) + timenum);
		return cld.getTime();
	}

	/**
	 * 获取日期
	 * 
	 * @param timeType
	 *            时间类型，譬如：Calendar.DAY_OF_YEAR
	 * @param timenum
	 *            时间数字，譬如：-1 昨天，0 今天，1 明天
	 * @param format_string
	 *            时间格式，譬如："yyyy-MM-dd HH:mm:ss"
	 * @return 字符串
	 */
	public static final String getDateFromNow(int timeType, int timenum, String format_string) {
		if ((format_string == null) || (format_string.equals("")))
			format_string = "yyyy-MM-dd HH:mm:ss";
		Date date = getDateFromNow(timeType, timenum);
		DateFormat df = new SimpleDateFormat(format_string);
		return df.format(date);
	}

	/**
	 * method diffdate
	 * 
	 * @param beforDate
	 *            2005-06-20
	 * @param afterDate
	 *            2005-06-21
	 * @return
	 * @throws ParseException
	 */
	public static int diffDate(String beforeDate, String afterDate) throws ParseException {
		Date before = convertStringToDate(beforeDate);
		Date after = convertStringToDate(afterDate);

		return (int) ((before.getTime() - after.getTime()) / (24 * 60 * 60 * 1000));
	}
}
