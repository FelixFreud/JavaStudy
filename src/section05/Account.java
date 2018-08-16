package section05;

/***列挙型の定義*/
enum AccountType{
	FUTSU,TOUZA,TEIKI;
} //switch文の分岐にも利用可能
@SuppressWarnings("unused")
public class Account {
	private String accountNo;
	private int balance;
	private AccountType accountType;
	public Account(String aNo,AccountType aType) {
		accountNo = aNo;
		accountType = aType;
	}
	/* aTypeにはAccountType.FUTSU,AcountType.TOUZA,AcountType.TEIKIのいずれかのみ代入できる
	 * import static section05.AccountType.*;のようにインポートすればAcountType.を省略可
	 */
}
