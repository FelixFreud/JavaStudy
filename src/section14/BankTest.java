package section14;
import org.junit.Test;
import static org.junit.Assert.*;
/*Q14*/
public class BankTest {
	//正常系 nameに"ミナト"を設定できるか
	@Test public void setRegularName() {
		Bank b = new Bank();
		b.setName("ミナト");
	}
	//異常系 nameにnullを設定すると例外が送出されるか
	@Test public void setNullName() {
		Bank b = new Bank();
		try {
			b.setName(null);
		}catch(NullPointerException e) {
			return;
		}
		fail();
	}
	//異常系 nameに３文字未満のStringを設定すると例外が送出されるか
	@Test(expected = IllegalArgumentException.class)
	public void setShortName() {
		Bank b = new Bank();
		b.setName("ミナ");
	}
}
