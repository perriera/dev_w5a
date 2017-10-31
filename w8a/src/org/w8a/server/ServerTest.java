package org.w8a.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServerTest {

	@Test
	public void testMain() throws Exception {
		String[] params = { "hello, world" };
		Server.main(params);
	}

}
