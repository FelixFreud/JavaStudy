package section12;

import java.sql.*;

public class List12 {

	public static void main(String[] args) {
		list12_1();
	}

/*データベースとは
	 * 常時動作しデータの格納や検索に関する要求を待ち受ける、DBMSと呼ばれるプログラムを駆使し
	 * データを管理すること、またそのデータそのもの
	 * Javaからの操作にはSQLという専用の言語で書かれた操作命令を送ることになる
	 *データベースのメリット
	 * 1.処理が非常に高速
	 * 2.同時に複数のプログラムから利用可能
	 * 3.処理の中断や異常終了に強い
	 */
/*データベースの基本操作
	 * データを一つ以上の表として内部に保持しており、表にはひと塊のデータとしての行と特定の種類の
	 * データを表す列がある
	 * データベースのアクセスにはデータベース操作専用のAPIであるJDBCを用いる
	 */
	/*java.sqlに含まれる主なJDBC API
	 * DriveManager			DBMSへの接続準備のために利用する
	 * Connection				DBMSへの接続や切断の際に利用する
	 * PreparedStatement	SQL文を送信する際に利用する
	 * ResultSet				DBMSから検索結果を受け取る際に利用する
	 */
	/*JDBCドライバ
	 * JDBC APIを介してJDBCドライバを動かしデータベースと接続するためドライバを切り替えるだけで
	 * 異なる種類のデータベースを利用することができる
	 */
/*データベース接続と切断*/
	/**JDBCを操作する基本パターン*/
	static void list12_1() {
		//step 0: 事前準備（jar配置後）
		try {
			//JDBCドライバの有効化
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			//JDBCドライバjarが見つからない場合の処理
			e.printStackTrace();
		}
		//Connection con = null;
		try(
				//step 1: データベースの接続（引数はJDBC URLまたはJDBC URL,接続ID,パスワード）
				Connection con = DriverManager.getConnection
					("jdbc:h2:~/Dropbox/Java/スッキリわかるJava入門 実践編 第２版/rpgdb");
		){
			//step 2: SQL送信処理
			addition12_1(con);
			list12_2(con);
			System.out.println("-------------------------");
			list12_3(con);
		}catch(SQLException e) {
			e.printStackTrace();
		}//finally {
			//step 3: データベースの切断
			/* アクセス数に限りがあるため必ずcloseで切断すること
			 */
		/*	if(con!=null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}*/
	}
/*SQL文の送信*/
	/**SQL送信パターン１更新系（List12_1のStep2部分）*/
	static void list12_2(Connection con) throws SQLException{
		//Step1 送信すべきSQL文のひな型を準備
		PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM MONSTERS WHERE HP <= ? OR NAME =?");
				//?(パラメータ)のところに値が入る
		//Step2 ひな型に値を流し込みSQL文を組み立てて送信する
		pstmt.setInt(1, 10); //引数はパラメーター番号,値
		pstmt.setString(2, "ゾンビ"); //setDouble、setDate、setTimestamp等もある
		int r = pstmt.executeUpdate();
		//Step3 処理結果を判定する
		if(r != 0) System.out.println(r + "件のモンスターを削除しました");
		else System.out.println("該当するモンスターはいませんでした");
		//後片付け
		pstmt.close();
	}
	/**SQL送信パターン２検索系*/
	static void list12_3(Connection con) throws SQLException{
		
		//Step1 送信すべきSQL文のひな型を準備
		PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM MONSTERS WHERE HP >= ?");
		
		//Step2 ひな型に値を流し込みSQL文を組み立てて送信する
		pstmt.setInt(1, 10);
		ResultSet rs = pstmt.executeQuery();
		
	/*結果表の処理
 	 * ResultSetではイテレーターのように注目している特定の１行の情報しか取り出すことができない
	 */
	/*ResultSetオブジェクトが備える主なオブジェクト
	 * boolean next()						対象の行を１つ進めtrueを返すか、最終行ならfalseを返す
	 * int getInt(int colIndex)			指定した列の整数値を取り出す。１から始まる列番号または
	 * int getInt(String colName)			列名で指定する
	 * String getString(int colIndex)	指定した列の文字列を取り出す。１から始まる列番号または
	 * String getString(String colName)	列名で指定する
	 */
		//Step3 結果表を処理する
		/**結果表処理パターン１（複数行）list12_4*/
		while(rs.next()) {
			System.out.println(rs.getString("NAME"));
		}
		
		System.out.println(" -------------------------");
		
		pstmt = con.prepareStatement("SELECT * FROM MONSTERS WHERE NAME='ゴブリン'");
		rs = pstmt.executeQuery();
		
		/**結果表処理パターン２（単一行）list12_5*/
		if(rs.next()) {
			System.out.println("ゴブリンのHPは"+rs.getInt("HP"));
		}else {
			System.out.println("ゴブリンはDBに存在しません");
		}
		
		rs.close();
		pstmt.close();
	}

/*トランザクション処理
	 * 送信する一つ以上のSQL要求を１つのグループとして扱う機能をトランザクションという
	 * グループ内のすべての処理が成功した場合のみ処理結果を確定（コミット）し、途中で
	 * 失敗があった場合はトランザクション実行前の状態に戻す（ロールバック）
	 */
	/**トランザクションを利用した基本パターンコード*/
	static void list12_6() {
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Connection con = null;
		try(
				Connection con = DriverManager.getConnection("jdbc:h2:~/rpgdb");
		){
			//con = DriverManager.getConnection("jdbc:j2:~/rpgdb");
			/*デフォルトではSQLが一つ送信されるたびに自動的にコミットされるため、それをオフにする*/
			con.setAutoCommit(false);
			
			//メインのDB処理（SQL送信）//
			
			/*一連のSQL文をすべて送信し終わったら手動でコミットする*/
			con.commit(); //これ以前のSQL要求が１つのトランザクションとみなされれる
		} catch (SQLException e) {
			//try {
				/*処理をキャンセルしロールバックさせたい場合*/
			/*	con.rollback(); //明示的に呼び出さなくても問題があれば自動でロールバックされる
			}catch(SQLException e2) {
				e2.printStackTrace();
			}finally {
				if(con != null) {
					try {
						con.close();
					}catch(SQLException e3) {
						e3.printStackTrace();
					}
				}
			}*/e.printStackTrace();
		}
	}

	/**データベースの準備*/
	static void addition12_1(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement
				("CREATE TABLE IF NOT EXISTS MONSTERS (NAME char primary key,HP int)");
		pstmt.executeUpdate();
		String[] names = {"ゾンビ","ゴブリン","スライム","ガイコツ"};
		int[] hp = {12,15,9,16};
		for(int i=0;i<names.length;i++) {
			PreparedStatement pstmt2 = con.prepareStatement
					("SELECT * FROM MONSTERS WHERE NAME=?");
			pstmt2.setString(1, names[i]);
			ResultSet rs = pstmt2.executeQuery();
			if(!rs.next()) {
				pstmt = con.prepareStatement("INSERT INTO MONSTERS (NAME,HP) VALUES (?,?)");
				pstmt.setString(1, names[i]);
				pstmt.setInt(2, hp[i]);
				pstmt.executeUpdate();
			}else {
				pstmt = con.prepareStatement("UPDATE MONSTERS SET HP=? WHERE NAME=?");
				pstmt.setInt(1, hp[i]);
				pstmt.setString(2, names[i]);
				pstmt.executeUpdate();
			}
			pstmt2.close();
			pstmt.close();
		}
	}
}
