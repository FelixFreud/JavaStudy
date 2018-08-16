package section10;
import java.io.*;
/**list10_8 直列化に対応したHeroクラス*/
public class SerializeHero implements Cloneable,Serializable{
	private String name;
	private int hp,mp;
	private SerializeSword sword;
	/*list10_11 シリアルバージョンUIDの宣言*/
	private static final long serialVersionUID = 81923983183821L; //数値は任意
	//クラス設計に変更があるたび数値を変更する必要がある
	
	public SerializeHero(String name,int hp,int mp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
	}
	public SerializeHero(String name) {
		this(name,1,1);
	}
	public SerializeHero() {
		this("",1,1);
	}

	/**toString()のオーバーライド*/
	@Override
	public String toString() {
		return "勇者（名前="+this.name+"/HP="+this.hp+"/MP="+this.mp+"/剣="+this.sword+"）";
	}
	/**equals()のオーバーライド*/
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(!(o instanceof SerializeHero)) return false;
		SerializeHero r = (SerializeHero) o;
		if(!this.name.equals(r.name)) return false;
		return true;
	}
	/**hashCode()のオーバーライド*/
	@Override
	public int hashCode() {
		int result = 37;
		result = result*31 + name.hashCode();
		return result;
	}
	/**clone()のオーバーライド*/
	@Override
	public SerializeHero clone() {
		SerializeHero result = new SerializeHero(this.name);
		result.hp = this.hp;
		result.mp = this.mp;
		//result.sword = this.sword; // <-これだと浅いコピーとなり、swordへの参照がコピーされるだけ
		result.sword = this.sword.clone(); // <-深いコピー
		return result;
	}
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
	}
	public int getMp() {
		return this.mp;
	}
	public void setSword(SerializeSword sword) {
		this.sword = sword;
	}
	public SerializeSword getSword() {
		return this.sword;
	}
}