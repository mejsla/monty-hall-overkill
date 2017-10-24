package se.mejsla.kd.montyhall;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import se.mejsla.kd.montyhall.io.*;
import se.mejsla.kd.montyhall.simulation.Simulator;

public class App 
{
	public static void main( final String[] args )
	{
		final Command command = new Command(args);
		final Reporter reporter = new Reporter(System.out, System.err);

		switch (command.getStatus()) {
		case OK:
			executeCommand(command, reporter);
			break;
		case HELP:
			reporter.reportHelp(command);
			break;
		case ERROR:
			reporter.reportError(command);
			break;
		default:
			assert false : "Unknown command status";
		}
	}


	/*
	 * An instantiable proxy for the ThreadLocalRandom class that can be injected
	 * as an instance of Random. The purpose is to reduce the risk of contention
	 * that may occur with the regular Random instances when used in a
	 * multithreaded environment.
	 */
	private static class ThreadLocalRandomProxy extends Random {
		private static final long serialVersionUID = 1;
		public ThreadLocalRandomProxy() {}

		@Override
		public int nextInt(final int bound) {
			return ThreadLocalRandom.current().nextInt(bound);
		}
	}

	private static void executeCommand(final Command command, final Reporter reporter) {
		final int numberOfRuns = command.getNumberOfRuns();
		final int numberOfDoors = 3;
		final Simulator simulation = new Simulator(numberOfRuns, numberOfDoors, new ThreadLocalRandomProxy());
		final long startTime = System.currentTimeMillis();
		final int numberOfWinsWithoutSwitch = simulation.withPerseveringBehavior();
		final int numberOfWinsWithSwitch = simulation.withSwitchingBehavior();
		final long endTime = System.currentTimeMillis();
		System.out.println("Total simulation time: " + (endTime - startTime) + " ms.");
		final Result result = new Result(numberOfWinsWithoutSwitch, numberOfWinsWithSwitch);
		reporter.reportResult(command, result);
	}
}
