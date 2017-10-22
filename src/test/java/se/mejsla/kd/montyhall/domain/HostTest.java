package se.mejsla.kd.montyhall.domain;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class HostTest {

	@Test
	public void openDoorWithGoat() throws Exception {
		final int doorCount = 3;
		final int carIndex = 1;
		final Stage stage = new Stage(doorCount, carIndex);
		final Door pickedDoor = stage.getDoor(0);
		final Door carDoor = stage.getDoor(1);
		final Door goatDoor = stage.getDoor(2);
		final Host host = new Host(stage, new Random());
		assertTrue(goatDoor.isClosed());
		host.openDoorWithGoat(pickedDoor);
		assertTrue(goatDoor.isOpen());
		assertTrue(pickedDoor.isClosed());
		assertTrue(carDoor.isClosed());
	}
}
