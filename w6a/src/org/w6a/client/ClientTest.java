package org.w6a.client;

import org.junit.Test;

public class ClientTest {

	@Test
	public void testMain() throws Exception {
		String[] params = { "hello, world" };
		Client.main(params);
	}

}
