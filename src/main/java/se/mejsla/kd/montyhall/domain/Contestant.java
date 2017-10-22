package se.mejsla.kd.montyhall.domain;

import java.util.Optional;

public class Contestant {

	private final Stage stage;
	private final PickDoorStrategy strategy;
	private Optional<Door> pickedDoor;

	public Contestant(final Stage stage, final PickDoorStrategy strategy) {
		this.stage = stage;
		this.strategy = strategy;
		this.pickedDoor = Optional.empty();
	}

	public Stage getStage() {
		return stage;
	}

	/*
	 * From the stage, randomly pick a closed door.
	 * If the Contestant already has picked a door,
	 * the Contestant may or may not revise
	 * her choice (depending on the type of contestant).
	 */
	public void pickDoor() {
		pickedDoor = strategy.pickDoor(stage, pickedDoor);
	}


	/*
	 * Get the picked Door (see pickDoor())
	 */
	public Optional<Door> getPickedDoor() {
		return pickedDoor;
	}
}
