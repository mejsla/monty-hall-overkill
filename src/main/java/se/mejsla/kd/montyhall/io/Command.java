package se.mejsla.kd.montyhall.io;

public class Command {

	public enum Status {
		OK,
		HELP,
		ERROR
	}

	public static class DefaultArgs {
		public final static int numberOfRuns = 1_000_000;
	}

	private int numberOfRuns = 0;
	private Status status = Status.ERROR;
	private String statusMsg = "Error";

	public Command(final String[] args) {

		if (args.length == 0) {
			numberOfRuns = DefaultArgs.numberOfRuns;
			status = Status.OK;
			statusMsg = "OK";

		}
		else if (args.length == 1 && args[0].equals("-help")) {
			status = Status.HELP;
			statusMsg = "Usage:" + "\'<command> [n]\'\n"
					+ "   or\n"
					+ "      " + "\'<command> -help\'\n"
					+ "where \'n\' is the number of simulations to run of each kind.\n"
					+ "(\'n\' defaults to " + DefaultArgs.numberOfRuns + ")";
		}
		else if (args.length == 1) {
			try {
				numberOfRuns = Integer.parseInt(args[0]);
				if (numberOfRuns > 0) {
					status = Status.OK;
					statusMsg = "OK";
				}
				else {
					statusMsg = "The number of simulations must be positive.";
				}

			}
			catch (final Exception e) {
				statusMsg = "Unknown argument: " + args[0];
			}
		}
		else {
			statusMsg = "Too many arguments.";
		}

	}

	public int getNumberOfRuns() {
		return numberOfRuns;
	}

	public Status getStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMsg;
	}
}
