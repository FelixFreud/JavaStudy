package section02;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		/*Q2-1*/
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int day = c.get(Calendar.DAY_OF_MONTH);
		day += 100;
		c.set(Calendar.DAY_OF_MONTH, day);
		d = c.getTime();
		SimpleDateFormat f = new SimpleDateFormat("西暦yyyy年MM月dd日");
		System.out.println(f.format(d));
		/*Q2-2*/
		LocalDateTime l = LocalDateTime.now();
		Period p = Period.ofDays(100);
		l = l.plus(p);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("西暦yyyy年MM月dd日");
		System.out.println(l.format(dtf));
	}

}
