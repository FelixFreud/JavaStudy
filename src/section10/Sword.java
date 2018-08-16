package section10;

class Sword implements Cloneable{
	private String name;
	public Sword(String name) {
		this.name = name;
	}

	@Override
	public Sword clone() {
		return new Sword(this.name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}