package section03;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**コレクション*/
public class List3{

	public static void main(String[] args) {
		System.out.println("--l1-------------------");
		list3_1();
		System.out.println("--a1-------------------");
		addition3_1();
		System.out.println("--a2-------------------");
		addition3_2();
		System.out.println("--l3-------------------");
		list3_3();
		System.out.println("--l4-------------------");
		list3_4();
		System.out.println("--a3-------------------");
		addition3_3();
		System.out.println("--l5-------------------");
		list3_5();
		System.out.println("--l6-------------------");
		list3_6();
		System.out.println("--l7-------------------");
		list3_7();
		System.out.println("--a4-------------------");
		addition3_4();
		System.out.println("--l8-------------------");
		list3_8();
		System.out.println("--a5-------------------");
		addition3_5();
		System.out.println("--l9-------------------");
		list3_9();
	}

/*ArrayListと配列の違い*/
	/* import文が必要
	 * ジェネリクスで格納する型を指定
	 * サイズの指定がなく、自動的に容量が追加される
	 * メモリ効率や性能は配列のほうが有利
	 * インスタンスでないもの（基本データ型）は格納できない
	 */
	/**ArrayListの使用例*/
	static void list3_1() {
		ArrayList<Integer> points = new ArrayList<>();
		
		points.add(10);
		points.add(80);
		points.add(75);
		for(int i:points) System.out.println(i);
	}
/*ArrayListの使い方*/
	/**ArrayListのメソッド*/
	static void addition3_1() {
		List<String> al = new ArrayList<>();
		/**要素の格納*/
		System.out.println("リストの最後に追加 "+al.add("No.1"));
		al.add(0,"No.2"); //リストのint番目に要素を追加（void）
		System.out.println("リストのint番目の値を上書きし古い値を返す "+al.set(0,"No.0"));
		/**要素を取り出し*/
		System.out.println("リストのint番目の値を取り出す "+al.get(0));
		/**リストの調査*/
		System.out.println("リストの要素数を返す "+al.size());
		System.out.println("要素数がゼロであるか判定 "+al.isEmpty());
		System.out.println("指定要素が含まれているか判定 "+al.contains("No.2"));
		System.out.println("指定要素が何番目にあるか検索 "+al.indexOf("No.1"));
		/**要素の削除*/
		System.out.println("int番目の要素を削除しその値を返す "+al.remove(1));
		al.clear(); //全ての要素を削除する（void）
	}
	/**for文による要素の取り出し*/
	static void addition3_2() {
		ArrayList<String> names = new ArrayList<>();
		names.add("湊");
		names.add("朝香");
		names.add("菅原");
		for(int i=0; i<names.size(); i++) {
			System.out.println(names.get(i));
		}
		for(String s:names) {
			System.out.println(s);
		}
	}
	/**Iteratorを使った要素の取り出し（list3_2含む）*/
	static void list3_3() {
		List<String> names = new ArrayList<>();
		names.add("湊");
		names.add("朝香");
		names.add("菅原");
		
		Iterator<String> it = names.iterator(); //イテレータの取得
		/*先頭要素の前を指した状態
		 * next()を呼び出して初めて先頭要素を指す
		 * これにより要素数0でもイテレータを取得できる
		 */
		
		while(it.hasNext()) System.out.println(it.next()); //要素の取り出し
		/*以下の処理と同等
		 * for(String s:names) System.out.println(s);
		 */
	}
/*その他のリスト*/
	/*LinkedLidt
	 * LinkedListは数珠つなぎの連結リストで、要素数があまり大きくなく要素の挿入・削除を頻繁に
	 * 行うときに有用
	 */
	/*ポリモーフィズムの利用
	 * 宣言時、変数の型を List<string> list = ～ のようにあいまいなインターフェース型で定義して
	 * おくと、そのインターフェースを実装する具体的なクラスインスタンスが状況に応じて選択できる
	 */
/*様々なコレクションクラス*/
	/*Set
	 * 集合というデータ構造を実現する
	 * 要素に順序がなく重複が認められていない
	 */
	/**HashSetの使用例*/
	static void list3_4() {
		Set<String> colors = new HashSet<>();
		
		colors.add("赤");
		colors.add("青");
		colors.add("黄");
		colors.add("赤"); //重複は無視
		for(String s:colors) System.out.println(s); //赤青黃以外の順序で表示される場合がある
	}
	/**HashSetのメソッド*/
	static void addition3_3() {
		Set<String> hs = new HashSet<>();
		/*Listにあったsetやgetはない*/
		/*要素の格納*/
		System.out.println("セットに要素を追加 "+hs.add("緑"));
		System.out.println("重複した要素の追加は "+hs.add("緑"));
		/*セットの調査*/
		System.out.println("格納されている要素数 "+hs.size());
		System.out.println("要素数がゼロであるか判定 "+hs.isEmpty());
		System.out.println("指定要素が含まれているか判定 "+hs.contains("緑"));
		/*要素を削除する*/
		System.out.println("指定した要素の削除 "+hs.remove("青"));
		hs.clear(); //全要素を削除（void）
	}
	/**Iteratorを使った要素の参照*/
	static void list3_5() {
		Set<String> colors = new HashSet<>();
		colors.add("赤");
		colors.add("青");
		colors.add("黄");
		
		Iterator<String> it = colors.iterator();
		while(it.hasNext()) System.out.println(it.next()); //赤青黃以外の順序で表示される場合がある
	}
	/**順序付けしたSet 一部改変*/
	static void list3_6() {
		List<Set<String>> words = new ArrayList<Set<String>>();
		words.add(new LinkedHashSet<>()); //格納順
		words.add(new TreeSet<>()); //自然順序（Stringでは辞書順）
		for(Set<String> s:words) {
			s.add("dog");
			s.add("cat");
			s.add("wolf");
			s.add("fox");
			for(String str:s) System.out.print(str+"→");
			System.out.println("");
		}
	}
/*Mapの使い方*/
	/*Map
	 * キーと値の２つの情報をペアとして格納するデータ構造
	 * 値の重複は認められるがキーの重複は認められない
	 */
	/**HashMapの使用例*/
	static void list3_7() {
		Map<String,Integer> prefs = new HashMap<>();
		
		prefs.put("京都府",255);
		prefs.put("東京都",1261);
		prefs.put("熊本県",181);
		System.out.println("東京都の人口は "+prefs.get("東京都"));
		prefs.remove("京都府");
		prefs.put("熊本県",182); //上書き
		System.out.println("熊本県の人口は "+prefs.get("熊本県"));
	}
	/**HashMapのメソッド*/
	static void addition3_4() {
		Map<String,Integer> hm = new HashMap<>() ;
		/*要素の格納*/
		System.out.println("マップにキーと値のセットを格納し、上書きであれば古い値を返す "+hm.put("京都府",255));
		//キーが異なれば値の重複は可能だがキーの重複は不可、というよりは値の上書きになる
		/*要素の取り出し*/
		System.out.println("キーに対応する値の取得 "+hm.get("京都府")); //なければnull
		/*キーの一覧表示*/
		System.out.println("格納されているキーの一覧を取得 "+hm.keySet());
		/*マップの調査*/
		System.out.println("格納されているペア数の取得 "+hm.size());
		System.out.println("要素数がゼロであるか判定 "+hm.isEmpty());
		System.out.println("指定値がキーに含まれているか判定 "+hm.containsKey("東京都"));
		System.out.println("指定値が値に含まれているか判定 "+hm.containsValue(255));
		/*要素の削除*/
		System.out.println("指定したキーのペアを削除し、その値を返す "+hm.remove("京都府"));
		hm.clear();
	}
	/**Mapでの拡張for文*/
	static void list3_8() {
		Map<String,Integer> prefs = new HashMap<>();
		prefs.put("京都府",255);
		prefs.put("東京都",1261);
		prefs.put("熊本県",182);
		
		for(String key:prefs.keySet()) System.out.println(key+"の人口は "+prefs.get(key));
		/*HashMapは格納したペア同士の順序を保証しない*/
	}
	/**順序付けしたMap*/
	static void addition3_5() {
		List<Map<Integer,String>> words = new ArrayList<>();
		words.add(new LinkedHashMap<>()); //格納順
		words.add(new TreeMap<>()); //自然順序（Integerでは昇順）
		for(Map<Integer,String> s:words) {
			s.put(4,"dog");
			s.put(2,"cat");
			s.put(3,"wolf");
			s.put(1,"fox");
			for(Integer key:s.keySet()) System.out.print(s.get(key)+"→");
			System.out.println("");
		}
	}
/*コレクションの応用*/
	/**参照型の注意点*/
	static void list3_9() {
		Hero h = new Hero();
		h.name = "ミナト";
		List<Hero> list = new ArrayList<>();
		list.add(h); //hが持つ参照を格納
		h.name = "スガワラ"; //hが持つ参照先の書き換え
		System.out.println(list.get(0).name); //書き換え後の参照先を参照する
	}
}
/**list3_9で使用*/
class Hero{
	public String name;
	/*自分が開発したクラスのインスタンスをコレクションに格納する場合は、equls()やhashCode()を
	 * 正しくオーバーライドすることが必要（詳細はsection04）
	 */
}