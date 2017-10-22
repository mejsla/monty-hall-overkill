package se.mejsla.kd.montyhall.domain;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;

public abstract class PickDoorStrategyTest {

	protected abstract PickDoorStrategy newStrategy();

	@Test
	public void pickDoorFromStage() throws Exception {
		final Stage stage = new Stage(5, 3);
		final PickDoorStrategy strategy = newStrategy();
		final Optional<Door> pickedDoor = strategy.pickDoor(stage, Optional.empty());
		assertTrue(pickedDoor.isPresent());
		final Door pick = pickedDoor.get();
		assertTrue(IntStream.range(0, stage.getNumberOfDoors())
				.mapToObj(index -> stage.getDoor(index))
				.filter(door -> door.equals(pick))
				.findFirst()
				.isPresent());
	}

	@Test
	public void pickSingleClosedDoor() throws Exception {
		final Stage stage = new Stage(7, 3);
		final PickDoorStrategy strategy = newStrategy();
		// Open all doors
		IntStream.range(0, stage.getNumberOfDoors())
		.mapToObj(index -> stage.getDoor(index))
		.forEach(door -> door.open());
		// And close one of them (in order to force that one to get picked)
		final Door doorToPick = stage.getDoor(5);
		doorToPick.close();
		final Optional<Door> pickedDoor = strategy.pickDoor(stage, Optional.empty());
		assertEquals(doorToPick, pickedDoor.get());
	}

	@Test
	public void makeNewPick() throws Exception {
		final Stage stage = new Stage(3, 1);
		stage.getDoor(2).open(); //Exclude this door from being picked
		final PickDoorStrategy strategy = newStrategy();
		final Optional<Door> door0 = strategy.makeNewPick(stage, Optional.empty());
		assertTrue(door0.isPresent());
		final Optional<Door> door1 = strategy.makeNewPick(stage, door0);
		assertTrue(door1.isPresent());

		assertNotEquals(door0, door1);
		//If the first pick is the first door, the second pick has to be the second door, or vice versa.
		assertTrue(door0.get().equals(stage.getDoor(0)) && door1.get().equals(stage.getDoor(1))
				|| door0.get().equals(stage.getDoor(1)) && door1.get().equals(stage.getDoor(0)));
	}

}

