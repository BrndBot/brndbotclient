package com.brndbot.client;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brndbot.client.BasicClientResponse;

public class BasicClientResponseTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	public void testBasicResponse () {
		BasicClientResponse cr = new BasicClientResponse("teacher");
		cr.setParameter ("name", "Jane Doe");
		assertEquals ("teacher", cr.getResponseType());
		assertEquals ("Jane Doe", cr.getParameter("name"));
	}
}
