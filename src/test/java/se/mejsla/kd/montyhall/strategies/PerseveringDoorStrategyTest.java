package se.mejsla.kd.montyhall.strategies;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
//@@@ Måste testa task list :-)

import se.mejsla.kd.montyhall.domain.*;

public class PerseveringDoorStrategyTest extends PickDoorStrategyTest {
//TODO: what is there to do?

	@Override
	protected PerseveringDoorStrategy newStrategy() {
		return new PerseveringDoorStrategy(new Random());
	}

	@Test
	public void pickSameDoor() throws Exception {
		final Stage stage = new Stage(5, 3);
		final PerseveringDoorStrategy strategy = newStrategy();
		final Optional<Door> doorToPick = Optional.of(stage.getDoor(2));
		final Optional<Door> pickedDoor = strategy.pickDoor(stage, doorToPick);
		assertEquals(doorToPick,  pickedDoor);
	}
}
