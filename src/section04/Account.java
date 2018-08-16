package section04;

public class Account implements Comparable<Account>{ //Comparable<クラス自身>で実装	
	String accountNo;
	public Account(String accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		return accountNo.trim();
	}
	@Override
	public boolean equals(Object o) {
		if(o == this) return true; //等値ならtrue
		if(o == null) return false; //nullならfalse
		if(!(o instanceof Account)) return false; //違うクラスならfalse
		Account r = (Account) o;
		if(!this.accountNo.trim().equals(r.accountNo.trim())) return false; //String値の比較
		return true;
	}
	@Override
	public int compareTo(Account obj) {
	/* colectionクラスのsort()やTreesetを用いる場合、並べ替えにこのcompareToが呼び出される
	 */
		int thisNo = Integer.parseInt(this.accountNo);
		int objNo = Integer.parseInt(obj.accountNo);
		/**戻り値の定義の仕方*/
		if(thisNo<objNo) return -1;
		if(thisNo>objNo) return 1;
		return 0;
		/* 自分自身がobjよりも小さい場合：負の数
		 * 自分自身とobjとが等しい場合：0
		 * 自分自身がobjよりも大きい場合：正の数
		 */
	}
}
