package section10;

import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**様々なファイル形式*/
public class List10 {
	public static void main(String[] args) {
		list10_2();
		System.out.println("--3------------------");
		list10_3();
		list10_5();
		System.out.println("--4------------------");
		list10_4();
		System.out.println("--6------------------");
		list10_6();
		System.out.println("--9------------------");
		list10_9();
	}
/*データフォーマット
	 * 複数のデータをどのような形式・順序で保存するかということを定めたルールのことをデータ
	 * フォーマットといい、特にファイル内のデータ構造を規定するものをファイルフォーマット、
	 * ネットワーク経由で伝送されるものをメッセージフォーマットと呼ぶ。
	 * 古くから使われていたり、なんらかの機関が制定している、世の中で広く使われ互換性の高い
	 * 汎用フォーマットがいくつか存在する。
	 */
	/**list10_1は省略*/
	/**CSV形式*/
	static void list10_2() {
		Hero h = new Hero("ミナト");
		
		try(Writer bw = new BufferedWriter(new FileWriter("rpgsave.dat"));){
		bw.write(h.getName());
		bw.write(",");			//デリミタ（区切り文字）の出力
		bw.write(((Integer)h.getHp()).toString());
		bw.write(",");
		bw.write(((Integer)h.getMp()).toString());
		bw.write("\n");			//,が列の区切り、\nが行の区切りとなる
		bw.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**StringTokenizerを使った文字列の分割*/
	static void list10_3(){
		try(Reader br = new FileReader("rpgsave.dat");){
			int i=0;String str="";
			while(i!=-1) {
				i=br.read();
				char c = (char) i;
				str += c; //データを一文字ずつ取得
			}
			StringTokenizer st = new StringTokenizer(str,","); //カンマをデリミタに指定
			while(st.hasMoreTokens()) {
				System.out.println(st.nextToken()); //１つずつコンソールに表示
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*プロパティファイル形式
	 * ・データは文字情報として保存、拡張子は「.properties」
	 * ・行ごとに「キー」と「値」をペアとして、それらを「ペアデリミタ」で区切る
	 * ・ペアデミリタは、"=",":"," "(空白)のいずれかの文字
	 * ・"#"または"!"で始まる行はコメントとして無視
	 */
	/**プロパティファイルへの書き込み*/
	static void list10_5() {
		try(Writer fw = new FileWriter("rpgsave.properties")){
			Properties p = new Properties(); //propertiesインスタンスの作成
			p.setProperty("heroName","アサカ");	//
			p.setProperty("heroHp","62");		//データのセット
			p.setProperty("heroMp","45");		//
			p.store(fw,"勇者の情報"); //先頭にコメントを追加してファイルへ書き出す
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	/**プロパティファイルの読み込み*/
	static void list10_4() {
		try(Reader fr = new FileReader("rpgsave.properties")){
			Properties p = new Properties();
			p.load(fr); //ファイルの内容を読み込む
			String name = p.getProperty("heroName");	//キーを指定し
			String strHp = p.getProperty("heroHp");	//値を取り込む
			int hp = Integer.parseInt(strHp); //Stringで取り込まれるので変換が必要
			System.out.println("勇者の名前："+name+"\n勇者のHP："+hp);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
/*XML形式
	 * ・ネスト（入れ子構造）のデータを表現できる
	 * ・タグと呼ばれる不等号記号で囲われたラベルの間にデータを記述する
	 * ・XMLファイルを操作するためのAPIとしてJAXPが存在する
	 */
	/**rpgsave.xmlの読み込みを実現するコード*/
	static void list10_6() {
		try(InputStream is = new FileInputStream("rpgsave.xml")){ //読み込むファイルの設定
			Document doc //XML全体を取得
				= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			Element hero = doc.getDocumentElement(); //一番外側のheroタグを取得
			Element weapon = findChildByTag(hero,"weapon"); //heroタグ内のweaponタグを取得
			Element power = findChildByTag(weapon,"power"); //weaponタグ内のpowerタグを取得
			String value = power.getTextContent(); //powerタグ内の文字情報を取得
			System.out.println(value);
		}catch(Exception e) {e.getStackTrace();}
	}
	/**list10_6用 指定された名前を持つタグの最初の子タグを返す*/
	static Element findChildByTag (Element self,String name) throws Exception{
		NodeList children = self.getChildNodes(); //すべての子を取得
		for(int i=0;i<children.getLength();i++) {
			if(children.item(i) instanceof Element) {
				Element e = (Element) children.item(i);
				if(e.getTagName().equals(name)) return e; //タグ名を照合
			}
		}
		return null;
	}
	/**list10_7は省略*/
	/**list10_8はSerializeHeroクラス参照*/
	/**直列化を利用してデータを保存・復元するコード*/
	static void list10_9() {
		//Serializableを実装し直列化に対応したHeroクラス
		SerializeHero hero1 = new SerializeHero("ミナト",75,18);
		hero1.setSword(new SerializeSword("鋼の剣"));
		SerializeHero hero2 = null;
		try {
			//インスタンスの直列化と保存
			FileOutputStream fos = new FileOutputStream("rpgsave.dat");
			try (ObjectOutputStream oos = new ObjectOutputStream(fos);){
				oos.writeObject(hero1); //インスタンスからバイト列へシリアライズ
				//oos.flush();
				//oos.close();
			}
			//ファイルからインスタンスを復元
			FileInputStream fis = new FileInputStream("rpgsave.dat");
			try (ObjectInputStream ois = new ObjectInputStream(fis);){
				hero2 = (SerializeHero) ois.readObject(); //デシリアライズ
				//ois.close();
			}
		}catch(Exception e) {
			System.out.println("Exception:"+e);
		}
		if(hero2!=null) System.out.println(hero2.toString());
	}
	/*直列化の注意点
	 * 1.Serializableを実装していないクラス型のフィールドは直列化の対象にならない
	 * 2.staticがついたフィールドは直列化の対象にならない
	 * 3.transientキーワードで修飾したフィールドは直列化の対象にならない
	 */
	/*シリアルバージョンUID
	 * クラスの改良前後で直列化して保存したファイルに矛盾が生じる場合がある。これを防ぐ機能として
	 * シリアルバージョンUIDをクラスフィールドとして宣言することができる
	 * list10_11はSerializeHeroを参照
	 */
}
