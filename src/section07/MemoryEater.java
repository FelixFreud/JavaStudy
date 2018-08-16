package section07;

public class MemoryEater {

	public static void main(String[] args) {
		long[] l = new long[1280000];
		for(int i=0;i<l.length;i++) l[i]=i;
	}

}
