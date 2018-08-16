package section14;
/*1.パッケージのインポート*/
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	/*list14_2
	public static void main(String[] args) {
		testInstantiate();
		testTransfer();
	}
	private static void testInstantiate() {
		System.out.println("無事newできるかテストします");
		Account a = new Account("ミナト",30000);
		if(!"ミナト".equals(a.getOwner())) {
			System.out.println("失敗―名義人がおかしい");
		}
		if(30000 != a.getBalance()) {
			System.out.println("失敗―残高がおかしい");
		}
		System.out.println("テストを終了します");
	}
	private static void testTransfer() {}
	*/
	/*2.テストクラスの中に複数のテストメソッド*/
	/*3.mainメソッドは記述しない*/
	/*list14_3*/
	@Test public void instantiate() { /*4.テストメッソドには@Testをつける*/
		Account a = new Account("ミナト",30000);
		/*5.値の確認などには専用のメソッドを使う*/
		assertEquals("ミナト",a.getOwner());
		assertEquals(30000,a.getBalance());
		/*JUnitで利用可能な主な評価用メソッド
		 * assertEquals(期待値,実際値)	：実際値が期待値と等価か
		 * assertSame(期待値,実際値)	：実際値が期待値と等値か
		 * assertNull(実際値)			：実際値がnullか
		 * assertNotNull(実際値)		：実際値がnull以外か
		 * fail()							：検証失敗
		 */
	}
	@Test public void transfer() {}
}
