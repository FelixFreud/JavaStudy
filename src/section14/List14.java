package section14;
/**単体テストとアサーション*/
public class List14 {
/*不具合に対するアプローチ
	 *単体テスト (Unit Test)
	 * プログラムを構成するある一つの部品について、例えば一つのクラスのすべてのメソッドが
	 * 正常に動作するか確認していくテスト。大抵の不具合はこの段階で修正される
	 *結合テスト (Integration Test)
	 * 単体テストが完了している完成部品をいくつか繋いで動作させ、設計通りに動作するか確認
	 * するテスト
	 *総合テスト (System Test)
	 * すべての部品を結合して行う最後のテスト
	 */
/*単体テストの実行*/
	/**list14_1はAccount.javaを参照*/
	/**list14_2はAccountTest.javaを参照*/
	/*テストケースの考え方
	 * 正常系：正常に動作するかを確認する。最低限１つは用意する
	 * 異常系：想定される異常な利用時に、きちんとエラー処理がなされるかを確認する。主に想定
	 * 　　　　されるものを複数作る
	 */
/*JUnitを用いたテスト*/
	/*JUnit
	 * オープンソースのテスティングフレームワーク
	 * 最新版はJUnit5だが、list14_3はJUnit4系を使っているので注意。
	 * JUnit5版はAccountJUnit5Test.javaを参照
	 */
	/**list14_3はAccountTest.javaを参照*/
/*アサーション
 	 * アサーションと呼ばれる簡易なテストケースをソースコード内に直接記述することができる
 	 * 実行中にアサーションの記述した行で評価式が評価され、エラーがあればAssertionErrorが
 	 * 送出されJVMが強制停止する
 	 */
	/**list14_4はAccount.javaを参照*/
	/**list14_5はMain.javaを参照*/
	/*アサーションの有効化
	 * アサーションを有効化するには実行時オプションに-eaを指定すれば良い
	 * eclipseの場合は実行の構成でVM引数に-eaを指定する
	 */
	/*アサーションの注意点
	 *アサーションの無効時に問題となるコードを書かない
	 * アサーションの行でなにかが変更してしまうような記述をすると、本稼働時のアサーションが
	 * 無効となったときに、開発時と異なる動作をしてしまう
	 *単体テストに変わるものではない
	 * ある入力値を与えて目的の結果となるかの確認をするようなテストには向いておらず、他にも
	 * 記述できるテストや利用目的に違いがある
	 */
}