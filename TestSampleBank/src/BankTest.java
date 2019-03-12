

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankTest {
	
	
	@Test
	public void test() {
		BankManageTest bmt = new BankManageTest();
		Assert.assertEquals(null, bmt.getCustomer(1));
				
		
	}

}
