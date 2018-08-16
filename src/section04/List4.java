package section04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**インスタンスの基本動作*/
public class List4 {
	public static void main(String[] args) {
		list4_9();
	}
/*インスタンスの５大操作*/
	/* toString()		文字列表現を得る
	 * equals()		等価判定を行う
	 * hashCode()		ハッシュ値を得る
	 * compare()		大小関係を判定する
	 * clone()		複製する
	 */
/*インスタンスの文字列表現*/
	/**toString()メソッドのオーバーライド*/
	static void list4_1() {
	/* toString()メソッドは、インスタンスの内容を人が読んで理解できる文字列表現として返す
	 * デフォルトでは「クラス名@ハッシュコード」しか表示されないため、新たなクラスを作成したなら適切な文字列表を
	 * 返すようにオーバーライドする必要がある（Hero.java参照）
	 */
		Hero a = new Hero("みなと",10,5);
		System.out.println(a); //println()にインスタンスを渡すとそのインスタンスのtoString()が呼び出される
	}
/*インスタンスの等価判定*/
	/**equals()のオーバーライド*/
	static void list4_2() {
	/* Objectクラスのequals()メソッドは等値であるかの判定であるため、等価判定ができるよう
	 * オーバーライドする必要がある（Account.java参照）
	 */
		Account a = new Account("123456789");
		//同じアドレスを指す＝等値（==）の判定
		Account b = a;
		//aとは別のアドレスを指すが中身の意味は同じ＝等価（equals）の判定
		Account c = new Account("123456789");
		System.out.println("等値,等価");
		System.out.println((a==b)+","+(a.equals(b)));
		System.out.println((a==c)+","+(a.equals(c)));
	}
	/**equals()のオーバーライドをサボった場合*/
	static void list4_3() {
		List<DameHero> list = new ArrayList<DameHero>();
		DameHero h1 = new DameHero("ミナト");
		list.add(h1);
		System.out.println("要素数="+list.size());
		h1 = new DameHero("ミナト");
		list.remove(h1); //名前が「ミナト」のDameHeroを削除したい
		System.out.println("要素数="+list.size()); //削除できていない
		/* remove()はequals()メソッドを用いて等価判定を行うため、オーバーライドされていないと
		 * Objectクラスのequals()メソッドを呼び出してしまい、等値判定で対象を比較することになる
		 * 上記ではnewで二つのインスタンスを作っており、等値判定ではfalseとなる（list4_2参照）
		 */
		/* 等価判定されることが無いクラスや、スーパークラスで既にオーバーライドされていて、その
		 * ロジックで問題のないクラスである場合を除き、equals()メソッドはオーバーライドする必要
		 * がある。
		 */
	}
/*インスタンスの要約*/
	/**HashSetでのremove()とequals()*/
	static void list4_4() {
		Set<DameHero2> list = new HashSet<>();
		DameHero2 h1 = new DameHero2("ミナト");
		list.add(h1);
		System.out.println("h1のハッシュ値:"+h1.hashCode()+"\n要素数="+list.size());
		h1 = new DameHero2("ミナト");
		list.remove(h1); //名前が「ミナト」のDameHeroを削除したい
		System.out.println("h1のハッシュ値:"+h1.hashCode()+"\n要素数="+list.size()); //削除できていない
		/* HashSetのremove()は引数と対象の値について、まずハッシュ値が同じか評価してからequals()
		 * で判定し、同じであれば削除する。これはオブジェクト同士の比較より、ハッシュ値つまり整数
		 * 同士の比較のほうが、格段に処理が早いため
		 */
		/* 全てのインスタンスにはhashCode()に対して正しいハッシュ値を返す義務がある。ハッシュ値は
		 * それ自体は意味のないint型整数であり、以下２つの条件さえ満たしていれば自由に定義して良い
		 * 「等価なインスタンスからは必ず同じハッシュ値が得られること」
		 * 「異なるインスタンスからはできるだけ異なるハッシュ値が得られ得ること」
		 */
	}
	/**hashCode()をオーバーライドした例*/
	static void list4_5() {
	/*Hero.javaも参照*/
		Set<Hero> list = new HashSet<>();
		Hero h1 = new Hero("ミナト");
		list.add(h1);
		System.out.println("h1のハッシュ値:"+h1.hashCode()+"\n要素数="+list.size());
		h1 = new Hero("ミナト");
		list.remove(h1); //名前が「ミナト」のDameHeroを削除したい
		System.out.println("h1のハッシュ値:"+h1.hashCode()+"\n要素数="+list.size()); //削除
	}
/*インスタンスの順序付け*/
	/**sort()の制約*/
	static void list4_6() {
		List<DameAccount> list = new ArrayList<>();
		list.add(new DameAccount("456"));
		list.add(new DameAccount("123"));
		list.add(new DameAccount("789"));
		//Collections.sort(list); //自然順序付けされていないクラスはそのままではソートできない
	}
	/**Comparableインターフェースの実装*/
	static void list4_7() {
	/* 新しく作ったクラスの自然順序を宣言するにはそのクラスにComparableインターフェースを実装し、
	 * compareTo()メソッドをオーバーライドする必要がある（Account.java参照）
	 */
		List<Account> list = new ArrayList<>();
		list.add(new Account("456"));
		list.add(new Account("123"));
		list.add(new Account("789"));
		Collections.sort(list); //自然順序によるソート
		System.out.println(list);
	}
/*インスタンスの複製*/
	/**clone()による複製*/
	@SuppressWarnings("unused")
	static void list4_8() {
		Hero h1 = new Hero("ミナト");
		Hero h2 = h1; //参照がコピーされるだけでインスタンスの実態はそのまま
		Hero h3 = h1.clone(); //自分自身の複製を返す
		/* clone()による複製を使うためには、そのクラスがCloneableインターフェースを実装し、
		 * cloneメソッドをpublicでオーバーライドする必要がある（Hero.java参照）
		 */
		/* Cloneableインターフェースは「clone()の実装により複製可能である」ことを示している
		 * だけで、clone()メソッドを定義していない
		 * このような特殊なインターフェースをマーカーインターフェースという
		 */
	}
	/**複製したクラスのフィールドを変更してみる*/
	static void list4_9() {
		Hero h1 = new Hero("ミナト");
		Sword s = new Sword("はがねの剣");
		h1.setSword(s);
		System.out.println("装備:"+h1.getSword().getName());
		System.out.println("clone()で複製");
		Hero h2 = h1.clone();
		System.out.println("コピー元の勇者の剣を変更");
		h1.getSword().setName("ひのきの棒");
		System.out.println("コピー元とコピー先の勇者の装備を表示");
		System.out.println("コピー元："+h1.getSword().getName()+"／コピー先："+h2.getSword().getName());
		/* Heroクラスのclone()メソッド内のSwordに対する処理の違い（浅いコピーと深いコピー）で結果が異なる
		 * （Hero.java参照）
		 */
	}
}