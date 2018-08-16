package section14;

public class Account {
	/**口座名義人*/
	private String owner;
	/**口座残高*/
	private int balance;
	//public void Account(String owner, int balance){ //戻り値があるのでメソッド扱い
	public Account (String owner,int balance) {
		//owner = owner;
		//balance = balance
		this.owner = owner;
		this.balance = balance;
	}
	public void transfer(Account dest,int amount) {
		dest.balance += amount;
		this.balance -= amount;
		System.out.println("正常に送金完了しました");
		/*list14_4 アサーション*/
		//assert this.balance >= 0;
		assert this.balance >= 0 : "負の残高"+this.balance;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
