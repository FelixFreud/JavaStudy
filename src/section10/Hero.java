package section10;

public class Hero implements Cloneable{
	private String name;
	private int hp,mp;
	private Sword sword;
	
	public Hero(String name,int hp,int mp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
	}
	public Hero(String name) {
		this(name,1,1);
	}
	public Hero() {
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
		if(!(o instanceof Hero)) return false;
		Hero r = (Hero) o;
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
	public Hero clone() {
		Hero result = new Hero(this.name);
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
	public void setSword(Sword sword) {
		this.sword = sword;
	}
	public Sword getSword() {
		return this.sword;
	}
	/**List6用のダミー*/
	public void sleep() {}
}