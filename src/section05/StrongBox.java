package section05;

/**Q5*/
public class StrongBox<E> {
	private E elm;
	private KeyType key;
	private int count;
//	private int[] counts = new int[4];
	public StrongBox(KeyType key) {
		this.key = key;
	}
	public void put(E elm) {
		this.elm = elm;
	}
	public E get() {
		this.count++;
		switch(this.key) {
		case PADLOCK:
			if(count < 1024) return null;
			break;
		case BUTTON:
			if(count < 10000) return null;
			break;
		case DIAL:
			if(count < 30000) return null;
			break;
		case FINGER:
			if(count < 1000000) return null;
			break;
		}
		this.count = 0;
		return this.elm;
/*		switch(key) {
		case PADLOCK:
			if(counts[0] > 1024) return this.elm;
			counts[0]++;
			break;
		case BUTTON:
			if(counts[1] > 10000) return this.elm;
			counts[1]++;
			break;
		case DIAL:
			if(counts[2] > 30000) return this.elm;
			counts[2]++;
			break;
		case FINGER:
			if(counts[3] > 1000000) return this.elm;
			counts[3]++;
			break;
		}
		return null;
*/	}
}
enum KeyType{
	PADLOCK,BUTTON,DIAL,FINGER
}
