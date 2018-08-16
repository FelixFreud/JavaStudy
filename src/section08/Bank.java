package section08;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Bank {
	String name;
	String address;
	/*list8_2 EqualsBuilderを用いないequalsメソッド
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(!(o instanceof Bank)) return false;
		Bank r = (Bank) o;
		if(!this.name.equals(r.name)) return false;			//フィールドの数だけ
		if(!(this.address.equals(r.address))) return false;	//この行が必要になる
		return true;*/
	/*list8_3 EqualsBuilderを用いたequalsメソッド*/
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	/**list8_4 HashCodeBuiderを用いたBankクラス*/
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this); //この一行だけでハッシュ値を生成
	}
}
