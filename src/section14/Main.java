package section14;
/*アサーション*/
public class Main {

	public static void main(String[] args) {
		/*list14_5 AssertionErrorが起こるはずのコード
		System.out.println("アサートにわざと失敗します");
		assert 1 == 0;
		System.out.println("正常終了します");
*/		/*list14_6 アサーションの副作用
		 * アサーションに値を変更するような記述をするべきではない
		 */
		int age = 33;
		assert (++age >= 20); //アサート実行時のみ++ageが動く
		System.out.println("あなたの来年の年齢は"+age); //アサート実行の有無で値が変わる
	}

}
