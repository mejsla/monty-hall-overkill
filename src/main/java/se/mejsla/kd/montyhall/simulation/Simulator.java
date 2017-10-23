package se.mejsla.kd.montyhall.simulation;

import java.util.Random;
import java.util.stream.IntStream;

import se.mejsla.kd.montyhall.domain.*;
import se.mejsla.kd.montyhall.strategies.*;

public class Simulator {

	private final int numberOfGames;
	private final int numberOfDoors;
	private final Random randomGenerator;

	public Simulator(final int numberOfGames, final int numberOfDoors, final Random randomGenerator) {
		this.numberOfGames = numberOfGames;
		this.numberOfDoors = numberOfDoors;
		this.randomGenerator = randomGenerator;
	}

	public int withPerseveringBehavior() {
		final int numberOfWins = playBatchOfGamesWith(
				new PerseveringDoorStrategy(randomGenerator));
		return numberOfWins;
	}

	public int withSwitchingBehavior() {
		final int numberOfWins = playBatchOfGamesWith(
				new ShiftingDoorStrategy(randomGenerator));
		return numberOfWins;
	}

	private int playBatchOfGamesWith(final PickDoorStrategy strategy) {
		final long numberOfWins = IntStream.range(0, numberOfGames)
				.parallel() // Comment this line to run sequentially
				.mapToObj(ix ->playSingleGameWith(strategy))
				.filter(prize -> prize == Prize.CAR)
				.count();
		return (int) numberOfWins;

	}

	private Prize playSingleGameWith(final PickDoorStrategy strategy) {
		final int indexOfDoorWithCar = randomGenerator.nextInt(numberOfDoors);
		final Stage stage = new Stage(numberOfDoors, indexOfDoorWithCar); 
		final Host host = new Host(stage, randomGenerator);
		final Contestant contestant = new Contestant(stage, strategy);
		final Game game = new Game(host, contestant);
		final Prize prize = game.play();
		return prize;
	}
}
