package section01;
import java.io.UnsupportedEncodingException;
/**文字列の操作 */
public class List1 {
	public static void main(String[] args) {
		System.out.println("--1-----------------");
		list1_1();
		System.out.println("--2-----------------");
		list1_2();
		System.out.println("--3-----------------");
		list1_3();
		System.out.println("--1-----------------");
		addition1_1();
		System.out.println("--4-----------------");
		list1_4();
		System.out.println("--2-----------------");
		addition1_2();
		System.out.println("--------------------");
		regularExpression();
		System.out.println("--7-----------------");
		list1_7();
		System.out.println("--8-----------------");
		list1_8();
		System.out.println("--9-----------------");
		list1_9();
		//addition1_2();
		//regularExpression();
	}
/*基本的な文字列操作*/
	/**文字列の調査*/
	static void list1_1() {
		String s1 = "スッキリJava";
		String s2 = "Java", s3 = "java";
		String s4 = "";
		
		if(!s2.equals(s3)) System.out.println("s2とs3は（内容が）等しくない");
		if(s2.equalsIgnoreCase(s3)) System.out.println("s2とs3はケースを区別しなければ等しい");
		System.out.println("s1の文字列長は "+s1.length()+" です");	//全角も半角も１文字
		if(s4.isEmpty()) System.out.println("s4は空文字です");
	}
	/**文字列の検索*/
	static void list1_2() {
		String s1 = "Java and JavaScript";
		/*真偽判定*/
		if(s1.contains("Java")) System.out.println("s1は文字列「Java」を含んでいます");
		if(s1.startsWith("Java")) System.out.println("s1の文字列の先頭は「Java」です");
		if(s1.endsWith("Script")) System.out.println("s1の文字列の最後尾は「Script」です");
		/*位置情報の検索（含まれていない場合は-1を返す）*/
		System.out.println("s1で最初に「Java」が出てくる位置は前から "+s1.indexOf("Java"));
		System.out.println("s1で最後に「Java」が出てくる位置は後ろから "+s1.lastIndexOf("Java"));
	}
	/**文字列の切り出し*/
	static void list1_3() {
		String s1 = "Java programing";
		/* 先頭位置は0であることに注意*/
		System.out.println("s1の5文字目は "+s1.charAt(5));
		System.out.println("s1の3文字目以降は "+s1.substring(3));
		System.out.println("s1の3〜8文字目は "+s1.substring(3,8));
	}
	/**文字列の変換*/
	static void addition1_1(){
		String s1 = " Java邪魔 JAVAjava  ";
		
		System.out.println("大文字を小文字に変換	："+s1.toLowerCase());
		System.out.println("小文字を大文字に変換	："+s1.toUpperCase());
		System.out.println("前後の空白を除去		："+s1.trim()); //全角スペースは対象外
		System.out.println("「邪魔」を「有用」に置き換え："+s1.replace("邪魔", "有用"));
	}
/*文字列の連結*/
	/* 文字列の連結の速度は以下の通り
	 * +演算子 << StringBuffer < SutringBuilder
	 */
	/**StringBuilderによる文字の連結*/
	static void list1_4() {
		StringBuilder sb = new StringBuilder();
		/**複数のスレッド間で使用する場合*/
		/*StringBuffer sb = new StringBuffer();
		 */
		/*appendでバッファに文字列を追加していき、toStringで完成した文字列を取り出す*/
		for(int i=0;i<10000;i++) {
			sb.append("Java").append(" "); //メソッドチェーン：自身への参照を返すメソッドを連続して呼び出す
		}
		System.out.println(sb.toString());
	}
	/* Stringインスタンスはimmutable（不変）なため、+演算子で連結する際は新しいインスタンスを生成することになる
	 * つまり+演算子が呼び出されるためnewによりインスタンスが生成されていくために、処理速度が他に比べ遅くなる
	 */
	/**String・char配列・byte配列の相互変換*/
	static void addition1_2() {
		String str = "こんにちはJava";
		
		/*文字列に含まれる各文字を1文字ずつ配列に格納*/
		char[] data1 = str.toCharArray();
		/*文字コード対応表を用いてバイト列に変換し、1バイトずつ配列に格納*/
		byte[] data2 = str.getBytes();//デフォルトの文字コードを使用
		byte[] data3 = {};
		try {
			data3 = str.getBytes("UTF8");//文字コードUTF-8を使用
		} catch (UnsupportedEncodingException e) {}
		/*配列からStringインスタンスへの変換*/
		System.out.println(new String(data1));
		System.out.println(new String(data2));
		System.out.println(new String(data3));
	}
/*正規表現*/
	/**パターンマッチング*/
	static boolean isVaildPlayerName(String name) {
		return name.matches("[A-Z][A-Z0-9]{7}");
		/*引数nameが、先頭が大文字の英字、それ以外が大文字英字と数字からなる8文字の文字列であるかの正否を返す*/
		/*以下の記述と同等
			if(name.length()!=8) return false;
			char first = name.charAt(0);
			if(!(first>='A' && first<='Z')) return false;
			for(int i=0;i<8;i++) {
				char c = name.charAt(i);
				if(!((c>='A' && c<='Z') || (c>='0' && c<='9'))) return false;
			}
			return true;
		 */
	}
	/**正規表現の基本文法*/
	static void regularExpression() {
		String s = "Java";
		/**通常の文字：その文字でなければならない*/
		System.out.println("完全一致 "+s.matches("Java"));
		/**ピリオド.：任意の1文字*/
		System.out.println("「.」は任意の1文字 "+s.matches("J.va"));
		/**アスタリスク*：直前の文字の0回以上の繰り返し*/
		System.out.println("JaaaaVa".matches("Ja*Va"));
		System.out.println("JaaaaVa".matches(".*")); //全ての文字列を許す
		System.out.println("Java+css".matches("Ja.*")); //「Ja」で始まる任意の文字列
		System.out.println("Java+css".matches(".*css")); //「css」で終わる任意の文字列
		/**波括弧{}：指定回数の繰り返し*/
			/*  {n}	：直前の文字のn回繰り返し
			 *  {n,}	：直前の文字のn回以上繰り返し
			 * {n,m}	：直前の文字のn回以上m回以下の繰り返し
			 *    ?		：直前の文字の0回または1回の繰り返し
			 *    +		：直前の文字の1回以上の繰り返し
			 */
		/**角括弧[]と角括弧内のハイフン*/
			/* 角括弧は[]内のいずれかの1文字を要求する
			 * [0-9]のようにハイフンを挟む場合、前後の値を端とする範囲に含まれる1文字を要求する
			 */
		System.out.println("範囲指定と繰り返し指定 "+s.matches("[A-Za-z]{4}"));
		/**文字クラス*/
			/* \d：いずれかの数字（[0-9]と同義）
			 * \w：英字または数字またはアンダーバー（[a-zA-Z0-9_]と同義）
			 * \s：空白文字（スペース・タブ・改行など）
			 * \\,\[,\*：それぞれ\、[、*といった記号を表す
			 */
		/**ハット^とダラー$*/
			/* ^：先頭、$：末尾
			 * 例えば"^J.*a$"は先頭が「J]で末尾が「a」の任意の長さの文字列を表す
			 * （matches()では不要だが、split(),replaceAll()で明示的に示したい場合に使用）
			 */
	}
	/**文字列の分割*/
	static void list1_7() {
		String s = "abc,def:ghi";
		String[] words = s.split("[,:]");
		for(String str:words) System.out.print(str+"->");
		System.out.println("");
	}
	/**文字列の置換*/
	static void list1_8() {
		String s = "abc,def:ghi";
		System.out.println(s.replaceAll("[beh]", "X"));
	}
/*文字列の書式整形*/
	/**プレースホルダ*/
		/* %「修飾」「桁」「型」（修飾と桁は省略可）
		 * 「修飾」
		 * 	,	：3桁ごとにカンマを入れる
		 * 	0	：空き領域を0で埋める
		 * 	-	：左寄せ（数字）
		 * 	+	：符号の強制表示
		 * 「桁」
		 * 	n	：n桁で表示
		 * 	.m	：小数点以下m桁で表示
		 * 	n.m	：全体n桁、小数点以下m桁で表示
		 * 「型」
		 * 	d	：整数
		 * 	s	：文字列
		 * 	f	：少数
		 * 	b	：真偽値（true,false）
		 */
	static void list1_9() {
		String[][] charactor = {{"minato","hero","280"},{"asaka","witch","32000"},{"sugawara","sage","41000"}};
		final String FORMAT = "%8s %6s  所持金:%,6d";
		for(int i=0;i<charactor.length;i++) {
			String s = String.format(FORMAT,charactor[i][0],charactor[i][1],Integer.parseInt(charactor[i][2]));
			System.out.println(s);
		}
		/**整形して画面に出力*/
		System.out.printf("\n製品番号%s-%02d","SJV",3);
	}
}