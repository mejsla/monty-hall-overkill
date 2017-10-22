package se.mejsla.kd.montyhall.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandTest {

	@Test
	public void defaultArgs() throws Exception {
		final String[] args = {};
		assertTrue(args.length == 0);
		final Command command = new Command(args);
		assertEquals(Command.Status.OK, command.getStatus());
		assertEquals(Command.DefaultArgs.numberOfRuns, command.getNumberOfRuns());
		assertTrue(command.getStatusMessage().contains("OK"));
	}

	@Test
	public void specifiedNumberOfRuns() throws Exception {
		final int nRuns = 3456;
		final String[] args = {"" + nRuns};
		assertTrue(args.length == 1);
		final Command command = new Command(args);
		assertEquals(Command.Status.OK, command.getStatus());
		assertEquals(nRuns, command.getNumberOfRuns());
		assertTrue(command.getStatusMessage().contains("OK"));
	}

	@Test
	public void toFewRuns() throws Exception {
		final int nRuns = -34;
		final String[] args = {"" + nRuns};
		assertTrue(args.length == 1);
		final Command command = new Command(args);
		assertEquals(Command.Status.ERROR, command.getStatus());
		assertEquals(nRuns, command.getNumberOfRuns());
		assertTrue(command.getStatusMessage().contains("positive"));
	}


	@Test
	public void badArg() throws Exception {
		final String[] args = {"bad"};
		assertTrue(args.length == 1);
		final Command command = new Command(args);
		assertEquals(Command.Status.ERROR, command.getStatus());
		assertTrue(command.getStatusMessage().contains("Unknown"));
	}

	@Test
	public void toManyArgs() throws Exception {
		final String[] args = {"34", "56"};
		assertTrue(args.length == 2);
		final Command command = new Command(args);
		assertEquals(Command.Status.ERROR, command.getStatus());
		assertTrue(command.getStatusMessage().contains("oo many"));
	}

	@Test
	public void help() throws Exception {
		final String[] args = {"-help"};
		assertTrue(args.length == 1);
		final Command command = new Command(args);
		assertEquals(Command.Status.HELP, command.getStatus());
		assertTrue(command.getStatusMessage().contains("Usage"));
	}
}
