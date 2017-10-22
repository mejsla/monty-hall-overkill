package se.mejsla.kd.montyhall.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StageTest {
	
	@Test
	public void construction() throws Exception {
		final int doorCount = 3;
		final int carIndex = 2;
		final Stage stage = new Stage(doorCount, carIndex);
		assertEquals(doorCount, stage.getNumberOfDoors());
		for (int i = 0; i < doorCount; i++) {
			final Door door = stage.getDoor(i);
			if (i == carIndex) {
				assertEquals(Prize.CAR, door.getConcealment());
			}
			else {
				assertEquals(Prize.GOAT, door.getConcealment());
			}
			assertTrue(door.isClosed());
		}
	}
}
