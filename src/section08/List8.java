package section08;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**非標準ライブラリーの活用*/
public class List8 {
	public static void main(String[] args) {
		list8_5(args);
	}
	/**list8_1は省略*/
	/**list8_2〜4はBank.javaを参照*/
	/**commons-loggingによるエラー出力*/
	static void list8_5(String[] args) {
		Log logger = LogFactory.getLog(List8.class);
		if(args.length != 2) logger.error("起動変数の数が異常: "+args.length);
	}
	/**代表的なOSSライセンス*/
	/* Public_Domain		:著作権が放棄され、だれでも自由に利用してよい
	 * BSD,MIT,Apache	:著作権表示なとをきちんとしていれば、基本的に自由に利用できる
	 * GPL,LGPL			:1.このライセンスを採用したソフトウェアを含む製品を発売・公表
	 * 							する場合、そのソースコードも公表しなければならない
	 * 						 2.このライセンスを採用したソフトウェアを含む製品を改造したり、
	 * 							一部に利用して別製品を作った場合、その製品もまたGPL（または
	 * 							LGPG）にしなければならない
	 */
	/**Creative_Commonsライセンス : http://creativecommons.jpを参照*/
}
