package de.cas.dcs.sync.adventofcode2021.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {
	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day4/day4.file");

	public static void main(String[] args) throws IOException {
		List<String> values = Files.lines(PUZZLE_RESOURCE)
				.collect(Collectors.toList());

		BoardHandler boardHandler = new BoardHandler();
		Game game = new Game();
		Arrays.stream(values.get(0).split(",")).map(s -> Integer.parseInt(s)).forEach(value -> game.numbersToDraw.add(value));

		List<Board> boards = buildBoards(values.subList(2, values.size()));

		Board winnerBoard = null;

		for(int number : game.numbersToDraw.stream().toList()) {
			for(Board board : boards) {
				boardHandler.markNumber(number, board);
				if(boardHandler.isBoardFinished(board)) {
					winnerBoard = board;
				}
			}
			if(winnerBoard != null) {
				int sum = boardHandler.sumOfAllUnmarkedFields(winnerBoard);
				System.out.println(sum * number);
				break;
			}
		};
	}

	private static List<Board> buildBoards(List<String> values) {
		List<Board> boards = new ArrayList<>();

		Board board = new Board();
		int rowCounter = 0;
		for(int i = 0; i< values.size(); i++) {
			String row = values.get(i);
			if(row.isBlank()) {
				board = new Board();
				rowCounter = 0;
				boards.add(board);
			} else {
				List<Integer> columnValues = Arrays.stream(row.split(" ")).filter(s -> !s.isBlank()).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
				for(int j=0; j < columnValues.size(); j++) {
					board.grid[rowCounter][j] = columnValues.get(j);
				}
				rowCounter++;
			}
		}

		return boards;
	}
}
