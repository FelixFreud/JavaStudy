package section07;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Properties;

/**JVM制御とリフレクション*/
public class List7 {
	public static void main(String[] args) {
		System.out.println("--2----------------");
		list7_2();
		System.out.println("--3----------------");
		list7_3();
		System.out.println("--5----------------");
		String[] a = {""};//list7_5用配列
		list7_5(a);
		System.out.println("--6----------------");
		try {
			list7_6();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*JVMの終了*/
	/**System.exit()を使ったプログラムの終了*/
	static void list7_1() {
		/*例）何かしらのデータ読み込み*/
		if(true/*データが正しくないとする*/) {
			System.out.println("データが壊れています。異常終了します。");
			System.exit(1); //ここでJVMを異常終了。「1」は任意の終了コード、多くの場合0が正常終了
		}
		System.out.println("正常終了しました");
	}
/*外部プログラムの実行*/
	/**計算処理が完了したらメモ帳を起動*/
	static void list7_2() {
		System.out.println("計算を開始します");
		/*例）何らかの計算処理*/
		System.out.println("計算が完了、メモ帳で結果を表示します");
		ProcessBuilder pb = new ProcessBuilder(
				"/usr/bin/mousepad","calcreport.txt");
				//"c:\\windows\\system32\\notepad.exe","calcreport.txt");
		try {pb.start();}
		catch (IOException e) {e.printStackTrace();}
	}
/*システムプロパティの利用
	/**システムプロパティの取得*/
	static void list7_3() {
		System.out.println("Javaのバージョン："+System.getProperty("java.version"));
		Properties p = System.getProperties();
		Iterator<String> i = p.stringPropertyNames().iterator();
		System.out.println("【システムプロパティ一覧】");
		while(i.hasNext()){
			String key = i.next();
			System.out.print(key+" = ");
			System.out.println(System.getProperty(key));
		}
		/* システムプロパティに標準で格納される情報（一部）
		 * キー					値の意味							値の例
		 * java.version		JREのバージョン					1.7.0_04-ea
		 * java.home			Javaのインストール先ディレクトリ	C:\Program Files\Java\jre1.8.0_172
		 * os.name			OSの名称							Windows 10
		 * line.separator	OSの改行コード						\r\n
		 * user.name			実行したユーザー名					sugawara
		 */
	}
	/**システムプロパティの設定*/
	/* 任意のキーと任意の文字列をシステムプロパティとして設定可能
	 * System.setProperty("rpg.version","0.3"); //もっとも簡単な設定方法
	 * java -D rpg.version=0.3 -D rpg.author=渚 Main //実行時に-Dオプションで指定
	 * ただし、不具合の原因になりうるので乱用しないこと
	 */
	/**list7_4は省略*/
/*メモリの状態の取得
	 * long f = Runtime.getRuntime().freeMemory()/1024/1024;	//残りのメモリ容量を返す
	 * long g = Runtime.getRuntime().totalMemory()/1024/1024;//現在のメモリ総容量を返す
	 * long h = Runtime.getRuntime().maxMemory()/1024/1024;	//メモリ総容量の限界値を返す
	 * いずれもlong値、バイト単位で返されるので/1024/1024でメガバイト単位に変換
	 */
/*リフレクション*/
	/*実行時型情報
	 * プログラムの実行中に型情報を利用したい場合は、リフレクションAPIを用いる
	 */
	/*リフレクションの用途
	 * 1.テスト解析のために、privateメンバを操作したい場合
	 *		setAccessible()を引数trueと共に呼び出すとprivateメンバの読み書き・呼び出しが可能
	 * 2.メンバ名を用いた特殊な処理を作り込みたい場合
	 * 		特定の名前のメソッドを持っていたら自動的にそれを呼び出すような特殊な処理を実現できる
	 * 3.利用するクラスを動的に追加・変更できるようにしたい場合
	 * 		クラスをnewして利用する必要があるが、どのクラスを使うかは実行時に決まるような処理を実現できる
	 */
	/**リフレクションを用いた型情報の取得*/
	@SuppressWarnings("unused")
	static void list7_5(String[] args) {
		/*Classインスタンスの基本的な取得方法*/
		Class<?> info0 = null;
		try {
			//① Class<?> cinfo = Class.forName(FQCN文字列);
			info0 = Class.forName("section07.InterfaceExample");}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		//② Class<?> cinfo = クラス名.class;
		Class<?> info1 = String.class;
		//③ Class<?> cinfo = 変数名.getClass();
		Class<?> info3 = args.getClass();
		/*リフレクションAPI：クラスやインターフェースに関する様々な情報をJVMに調べさせる*/
		System.out.println(info1.getSimpleName());	//クラス名の取得
		System.out.println(info1.getName());		//FQCN（パッケージ名+クラス名）を取得
		System.out.println(info1.getPackage().getName());	//パッケージ名の取得
		System.out.println(info0.isArray());		//配列ならtrue
		System.out.println(info0.isInterface());	//インターフェースであればtrue
		System.out.println(info0.isEnum());			//列挙型であればtrue
		Class<?> info2 = info1.getSuperclass();		//Stringの親クラスを取得する
		System.out.println(info2.getName());
		System.out.println(info3.isArray());		//argsは文字列配列として判定される		
		/*下記の一行をリフレクションで実現すると
		 * RefSample rs = new RefSample(256);
		 */
		//RefSampleより情報を取得する
		Class<?> clazz = RefSample.class;
		//引数一つのコンストラクタを取得し、インスタンスを生成する
     	Constructor<?> cons;
		RefSample rs;
        try {
        	// 1. RefSampleクラスにint型の引数を持つコンストラクタを探す
        	cons = clazz.getConstructor(int.class);
	        // 2. 1.で見つけたコンストラクタに、実際に渡したい値を指定してインスタンス生成を行う。
			rs  = (RefSample) cons.newInstance(256);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**リフレクションを用いてメンバを直接操作*/
	static void list7_6() throws Exception{
		Class<?> clazz = RefSample.class;
		Constructor<?> cons = clazz.getConstructor(int.class);	//引数一つのコンストラクタの取得
		RefSample rs = (RefSample) cons.newInstance(256);			//インスタンスを生成する
		Field f = clazz.getField("times");	//pubフィールドに関するFieldを取得
		f.set(rs,2);						//取得したFieldの読み書き
		System.out.println(f.get(rs));		//
		Method m = clazz.getMethod("hello",String.class,int.class);	//引数二つのHelloメソッドを
		m.invoke(rs,"reflection!",128);									//取得して呼び出し
		boolean pubc = Modifier.isPublic(clazz.getModifiers());	//clazzがPublicであるか調べる
		boolean finm = Modifier.isFinal(m.getModifiers());		//mがFinalであるか調べる
		System.out.println(pubc+","+finm);
	}
}
