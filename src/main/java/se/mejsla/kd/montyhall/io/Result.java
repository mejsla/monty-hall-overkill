package se.mejsla.kd.montyhall.io;

public class Result {

	private final int numberOfWinsWithoutSwitch;
	private final int numberOfWinsWithSwitch;

	public Result(final int numberOfWinsWithoutSwitch, final int numberOfWinsWithSwitch) {
		this.numberOfWinsWithoutSwitch = numberOfWinsWithoutSwitch;
		this.numberOfWinsWithSwitch = numberOfWinsWithSwitch;
	}

	public int getNumberOfWinsWithoutSwitch() {
		return numberOfWinsWithoutSwitch;
	}

	public int getNumberOfWinsWithSwitch() {
		return numberOfWinsWithSwitch;
	}
}
