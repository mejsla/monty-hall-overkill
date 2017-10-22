package se.mejsla.kd.montyhall.domain;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import se.mejsla.kd.montyhall.strategies.PerseveringDoorStrategy;

public class PerseveringContestantTest extends ContestantTest {

	@Override
	protected Contestant newContestant(final Stage stage) {
		final Random randomGenerator = new Random();
		return new Contestant(stage, new PerseveringDoorStrategy(randomGenerator));
	}

	@Test
	public void pickSameDoor() throws Exception {
		final Stage stage = new Stage(5, 3);
		final Contestant contestant = newContestant(stage);
		contestant.pickDoor();
		final Optional<Door> door0 = contestant.getPickedDoor();
		contestant.pickDoor();
		final Optional<Door> door1 = contestant.getPickedDoor();
		assertTrue(door0.isPresent());
		assertEquals(door0,  door1);
	}
}
