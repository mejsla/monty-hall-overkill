package se.mejsla.kd.montyhall.io;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.junit.Test;

public class ReporterTest {

	@Test
	public void reportResult() throws Exception {

		final String[] args = {"10"};
		final Command command = new Command(args);

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final Reporter reporter = new Reporter(new PrintStream(out), new PrintStream(err));

		final Result result = new Result(3, 6);
		reporter.reportResult(command, result);

		final String outString = new String(out.toByteArray(), StandardCharsets.UTF_8);
		final String errString = new String(err.toByteArray(), StandardCharsets.UTF_8);
		
		final Pattern outPattern = Pattern.compile(".*\\s3\\s.*.*\\s10\\s.*.*\\s30\\.0 %.+\\s6\\s.*\\s10\\s.*.*\\s60\\.0 %.+");
		assertTrue(outPattern.matcher(outString.replace('\n', '_')).find());
		assertTrue(errString.isEmpty());
	}


	@Test
	public void reportHelp() throws Exception {
		final String[] args = {"-help"};
		final Command command = new Command(args);

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final Reporter reporter = new Reporter(new PrintStream(out), new PrintStream(err));

		reporter.reportHelp(command);

		final String outString = new String(out.toByteArray(), StandardCharsets.UTF_8);
		final String errString = new String(err.toByteArray(), StandardCharsets.UTF_8);
		
		final Pattern outPattern = Pattern.compile("Usage.+where");
		assertTrue(outPattern.matcher(outString.replace('\n', '_')).find());
		assertTrue(errString.isEmpty());
	}

	@Test
	public void reportError() throws Exception {
		final String[] args = {"some bad args"};
		final Command command = new Command(args);

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final Reporter reporter = new Reporter(new PrintStream(out), new PrintStream(err));

		reporter.reportError(command);

		final String outString = new String(out.toByteArray(), StandardCharsets.UTF_8);
		final String errString = new String(err.toByteArray(), StandardCharsets.UTF_8);
		
		assertTrue(outString.isEmpty());
		assertTrue(errString.contains(command.getStatusMessage()));
	}

}
