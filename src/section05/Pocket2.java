package section05;

public class Pocket2<E> {
/* Eは仮型引数でPocket2クラスを利用する際に実型引数を指定する
 * 利用できる実型引数を制限するには継承を利用する
 * 例えば<E extends Character>とすれば、Caracterクラスの子クラスのみが実型引数として利用できる
 */
	private E data;
	public void put(E d) {
		this.data=d;
	}
	public E get() {
		return this.data;
	}
}
