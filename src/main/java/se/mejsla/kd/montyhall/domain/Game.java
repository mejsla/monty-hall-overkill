package se.mejsla.kd.montyhall.domain;

import java.util.Optional;

public class Game {

	private final Host host;
	private final Contestant contestant;

	public Game(final Host host, final Contestant contestant) {
		assert host.getStage().equals(contestant.getStage());
		this.host = host;
		this.contestant = contestant;
	}

	/*
	 * Play the Monty Hall game.
	 * The contestant picks a door.
	 * The host opens a door and the contestant gets to revise her choice.
	 * @return the prize that's concealed behind the chosen door.
	 */
	public Prize play() {
		contestant.pickDoor();
		final Door firstPick = contestant.getPickedDoor().get();
		host.openDoorWithGoat(firstPick);
	
		contestant.pickDoor();
		final Door revisedPick = contestant.getPickedDoor().get();
		final Prize prize = revisedPick.getConcealment();
		return prize;
	}

	/*
	 * Same as 'play()' if everything has been set up correctly,
	 * but with defaults if something goes wrong:
	 * - If the contestant initially can't pick a door,
	 *   the host wont open a door.
	 * - If the contestant doesn't have a picked door after her
	 *   second attempt, she looses (gets a goat)
	 */
	public Prize play_safe() {
		contestant.pickDoor();
		contestant.getPickedDoor().ifPresent(door -> host.openDoorWithGoat(door));
		contestant.pickDoor();
		final Optional<Prize> prize = contestant.getPickedDoor().map(Door::getConcealment);
		return prize.orElse(Prize.GOAT);
	}
}
