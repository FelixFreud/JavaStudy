package section06;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.stream.IntStream;

import section04.Hero;

/**ラムダ式と関数オブジェクト*/
public class List6 {

	public static void main(String[] args) {
		//list6_3();
		addition6_1();
	}
	
/*関数オブジェクト*/
	/* 第一級オブジェクト
	 * プログラムの実行中に生成したり変数に代入できるもの
	 * Java8以降では、データ、データ構造、インスタンス、関数がこれにあたる
	 */
	/* Javaにおける関数
	 * 何らかの情報(Input)を引数として受け取り、
	 * 何らかの処理(Process)を行い、
	 * 何らかの結果(Output)を戻り値として返す、ひとかたまりの処理ロジック
	 * （ただし広義には、引数の数がゼロ・戻り値がvoidのものも含める）
	 */
	/**list6_1は省略*/
	/*関数とメソッドの違い
	 * メソッドとはクラスに属する一種の関数である
	 * 関数にとって名前は重要ではなく必須でもない、重要なのは入力・処理・出力が明確であること
	 */
	/**メソッドを変数に代入して呼び出す（パッケージのSAMインターフェースを用いる場合） List6_1クラスも参照*/
	static void list6_2() {
		/*静的(static)メソッドの場合
		 * 変数名＝クラス名::そのクラスの静的メソッド名*/
		IntBinaryOperator func = List6_1::sub;	//変数へのメソッド参照の格納
		int a = func.applyAsInt(5,3);			//格納したメソッドの呼び出し
		System.out.println("5-3="+a);
		/**動的(非static)メソッドの場合
		 * 変数名＝インスタンス変数名::そのインスタンスのメソッド名*/
		List6_1 ins = new List6_1();			//インスタンス生成
		IntBinaryOperator func2 = ins::sum;		//変数へのメソッド参照の格納
		int b = func2.applyAsInt(5,3);			//格納したメソッドの呼び出し
		System.out.println("5+3="+b);
	}
	/*SAMインターフェース
	 * 格納したい関数オブジェクトと同じ引数や戻り値を持った抽象メソッドを１つだけ持つインターフェース
	 * SAMインターフェース型の変数にメソッド参照を格納できる
	 */
	/**関数格納用のインターフェース*/
	public interface MyFunction{					//インターフェース名は任意
		public abstract int call(int x,int y);	//格納したい関数と同じ引数と戻り値（名前は任意）
	}
	/**メソッドを変数に代入して呼び出す（関数格納用のインターフェースを用いる場合） MyFunctionインターフェースも参照*/
	static void list6_3() {
		MyFunction func = List6_1::sub;
		int a = func.call(5,3); //関数を格納した変数の名前.SAMインターフェースのメソッド名(引数) で呼び出す
		System.out.println("5+3="+a);		
	}
	/**int.util.functionパッケージに用意されたSAMインターフェース*/
	/* 名称						引数		戻り値		メソッド
	 * intBinaryOperator	int,int	int			applyAsInt
	 * intToLongFunction	int			long		applyAsLong
	 * intToDoubleFunction	int			double		appryAsDouble
	 * IntConsumer			int			(void)		accept
	 * IntSupplier			なし		int			getAsInt
	 * intPredicate			int			boolean	test
	 */
	/**ラムダ式*/
	static void list6_4() {
		IntBinaryOperator func = (int a,int b)->{return a-b;};	//この行が実行された瞬間、
		int a = func.applyAsInt(5,3);								//関数の実態が生成される
		System.out.println("5-3="+a);
		/* ラムダ式の例
		 *	(Hero h) -> {return h.getName();}		//Heroインスタンスを受け取りそのnameフィールドを返す
		 *	() -> {return new java.util.Date();}	//何も受け取らず、現在日時を返す
		 *	(long[] array) -> {															//long配列を受け取り、
		 *		long[] array2 = java.util.Arrays.copyOf(array,array.length);	//そのコピーを作り、
		 *		java.util.Arrays.sort(array2);										//コピーの内容を並び替えて
		 *		return array2;															//コピーを返す
		 *	}
		 *	(IntBinaryOperator func,int a,int b) -> {					//関数オブジェクトを受け取り、
		 *		return func.applyAsInt(a,b)+func.applyAsInt(a,b);		//２回呼び出した合計を返す
		 *	}
		 */
		/* ラムダ式の省略記法
		 * IntToDoubleFunction func = (int x) -> {return x*x*3.14;};
		 * IntToDoubleFunction func = (x) -> {return x*x*3.14;};	//代入式の左辺（代入先の抽象メソッドから推測）
		 * IntToDoubleFunction func = x -> {return x*x*3.14;};		//ラムダ式の引数が１つ
		 * IntToDoubleFunction func = x -> x*x*3.14;					//単一のreturn文
		 */
	}
	/**ブロック外部の変数にアクセスするラムダ式*/
	@SuppressWarnings("unused")
	static void list6_5() {
		double b = 1.41;
		IntToDoubleFunction func = x -> x*x*b;	//この行が実行される段階で、アクセス可能なすべての
	}													//情報を記憶し、ラムダ式内で利用できる
/*Streamによる関数の活用*/
	/**コレクションでよく行うStreamによる処理*/
	static void addition6_1() {
	/* ファイルの読み書きやネットワーク通信などのストリームとは意味が異なるので注意*/
		List<Integer> list1 = new ArrayList<>();
		List<Hero> list2 = new ArrayList<>();
		/*for文を用いた処理とforEach()を用いた処理の比較*/
		for(Integer i:list1){i=i*2;}
		list1.stream().forEach(i->i=i*2);
		for(Hero h:list2){h.sleep();}
		list2.stream().forEach(h->h.sleep());
		/*配列に対するStream処理*/
		int[] data = {1,2,3};
		IntStream st = Arrays.stream(data);
		st.forEach(x->x=x*x);
		Arrays.stream(data).forEach(x->System.out.print(x));
		/* Streamをうまく用いると、従来の記述より何倍も処理を高速化できることがある
		 * 例えばstream()の代わりにparallelStream()を用いることによってマルチスレッド処理を行える
		 */
	}
}
/**メソッドは関数の一種*/
class List6_1 {
	/*プログラム起動の地点で実体が生成され、mainメソッドが動き始めるときにはすでに存在する*/
	public static int twice(int x) {return x*2;}		//f(x)=2xの意味（名前がfでなくtwiceなだけ）
	public static int sub(int a,int b) {return a-b;}	//f(a,b)=a-b（名前がfでなくsubなだけ
	public int sum(int a,int b) {return a+b;}
	/* メソッドには名前があるが、関数においては入力、処理、出力が明確であることが重要であって、名前は
	 * 必須ではない
	 */
}