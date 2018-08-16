package section10;
import java.io.*;

class SerializeSword implements Cloneable,Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	public SerializeSword(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
	@Override
	public SerializeSword clone() {
		return new SerializeSword(this.name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}