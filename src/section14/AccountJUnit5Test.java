package section14;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountJUnit5Test {

	@Test
	void instantiate() {
		Account a = new Account("ミナト",30000);
		assertEquals("ミナト",a.getOwner());
		assertEquals(30000,a.getBalance());
	}
	@Test
	void transferTest() {
		Account a = new Account("ミナト",30000);
		Account b = new Account("アサカ",50000);
		a.transfer(b, 10000);
		assertEquals(20000,a.getBalance());
		assertEquals(60000,b.getBalance());
	}

}
