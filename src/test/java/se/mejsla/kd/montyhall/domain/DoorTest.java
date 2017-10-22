package se.mejsla.kd.montyhall.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoorTest {

	@Test
	public void construction() {
		{
			final Prize prize = Prize.CAR;
			final Door door = new Door(prize);
			assertEquals(prize, door.getConcealment());
			assertTrue(door.isClosed());
		}
		{
			final Prize prize = Prize.GOAT;
			final Door door = new Door(prize);
			assertEquals(prize, door.getConcealment());
			assertTrue(door.isClosed());
		}
	}

	@Test
	public void openAndClose() {
		final Door door = new Door(Prize.CAR);
		assertTrue(door.isClosed());
		assertNotEquals(door.isClosed(), door.isOpen());

		door.open();
		assertTrue(door.isOpen());
		assertNotEquals(door.isClosed(), door.isOpen());

		door.close();
		assertTrue(door.isClosed());
		assertNotEquals(door.isClosed(), door.isOpen());
	}
}
