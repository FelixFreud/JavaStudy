package section09;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
/**ファイル操作*/
public class List9 {
	public static void main(String[] args) {
		try {
			list9_1();
			System.out.println("--2----------------");
			list9_2();
			list9_3();
			list9_4();
			System.out.println("--5----------------");
			list9_5();
			System.out.println("--6----------------");
			list9_6();
			addition9_1();
			System.out.println("--2----------------");
			addition9_2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*テキストファイルの読み書き*/
	/**FileWriterを用いたサンプルコード*/
	static void list9_1() throws IOException{
		FileWriter fw = new FileWriter("rpgsave.dat",false); //開く
		/* FileWriterのインスタンス化により、ファイルを開くことができる。指定したファイルが
		 * 存在しない場合は自動的に作成される。
		 * 第１引数に文字列を指定する場合はフォルダの区切り記号を\\と２つ重ねることに注意。
		 * 第２引数にtrueを指定した場合はファイルの末尾にデータを追加していき、falseを指定した
		 * 場合または第２引数を指定しない場合はファイルの先頭からデータを上書きしていく。
		 */
		fw.write("Hello Java"); //書く
		fw.flush(); //強制書き込み
		/* write()メソッド実行時、すぐにデータが書き込まれるとは限らず（バッファによる）、
		 * ファイルを閉じる前にflash()で書き込み待ちのデータをその場で書き込ませる必要がある
		 */
		fw.close(); //閉じる
		/* 上記いずれのメソッドもjava.io.IOExceptionを送出する可能性がある（例外処理が必要）*/
	}
	/**FileReaderを用いたサンプルコード*/
	static void list9_2() throws IOException{
		FileReader fr = new FileReader("rpgsave.dat"); //開く
		/* すく数文字を読み込むメソッドも存在するが基本は１文字ずつ読み込む
		 */
		System.out.println("すべてのデータを読み込んで表示します");
		int i = fr.read();
		/* １文字を読み込んだらその文字情報をint型（charデータの数値表現）として返し、ファイルの末尾まで
		 * 読み切ったあとは-1を返す
		 */
		while(i!=-1) {
			char c = (char) i;
			/* intからcharにキャストして文字情報から文字に戻す*/
			System.out.print(c);
			i=fr.read();
		}
		System.out.println("\nファイルの末尾に到達しました");
		fr.close(); //閉じる
		/* 上記いずれのメソッドもjava.io.IOExceptionを送出する可能性がある（例外処理が必要）*/
	}
/*バイナリファイルの読み書き
	 * 文字として解釈可能な文字列データを持つテキストファイルに対し、文字とは解釈できない
	 * バイト列データを持つもの（画像ファイルなど）をバイナリファイルと呼ぶ
	 * バイナリファイルには、文字以外のデータを扱える、小さなデータ容量で済む、高速で処理できる
	 * といったメリットがあり、データの保存や伝送のために広く利用されている
	 * ただし、文字'A'を書き込むことと、バイト01000001を書き込むことは実質的に同じである
	 */
	/**FileOutputStreamを用いたサンプルコード*/
	static void list9_3() throws IOException {
		FileOutputStream fos = new FileOutputStream("rpgsave2.dat",true);
		fos.write(65); //２進数の 01000001 が書き込まれる
		fos.flush();
		fos.close();
	}
/*ファイルの閉じ損ね*/
	/**正しく例外処理を行うFileWriterサンプルコード*/
	static void list9_4() throws IOException {
		try(FileWriter fw = new FileWriter("rpgsave3.dat",true);){ //自動でcloseする
			fw.write('A');
			fw.flush();
		}catch(IOException e) {System.out.println("ファイル書き込みエラー");}
	}
/*ストリーム
	 * メモリ領域には限りがあるので、JVMの外部にあるデータは少しずつ読み書きするのが基本
	 * このデータの読み書きの流れはストリームと呼ばれ、文字列が流れるものを文字ストリーム、
	 * バイト列が流れるものをバイトストリームと呼ぶ
	 * JVMは起動時に以下の３つのストリームを自動的に準備する
	 * ・名称（通称）			・パッケージ名	・標準の接続先	・用途
	 * 標準出力(stdout)		System.out		ディスプレイ	通常の出力画面
	 * 標準エラー出力(stderr)	System.err		ディスプレイ	エラー情報の画面表示
	 * 標準入力(stdin)			System.in			キーボード		キーボードからの入力
	 */
	/**文字列を終端に持つストリーム*/
	static void list9_5() throws IOException{
		String msg = "第１土曜";
		Reader sr = new StringReader(msg); //String型変数から一文字ずつ読み取る機能
		char[] c = new char[msg.length()];
		
		for(int i=0;i<c.length;i++) c[i] = (char) sr.read(); //一文字ずつchar配列に格納される

		msg = "";
		Writer sw = new StringWriter(); //文字列に一文字ずつ書き込む機能
		
		for(char ch:c) sw.write(ch);
		msg = sw.toString(); //String型文字列に変換して代入
		System.out.println(msg);
	}
	/**バイト配列を終端に持つストリーム*/
	static void list9_6() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	//バイト配列に1バイトずつ
		baos.write(65);														//書き込む機能
		baos.write(66);
		
		byte[] data = baos.toByteArray(); //
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);	//バイト配列から1バイト
		System.out.println(bais.read());																			//ずつ読み込む機能
	}
/*フィルタ*/
	/**フィルタの活用*/
	static void addition9_1() throws Exception {
        // 暗号化オブジェクト・初期ベクトルを作成
		Cipher c = Cipher.getInstance( "RSA/ECB/PKCS1Padding" );
		// RSA鍵作成クラスを初期化
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance( "RSA" );
		keyGen.initialize( 1024 );
		// RSA鍵ペアを作成
		KeyPair keyPair = keyGen.generateKeyPair();
		Key publicKey = keyPair.getPublic();
		c.init( Cipher.DECRYPT_MODE , publicKey );
		/*ファイル出力ストリームの作成*/
		FileOutputStream fos = new FileOutputStream("sata.txt");
		/*ストリームの下流に暗号化ストリームを接続（この例ではalgotithmがないため実行はできない）*/
		CipherOutputStream cos = new CipherOutputStream(fos,c);
		/*暗号化ストリームの上流にバイト変換をするストリームを接続*/
		OutputStreamWriter osw = new OutputStreamWriter(cos);
		osw.write("AB"); //oswにを書き込めば、バイト変換・暗号化された上でファイルに流されていく
		osw.close(); //接続されているfosとcosも連鎖的に閉じる
	}
	/**バッファリングフィルタ*/
	@SuppressWarnings("resource")
	static void addition9_2() throws IOException{
		/* BufferedReader,BufferedWriter,BufferedInputStream.BufferedOutputStreamの４つは変換はせず、
		 * データの集約をする働きがある
		 */
		FileReader fr = new FileReader("rpgsave.dat");
		BufferedReader br = new BufferedReader(fr); //FileReaderの下流にBufferedReaderを接続
		String line = null;
		while((line=br.readLine())!=null) { //１行分のString型データをlineに代入
			System.out.println(line);
		}
	}
/*ファイルシステムの操作*/
	File file = new File("rpgsave.dat");
		//Fileクラスで特定のファイルやフォルダを示すインスタンスを作成できる（ただし古いクラス定義）
		//ファイル入出力ストリームのコンストラクタ引数として指定可能
		//ファイル操作用のメソッドを持つが、中途半端にしか揃っていない
	Path p1 = Paths.get("rpgsave.dat"); //String文字列でファイルパスを指定
	Path p2 = file.toPath(); //Fileインスタンスからファイルパスを読み込む
		//Pathクラスは、ファイルやフォルダを指し示すことに特化した新しいクラス
		//Pathに対し、ファイルやフォルダを操作することに特化したFilesクラスが存在する
	/* java.nio.file.Filesクラスが備える代表的なメソッド。Files.～で呼び出す
	 * ・操作					・メソッドのシグニチャ
	 * コピー					static long　copy(Path source, OutputStream out)
	 * 移動（改名）				static Path move(Path source, Path target, CopyOption... options) 
	 * 削除						static void delete(Path path) 
	 * 中身をすべて読み込む		static byte[] readAllBytes(Path path) 
	 * 全行を読み込む			static List<String> readAllLines(Path path) 
	 * 存在するか確認			static boolean exists(Path path, LinkOption... options) 
	 * フォルダであるか確認	static boolean isDirectory(Path path, LinkOption... options) 
	 * ファイルサイズを確認	static long size(Path path) 
	 */

}
