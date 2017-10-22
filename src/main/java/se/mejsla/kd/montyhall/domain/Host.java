package se.mejsla.kd.montyhall.domain;

import java.util.*;
import java.util.stream.*;

public class Host {

	private final Stage stage;
	private final Random randomGenerator;

	public Host(final Stage stage, final Random randomGenerator) {
		this.stage = stage;
		this.randomGenerator = randomGenerator;
	}

	public Stage getStage() {
		return stage;
	}

	/*
	 * On the stage, open a closed door that conceals a goat under the 
	 * condition that the door hasn't been picked by the contestant.
	 */
	public void openDoorWithGoat(final Door pickedDoor) {
		final List<Door> validDoors = IntStream
				.range(0, stage.getNumberOfDoors())
				.mapToObj(index -> stage.getDoor(index))
				.filter(Door::isClosed)
				.filter(door -> !door.equals(pickedDoor))
				.filter(door -> door.getConcealment() == Prize.GOAT)
				.collect(Collectors.toList());
		if (validDoors.size() > 0) {
			final Door door = validDoors.get(
					randomGenerator.nextInt(validDoors.size()));
			door.open();
		}
	}

}
