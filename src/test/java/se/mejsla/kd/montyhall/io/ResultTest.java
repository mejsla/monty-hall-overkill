package se.mejsla.kd.montyhall.io;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResultTest {

	@Test
	public void result() throws Exception {
		final int nWithout = 3;
		final int nWith = 6;
		final Result result = new Result(nWithout, nWith);
		assertEquals(nWithout, result.getNumberOfWinsWithoutSwitch());
		assertEquals(nWith, result.getNumberOfWinsWithSwitch());
	}

}
