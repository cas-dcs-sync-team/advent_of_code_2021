package de.cas.dcs.sync.adventofcode2021.day2;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.CommandsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {

	private Puzzle2 puzzle;
	private CommandsProvider commandsProvider;

	@BeforeEach
	public void setup() {
		puzzle = new PartTwo().getPuzzle2();
		commandsProvider = new ResourceCommandsProvider();
	}

	@Test
	public void returnsExpectedResultTest() throws IOException {
		// ARRANGE
		int expectedResult = 1842742223;

		// ACT
		int actualResult = puzzle.calculate(commandsProvider);

		// ASSERT
		assertEquals(expectedResult, actualResult);
	}
}