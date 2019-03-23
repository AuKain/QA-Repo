package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Main;

public class TestMain {

	@Test
	public void testMain() {
		String[] args = null;
		assertEquals( "", Main.main( args ) );
	}

}
