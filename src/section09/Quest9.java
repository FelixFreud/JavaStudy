package section09;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Quest9 {

	public static void main(String[] args){
		try(
				FileInputStream in = new FileInputStream(args[0]);
				FileOutputStream out = new FileOutputStream(args[1]);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				GZIPOutputStream gout = new GZIPOutputStream(bout);
		){
			int i = in.read();
			while(i != -1) {
				gout.write(i);
				i = in.read();
			}
			gout.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
