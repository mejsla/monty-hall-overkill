package se.mejsla.kd.montyhall;

import java.util.Random;

import se.mejsla.kd.montyhall.io.Command;
import se.mejsla.kd.montyhall.io.Reporter;
import se.mejsla.kd.montyhall.io.Result;
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


	private static void executeCommand(final Command command, final Reporter reporter) {
		final int numberOfRuns = command.getNumberOfRuns();
		final int numberOfDoors = 3;
		final Simulator simulation = new Simulator(numberOfRuns, numberOfDoors, new Random());
		final int numberOfWinsWithoutSwitch = simulation.withPerseveringBehavior();
		final int numberOfWinsWithSwitch = simulation.withSwitchingBehavior();
		final Result result = new Result(numberOfWinsWithoutSwitch, numberOfWinsWithSwitch);
		reporter.reportResult(command, result);
	}
}
