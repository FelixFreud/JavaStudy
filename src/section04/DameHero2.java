package section04;

public class DameHero2 {
	public String name;
	public DameHero2(String name) {
		this.name = name;
	}
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(!(o instanceof DameHero2)) return false;
		DameHero2 r = (DameHero2) o;
		if(!this.name.equals(r.name)) return false;
		return true;
	}
}
