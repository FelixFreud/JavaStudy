package section01;

public class Quest1 {

	public static void main(String[] args) {
		System.out.println("--1------------------");
		quest1_1();
		System.out.println("--2------------------");
		System.out.println(quest1_2("C:\\User\\Felix","Test.txt"));
	}

	/**Q1-1*/
	static void quest1_1() {
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=100;i++) {
			sb.append(i+",");
		}
		String s = sb.toString();
		String[] a = s.split(",");
		System.out.println(a);
	}
	/**Q1-2*/
	static String quest1_2(String folder,String file) {
		if(!folder.endsWith("\\")) folder += "\\";
		return folder+file;
	}
	/**Q1_3*/
	/* 1. .*
	 * 2. A\d\d?
	 * 3. U[A-Z]{3}
	 */
}
