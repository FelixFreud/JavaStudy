package section04;

public class DameAccount {
	String accountNo;
	public DameAccount(String accountNo) {
		this.accountNo = accountNo;
	}
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(!(o instanceof DameAccount)) return false;
		DameAccount r = (DameAccount) o;
		if(!this.accountNo.trim().equals(r.accountNo.trim())) return false;
		return true;
	}
}
