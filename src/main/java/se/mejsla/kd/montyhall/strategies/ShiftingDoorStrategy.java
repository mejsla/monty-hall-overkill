package se.mejsla.kd.montyhall.strategies;

import java.util.*;

import se.mejsla.kd.montyhall.domain.*;

/*
 * A Contestant that always will shift to another door given
 * the chance to revise her previous choice
 */
public class ShiftingDoorStrategy extends PickDoorStrategy {

	public ShiftingDoorStrategy(final Random randomGenerator) {
		super(randomGenerator);
	}

	/*
	 * @return another Door than pickedDoor (given that there such a closed Door)
	 */
	@Override
	public Optional<Door> pickDoor(final Stage stage, final Optional<Door> pickedDoor) {
		return makeNewPick(stage, pickedDoor);
	}
}
