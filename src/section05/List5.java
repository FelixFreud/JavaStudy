package section05;
import section04.Hero;
/**様々な種類のクラス*/
public class List5{
	public static void main(String[] args) {
		list5_1();
	}
/*型安全*/
	/**Object型を利用した型の仕組みがないJavaプログラム*/
	static void list5_1() {
		Object s = "こんにちは";	//文字列も
		s = new Hero();				//任意のクラスにも格納可能
		Object n = 1;
		damePrints(s,n);				//呼び出しに注意が必要
		/* prints(n,s)や引数がnullであってもコンパイルエラーが出ないため、
		 * 実際にそのメソッドが使用されるまでこのバグに気づけない
		 */
	}
	/**list5_1用damePrintsメソッド*/
	public static void damePrints(Object a,Object b) {
		for(int i=0;i<(Integer)b;i++) System.out.println(a);
	}
	/**damePrintsを書き換えた型を適切に用いたprintsメソッド*/
	public static void prints(String msg,int num) {
		for(int i=0;i<num;i++) System.out.println(msg);
	}
	/* 型安全のために、（必要な柔軟性は持たせつつ）変数には可能な限り厳密な型を指定するべき
	 * コンパイラにデバッグさせミスを探させることで、リリース後のバグを発生を抑えることができる
	 */
	/**list5_2はPocket1.java参照*/
	/**Pocket1クラスを利用するプログラム*/
	static void list5_3() {
		Pocket1 p = new Pocket1();
		p.put("1192"); //String->Objectに代入される
		String s = (String) p.get(); //Object->Stringにキャストする必要がある
		System.out.println(s);
		/* これではp.put(1192)としてしまってもコンパイルが通ってしまう
		 * （intからStringへのキャストは不可）
		 */
	}
	/**list5_4はPocket2.java参照*/
	/**list5_5は内部処理の説明のためのものなので省略*/
	/**Pocket2クラスを利用するプログラム*/
	static void list5_6() {
		Pocket2<String> p = new Pocket2<>();
		p.put("1192");
		String s = p.get(); //戻り値がString型なのでキャスト不要
		System.out.println(s);
	}
/*型安全のための列挙型の利用*/
	/**list5-8,5-9は省略、list5-10はAccount.javaを参照*/
/*インナークラス*/
	/**メンバークラスのインスタンス化*/
	@SuppressWarnings("unused")
	static void list5_11() {
		/*static付きメンバクラスのインスタンス化*/
		Outer.Inner ic = new Outer.Inner(); //「外部クラス名.内部クラス名」で利用
		/*static無しメンバクラスのインスタンス化*/
		Outer o = new Outer(); //外部クラスのインスタンスがなければnewできない
		Outer.Inner2 oi = o.new Inner2();
	}
	/**list5_12は外部クラスを参照*/
	/**匿名クラスの利用例*/
	@SuppressWarnings("unused")
	static void list5_13() {
		Pocket2<Object> pocket = new Pocket2<>();
		System.out.println("使い捨てのインスタンスを作りpocketに入れます");
		pocket.put(new Object() { //匿名クラスの親クラスを指定 「new 親クラス名(){...}」
			String innerField;
			void innerMethod() {}
			/* 外部クラスのメンバーにはアクセス可能だが、自分を囲むメソッド内にある
			 * finalのついた定数以外のローカル変数にはアクセス不可（ローカルクラスと同様）
			 */
		});
	}
}
/**メンバクラスとローカルクラス（list5_12を含む）*/
class Outer {
	int outerField = 10;
	static int outerStaticField;
	/*staticなメンバクラス*/
	static class Inner{
		void innerMethod() {
			outerStaticField = 10; //staticなメンバのみ利用可能
		}
	}
	/*非staticメンバクラス*/
	class Inner2{
		int i = outerField; //非staticなメンバにアクセス可能
		int j = outerStaticField; //もちろんstaticなメンバーにもアクセス可能
	}
	
	@SuppressWarnings("unused")
	void otherMethod() {
		int a = 10;
		final int A = 10;
		/*同じクラス内ではメンバクラス名のみで利用可*/
		Inner ic = new Inner();
		Inner2 ic2 = new Inner2();
		/*ローカルクラス*/
		class Inner3{
			public void innerMethod() {
				System.out.println(outerField);
				/* 外部クラスのメンバーにはアクセス可能だが、自分を囲むメソッド内にあるfinalの
				 * ついた定数以外のローカル変数にはアクセス不可（Aは使えるがaは使えない）
				 */
			}
		}
		Inner3 ic3 = new Inner3(); //宣言したメソッド内でのみ利用可能
		ic3.innerMethod();
	} //メソッド終了の地点でローカルクラスは利用できなくなる
}
