package se.mejsla.kd.montyhall.domain;

import java.util.stream.IntStream;

/*
 * The stage in the Monty Hall game show.
 * The stage contains a number of doors, and exactly one 
 * door conceals a car while all other doors conceal goats.
 * The carIndex specifies the index of the door that conceals the car.
 */
public class Stage {
	private final Door[] doors;

	/*
	 * @param doorCount the number of doors on this stage (usually 3)
	 * @param carIndex	the zero-based index of the door with the car
	 */
	public Stage(final int doorCount, final int carIndex) {
		assert doorCount > 2; 
		assert carIndex >= 0 && carIndex < doorCount;

		doors = new Door[doorCount];
		IntStream.range(0, doorCount)
		.forEach(i -> {
			final Prize prize =	i==carIndex ? Prize.CAR : Prize.GOAT;
			doors[i] = new Door(prize);
		});
	}

	public int getNumberOfDoors() {
		return doors.length;
	}

	public Door getDoor(final int index) {
		return doors[index];
	}

}
