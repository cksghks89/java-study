package chapter04;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();

		Locale aLocale = Locale.getDefault(Locale.Category.FORMAT);
		TimeZone tz = TimeZone.getDefault();
//		System.out.println(aLocale.toString());

		System.out.println(aLocale + ":" + tz);

		printDate(cal);
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 11); // 12월 Month - 1
		cal.set(Calendar.DAY_OF_MONTH, 25);

		printDate(cal);

		cal.set(1996, 6, 5);
		cal.add(Calendar.DATE, 10000);
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = { "일", "월", "화", "수", "목", "금", "토" };

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); // 0 ~ 11, +1
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); // 1(일) ~ 7(토)
		int hours = cal.get(Calendar.HOUR);
		int minutes = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		System.out.println(year + "년 " + (month + 1) + "월 " + date + "일 " + DAYS[day - 1] + "요일 " + hours + ":"
				+ minutes + ":" + second);
	}

}
