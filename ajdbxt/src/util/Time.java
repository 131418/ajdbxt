package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	// 一段yyyy-MM-dd HH:mm:ss字符串分成年月日三段
		// 2017-11-09 15:08:50
		//
		public static String timeToYear(String time) {
			String year = time.substring(0, 4);
			return year;
		}

		public static String timeToMonth(String time) {
			String month = time.substring(5, 7);
			return month;
		}

		//
		public static String timeToDay(String time) {
			String day = time.substring(8, 10);
			return day;
		}

		public static String getStringSecond() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date secondDate = new Date();
			String date = formatter.format(secondDate);
			try {
				secondDate = formatter.parse(date);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return "0000-00-00";
			}

		}

		public static String yearAndMonth() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			Date secondDate = new Date();
			String date = formatter.format(secondDate);
			try {
				secondDate = formatter.parse(date);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return "0000-00";
			}

		}

		public static String getDay_Of_Week(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("E");
			String week = sdf.format(date);
			return week;
		}

		public static String getWeek_Of_Month(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("W");
			String week = sdf.format(date);
			return week;
		}

		public static Date getDateSecond() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date secondDate = new Date();
			String date = formatter.format(secondDate);
			try {
				secondDate = formatter.parse(date);
				return secondDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		}

		public static String getStringDay() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date secondDate = new Date();
			String date = formatter.format(secondDate);
			try {
				secondDate = formatter.parse(date);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		}

		public static Date getDateDay() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date secondDate = new Date();
			String date = formatter.format(secondDate);
			try {
				secondDate = formatter.parse(date);
				return secondDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		}

		public static String getTimeChou() {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date secondDate = new Date();
			String date = formatter.format(secondDate);
			try {
				secondDate = formatter.parse(date);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return "00000000";
			}
		}

}
