package se.mejsla.kd.montyhall.domain;

import java.util.*;
import java.util.stream.*;

public abstract class PickDoorStrategy {

	protected final Random randomGenerator;


	protected PickDoorStrategy(final Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	/*
	 * From the given stage, randomly pick a closed door.
	 * If there is a pickedDoor,
	 * the choice may or may not be revised
	 * (depending on the type of strategy).
	 * @return a picked Door (if possible)
	 */
	public abstract Optional<Door> pickDoor(Stage stage, Optional<Door> pickedDoor);

	/*
	 * Pick a new closed door, but do not pick the
	 * same door if a door is already currently picked.
	 * @return a new pickedDoor (given that there is one to pick)
	 */
	protected Optional<Door> makeNewPick(final Stage stage, final Optional<Door> pickedDoor) {
		final List<Door> closedDoors = getClosedNonpickedDoors(stage, pickedDoor);

		final Optional<Door> newPick;
		if (!closedDoors.isEmpty()) {
			final int index = randomGenerator.nextInt(closedDoors.size());
			newPick =  Optional.of(closedDoors.get(index));
		}
		else {
			newPick = Optional.empty();
		}
		return newPick;
	}

	private static List<Door> getClosedNonpickedDoors(final Stage stage, final Optional<Door> pickedDoor) {
		return IntStream.range(0, stage.getNumberOfDoors())
		.mapToObj(index -> stage.getDoor(index))
		.filter(Door::isClosed)
		.filter(door -> !Optional.of(door).equals(pickedDoor))
		.collect(Collectors.toList());
	}
}
