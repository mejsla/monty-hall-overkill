package se.mejsla.kd.montyhall.io;

import java.io.PrintStream;

public class Reporter {

	private final PrintStream stdout;
	private final PrintStream stderr;

	/*
	 * Takes care of output to specified output streams.
	 */
	public Reporter(final PrintStream stdout, final PrintStream stderr) {
		this.stdout = stdout;
		this.stderr = stderr;
	}

	public void reportResult(final Command command, final Result result) {
		stdout.println("=== Monty Hall Simulation ===");
		stdout.printf("- The contestant who never switched door" + 
				" won %d out of %d games. That is %.1f %% of the games.\n",
				result.getNumberOfWinsWithoutSwitch(), 
				command.getNumberOfRuns(), 
				result.getNumberOfWinsWithoutSwitch() * 100.0 / command.getNumberOfRuns());
		stdout.printf("- The contestant who always switched door" + 
				" won %d out of %d games. That is %.1f %% of the games.\n",
				result.getNumberOfWinsWithSwitch(), 
				command.getNumberOfRuns(), 
				result.getNumberOfWinsWithSwitch() * 100.0 / command.getNumberOfRuns());
	}

	public void reportHelp(final Command command) {
		stdout.println(command.getStatusMessage());
	}

	public void reportError(final Command command) {
		stderr.println(command.getStatusMessage());
		stderr.println("Try \'-help\' for more information.");
	}
}
