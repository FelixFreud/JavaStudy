package section11;

import java.io.*;
import java.net.*;

/**ネットワーク通信*/
public class List11 {

	public static void main(String[] args) {
		try {
			list11_1();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

/*URLを用いた高水準アクセス*/
	/*java.netパッケージ
	 * Webページの取得などよくあるニーズを手軽に実現する高水準APIと、
	 * IPアドレス、ポート、暗号化通信、プロトコルなど様々な要素を細かく制御する低水準APIがある
	 */
	/**URLを用いてWebページを習得するプログラム*/
	static void list11_1() throws IOException{
		//URLクラスのインスタンス化
		URL url = new URL("http://dokojava.jp");
		//openStream()を呼び出しデータを取り出すストリームを取得する
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		//read()を呼び出しストリームから１バイトずつ情報を取り出す
		int i = isr.read();
		while(i!=-1) {
			System.out.print((char) i);
			i = isr.read();
		}
		isr.close();
	}
/*Socketを用いた低水準アクセス*/
	/**Socketを用いたWebページの習得*/
	static void list11_2() throws IOException{
		//IPアドレスまたはサーバー名とポート番号を指定しSocketインスタンスを生成
		Socket sock = new Socket("dokojava.jp",80);
		//Socketから入力ストリームと出力ストリームを取得
		InputStream is = sock.getInputStream();
		OutputStream os = sock.getOutputStream();
		//HTTP要求を送信
		os.write("GET /index.html HTTP/1.0\r\n".getBytes());
		os.write("\r\n".getBytes());
		os.flush();
		InputStreamReader isr = new InputStreamReader(is);
		//応答の受信
		int i = isr.read();
		while(i!=-1) {
			System.out.print((char) i);
			i = isr.read();
		}
		sock.close(); //Socketを閉じる
	}
/*ServerSocketを使ってサーバーを作る*/
	/**ServerSocketを使ったシンプルなサーバープログラム*/
	static void list11_3() throws Exception{
		System.out.println("起動完了");
		ServerSocket svSock = new ServerSocket(39648); //待受ポート
		Socket sock = svSock.accept(); //接続待受状態になる
		System.out.println(sock.getInetAddress()+"から接続");
		sock.getOutputStream().write("WELCOME".getBytes());
		sock.getOutputStream().flush();
		sock.close();
		/* 複数の相手から同時に接続を受け付けるサーバープログラムはスレッドを駆使し
		 * 実装する必要がある
		 */
	}
}
