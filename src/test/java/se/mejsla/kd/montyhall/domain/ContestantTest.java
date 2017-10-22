package se.mejsla.kd.montyhall.domain;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;

public abstract class ContestantTest {

	protected abstract Contestant newContestant(Stage stage);
	
	@Test
	public void pickDoorFromStage() throws Exception {
		final Stage stage = new Stage(5, 3);
		final Contestant contestant = newContestant(stage);
		assertFalse(contestant.getPickedDoor().isPresent());
		contestant.pickDoor();
		assertTrue(contestant.getPickedDoor().isPresent());
		final Door pick = contestant.getPickedDoor().get();
		assertTrue(IntStream.range(0, stage.getNumberOfDoors()).mapToObj(index -> stage.getDoor(index)).filter(door -> door.equals(pick)).findFirst().isPresent());
	}

	@Test
	public void pickSingleClosedDoor() throws Exception {
		final Stage stage = new Stage(7, 3);
		final Contestant contestant = newContestant(stage);
		// Open all doors 
		IntStream.range(0, stage.getNumberOfDoors()).mapToObj(index -> stage.getDoor(index)).forEach(door -> door.open());
		// And close one of them (in order to force that one to get picked)
		final Door doorToPick = stage.getDoor(5);
		doorToPick.close();
		contestant.pickDoor();
		final Door pickedDoor = contestant.getPickedDoor().get();
		assertEquals(doorToPick, pickedDoor);
	}

}
