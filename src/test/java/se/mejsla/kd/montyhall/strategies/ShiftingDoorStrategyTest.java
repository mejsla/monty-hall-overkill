package se.mejsla.kd.montyhall.strategies;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import se.mejsla.kd.montyhall.domain.*;

public class ShiftingDoorStrategyTest extends PickDoorStrategyTest {

	@Override
	protected ShiftingDoorStrategy newStrategy() {
		return new ShiftingDoorStrategy(new Random());
	}

	@Test
	public void pickOtherDoor() throws Exception {
		final Stage stage = new Stage(3, 1);
		final PickDoorStrategy strategy = newStrategy();
		final Optional<Door> door0 = strategy.pickDoor(stage, Optional.empty());
		assertTrue(door0.isPresent());
		final Optional<Door> door1 = strategy.pickDoor(stage, door0);
		assertTrue(door1.isPresent());
		assertNotEquals(door0,  door1);
	}

}
