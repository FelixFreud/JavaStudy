package section11;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class Quest11 {

	public static void main(String[] args) {
		quest11_1();
	}

	/*Q11-1*/
	static void quest11_1(){
		URL url = null;
		try {
			url = new URL("http://dokojava.jp/favicon.ico");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try(
				InputStream is = url.openStream();
				OutputStream os = new FileOutputStream("dj.ico");
		){
			int i = is.read();
			while(i != -1) {
				os.write((byte)i);
				is.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*Q11-2*/
	static void quest11_2() throws IOException{
		Socket sock = new Socket("smtp.example.com",60025);
		try( OutputStream os = sock.getOutputStream(); ){
			os.write("HELO example.com\r\n".getBytes());
			os.write("MAIL FROM: asaka@example.com\r\n".getBytes());
			os.write("RCPT TO: minato@example.com\r\n".getBytes());
			os.write("DATA\r\n".getBytes());
			os.write("From: asaka@example.com\r\n".getBytes());
			os.write("Subject: Please send me your RPG\r\n".getBytes());
			os.write("Hello minato. I would like to play your RPG\r\n".getBytes());
			os.write("Could you please send it to me?\r\n".getBytes());
			os.write(".\r\n".getBytes());
			os.write("QUIT".getBytes());
		}
	}
}
