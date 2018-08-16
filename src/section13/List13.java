package section13;

/**基本的な開発ツール*/
public class List13 {
/*javadoc―仕様書の自動生成*/
	/*javadocコマンド
	 * javadoc [オプション] ソースファイル
	 * デフォルトではprotectedかpublicのメンバしか出力されない
	 * オプション
	 * -private				：すべてのメンバを出力する
	 * -d フォルダ				：指定フォルダに出力する
	 * -locale (ja)			：言語の指定
	 * -decencoding (utf-8)：出力するHTMLの文字コード
	 * -charset (utf-8)		：HTMLでの明示的な文字コード宣言
	 * -encoding (utf-8)	：ソースコードの文字コード設定
	 * -author				：@authorの作者情報を出力する（デフォルトでは出力されない）
	 * -version				：@versionのバージョン情報を出力する（デフォルトでは出力されない）
	 */
	/**javadocコメントを加えたソースコード*/
	@SuppressWarnings("unused")
	public static void list13_1() {
		/*クラス宣言の直前に書いたjavadocコメントはそのクラスの解説文になる*/
		/**
		 * 口座クラス。
		 * このクラスは、１つの銀行口座を表します。
		 */
		class Account{
			/*フィールドの直前に書いたjavadocコメントはそのフィールドの解説になる*/
			/** 残高 */
			private int balance;
			/** 口座名義人 */
			private String owner;
			/*メソッド宣言の直前に書いたjavadocコメントはそのメッソドの解説になる*/
			/**
			 * 送金を行うメソッド。
			 * このメソッドを呼び出すと、<b>他の</b>口座に送金します。
			 */                     /*HTMLタグも利用可能*/
			public void trancefer(Account dest,int amount) {}
		}
	}
	/*よく利用されるjavadocタグ C:クラス,F:フィールド,M:メソッド
	 * @author 作者名			C--	：クラスの作者名
	 * @version バージョン	C--	：バージョン情報
	 * @param 引数名 解説		--M	：引数とその解説
	 * @return 解説			--M	：戻り値の解説
	 * @exception FQCN 解説	--M	：出力する可能性がある例外（コンストラクタには利用不可）
	 * @see クラス名			CFM	：参考にしてほしい他のクラス
	 * @since バージョン		CFM	：実装されたバージョン
	 * @deprecated 解説		CFM	：非推奨として表示する
	 */
	/**javadocタグを加えたソースコード*/
	@SuppressWarnings({"unused","serial"})
	public static void list13_2() {
		/**
		 * 口座クラス
		 * @author felix
		 * @version 1.0.0
		 * @deprecated 代わりにNewAccountクラスを利用してください
		 * @see NewAccount
		 */
		class Account implements java.io.Serializable{
			private int balance;
			/**
			 * @param bank 送金先銀行
			 * @param dest 送金先口座
			 * @param amount 送金する金額
			 * @return 送金手数料
			 * @exception java.lang.IllegalArgumenException 残高不足のとき
			 */
			public int transfer (Bank bank, Account dest, int amount) {return 0;}
		}
		class NewAccount implements java.io.Serializable{}
	}	
/*コンパイル*/
	/*アノテーション
	 * コンパイラにはソースコードの文法をチェックして、異常な記述や懸念がある部分をプログラマに
	 * 伝える役割があり、アノテーションを利用して特定の文法チェックを制御することができる
	 * なお、より入念に不適切な記述がないかチェックするには-Xlintオプションをつけてコンパイル
	 * する必要がある
	 */
	/**アノテーションを利用したコード*/
	@SuppressWarnings({"unused","serial"}) //警告の抑制
	static void list13_3() {
		class Account implements java.io.Serializable{
			public int transfer(Bank bank,Account dest,int amount) {return 0;}
		}
		class NewAccount extends Account{
			@Override //オーバーライド宣言（正しく行えていないと警告を送出）
			public int transfer(Bank bank,Account dest,int amount) {return 0;}
			@Deprecated //非推奨の宣言（使用されていると警告を送出）
			public void transfer(Account dest, int amount) {}
		}
	}
/*jarコマンドによるアーカイブ操作
	 *アーカイブの作成
	 * jar -cvf JARファイル名 ファイル及びフォルダ
	 *アーカイブの展開
	 * jar -xvf JARファイル名
	 *アーカイブ内容の閲覧
	 * jar -tvf JARファイル名
	 *実行時のクラスパスにjarファイルを追加
	 * jar -cp JARファイル名 実行ファイル名
	 */
	/*マニフェストファイル
	 * JARファイルには、アーカイブ内に含まれるファイルに関する様々な追加情報を記述したアーカイブ
	 * ファイルを含めるのが一般的であり、MANIFEST.MFという名前のテキストファイルとして用意する
	 * マニフェストファイルの中には「〜:〜」という形式のエントリを記述することが定められおり、その
	 * 先頭にはマニフェストファイルの仕様のバージョンであるManifest-Version: 1.0を記述しなければ
	 * ならない
	 * jar -cvfでJARファイルを作成した場合はマニフェストファイルが自動で生成される。自作のマニ
	 * フェストファイルの内容を反映させたい場合は次のように指定する
	 *  jar -cvfm JARファイル名 マニフェストファイル名 ファイル及びフォルダ
	 *Main-Classエントリ
	 * マニフェストファイルにMain-Class:完全限定クラス名(FQCN)を指定すると、javaコマンドでの起動
	 * 時にメインクラスの指定が不要になり、JARファイルをダブルクリックするだけでプログラムを起動
	 * できるようになる
	 */
/*javaコマンドによるJVMの起動と実行
	 *主なオプション
	 * -cp クラスパス指定				実行時にクラスファイルを検索するクラスパスの指定
	 * -classpath クラスパス指定		上記と同じ
	 * -jar JARファイル名			Main-Classエントリを含むマニフェストが含まれたJARファイルを実行する
	 * -verbose:gc					ガーベッジコレクションが発生するたびに情報が表示される
	 * -Xms ヒープメモリ最小割当量	JVMに割り当てるヒープ（メモリ）の最小値を指定
	 * -Xmx ヒープメモリ最大割当量	JVMに割り当てるヒープ（メモリ）の最大値を指定
	 *ヒープとスタック
	 * OSから割り当てられたメモリはヒープとスタックと呼ばれる２種類の領域に分けてJVMが管理する
	 * 通常頻繁かつ大量に利用されるのはヒープ領域で、例えばnewでインスタンスを生成するたびにヒープの
	 * 一部が消費される。ヒープ領域が不足した状態でnewしようとするとOutOfMemoryErrorが創出され
	 * プログラムが強制停止するが、通常は適切なヒープ容量をJVMが自動的に推測してメモリを確保するため
	 * 明示的にヒープ容量を指定する必要はない
	 *ガーベッジコレクション
	 * ある程度ヒープが残り少なくなると、不要なインスタンスを開放しヒープ容量を回復させるガーベッジ
	 * コレクションが自動的に働く。ただし、ガーベッジコレクションが動作している間は他の処理が完全に
	 * 停止するため、ヒープが無駄に大きいとガーベッジコレクションの処理時間が長くなり、プログラムの
	 * 実行速度が低下することになるので、自分でヒープ割当量を指定する場合は注意が必要
	 *メモリリーク
	 * インスタンスを利用した後、インスタンスをコレクションに格納したままにするようなプログラムミス
	 * で、ヒープを開放できない状態にしてしまうことある。この状態が重なると、ヒープの空き容量がなく
	 * なっていき、ガーベッジコレクションが繰り返し実行されプログラムがフリーズしてしまうという状態
	 * になる。これをメモリリークという
	 */
}