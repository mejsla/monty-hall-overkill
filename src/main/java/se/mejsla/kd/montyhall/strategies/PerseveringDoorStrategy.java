package se.mejsla.kd.montyhall.strategies;

import java.util.Optional;
import java.util.Random;

import se.mejsla.kd.montyhall.domain.Door;
import se.mejsla.kd.montyhall.domain.PickDoorStrategy;
import se.mejsla.kd.montyhall.domain.Stage;

/*
 * A Contestant that always will stay with her first
 * choice of door when offered to revise her previous choice.
 */
public class PerseveringDoorStrategy extends PickDoorStrategy {

	public PerseveringDoorStrategy(final Random randomGenerator) {
		super(randomGenerator);
	}

	@Override
	public Optional<Door> pickDoor(final Stage stage, final Optional<Door> pickedDoor) {
		return pickedDoor.isPresent() ? pickedDoor : makeNewPick(stage, pickedDoor);
	}
}
