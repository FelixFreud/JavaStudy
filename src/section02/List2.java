package section02;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**日付と時間の取扱*/
public class List2 {

	public static void main(String[] args) {
		System.out.println("--1---------------------");
		list2_1();
		System.out.println("--2---------------------");
		list2_2();
		System.out.println("--3---------------------");
		list2_3();
		System.out.println("--4---------------------");
		list2_4();
		System.out.println("--5---------------------");
		list2_5();
	}

/*Date型のおさらい*/
	/**Dateクラスを用いた例*/
	static void list2_1() {
		Date now = new Date(); //現在の日時を習得
		System.out.println(now);
		System.out.println(now.getTime());
		Date past = new Date(1316622225935L);
		System.out.println(past);
	}

/*Time API*/
	/**Instant,ZonedDateTimeの利用例*/
	@SuppressWarnings("unused")
	static void list2_2() {
		/*Instant
		 * エポックからの経過時間をナノ秒で格納する
		 *ZonedDateTime
		 * タイムゾーンを含んだ時刻を格納する
		 */
		Instant i1 = Instant.now(); //現在時刻の取得
		/*Instantとlong値の相互変換*/
		Instant i2 = Instant.ofEpochMilli(31920291332L);
		long l = i2.toEpochMilli();
		/*ZonedDateTimeの生成*/
		ZonedDateTime z1 = ZonedDateTime.now();
		ZonedDateTime z2 = ZonedDateTime.of(2014, 1, 2, 3, 4, 5, 6, ZoneId.of("Asia/Tokyo"));
																		//東京時間 2014年1月2日3時4分5秒6ナノ秒
		/*InstantとZonedDateTimeの相互変換*/
		Instant i3 = z2.toInstant();
		ZonedDateTime z3 = i3.atZone(ZoneId.of("Europe/London"));
		/*ZonedDateTimeの利用*/
		System.out.println("東京"+z2.getYear()+z2.getMonthValue()+z2.getDayOfMonth());
		System.out.println("ロンドン"+z3.getYear()+z3.getMonthValue()+z3.getDayOfMonth());
		if(z2.isEqual(z3)) System.out.println("これらは同じ時間を表しています");
	}
	/**LocalDateTimeの利用*/
	@SuppressWarnings("unused")
	static void list2_3() {
		/*LocalDateTime
		 * タイムゾーンの欠落した曖昧な日時情報を格納する
		 */
		/*LocalDateTimeの生成*/
		LocalDateTime l1 = LocalDateTime.now(); //現在日時を取得
		LocalDateTime l2 = LocalDateTime.of(2014, 1, 1, 9, 5, 0, 0); //2014/1/1 9:05を取得
		/*LocalDateTimeとZonedDateTimeの相互変換*/
		ZonedDateTime z1 = l2.atZone(ZoneId.of("Europe/London"));
		LocalDateTime l3 = z1.toLocalDateTime();
		System.out.println(l3);
	}
	/**その他の日時を表すクラス*/
	/* LocalDate	年月日のみを格納する
	 * LocalTime	時間のみを格納する
	 * Year		年のみを格納する
	 * YearMonth	年月のみを格納する
	 * Month		月のみを格納する
	 * MonthDay	月日のみを格納する
	 */
	/**Temporalインターフェースを実装するクラスが持つメソッド*/
	/* now()			静的	現在日時からインスタンスを生成する
	 * of(),of~()		静的	他の種類から変換してインスタンスを生成する
	 * parse()		静的	文字列からインスタンスを生成する、文字書式はDateTimeFormatterで指定する
	 * format()		動的	保持情報を文字列に変換する、文字書式はDataTimeFormatterで指定する
	 * get~()			動的	格納する情報から特定の情報（Year,Month,DayOfMonth,Hour,Second,Second,Nano等）
	 * 							を取得する
	 * isAfter()		動的	引数で指定したインスタンスよりも後の時間であるか判定する
	 * isBefore()		動的	引数で指定したインスタンスよりも前の時間であるか判定する
	 * plus~()		動的	指定した分だけ未来の時点を返す
	 * minus~()		動的	指定した分だけ過去の時点を返す
	 * plus()			動的	指定した時間間隔（PeriodやDuration）の分だけ未来の地点を返す
	 * minus()		動的	指定した時間間隔の分だけ過去の地点を返す
	 */
	/**各種日時クラスのメソッド利用例*/
	static void list2_4() {
		/*文字列からLocalDateを作成*/
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate d = LocalDate.parse("2011/08/21",f);
		/*1000日後を計算する*/
		d = d.plusDays(1000);
		String str = d.format(f);
		System.out.println("100日後は"+str);
		/*現在日付との比較*/
		LocalDate now = LocalDate.now();
		if(now.isAfter(d)) {
			System.out.println("nowはdより新しい");
		}
	}
	/**時間や期間を表すクラス*/
	@SuppressWarnings("unused")
	static void list2_5() {
		/*Duration
		 * 時・分・秒単位の比較的短い間隔を表す場合に用いる
		 *Period
		 * サマータイムやうるう年なども考慮しながら日数ベースで期間を扱う場合に用いる
		 */	
		LocalDate d1 = LocalDate.of(2012, 1, 1);
		LocalDate d2 = LocalDate.of(2012, 1, 4);
		/*3日間を表すPeriodを2通りの方法で生成*/
		Period p1 = Period.ofDays(3);
		Period p2 = Period.between(d1, d2);
		/*d2のさらに3日後を計算する*/
		LocalDate d3 = d2.plus(p2);
		System.out.println(d3);
	}
}
