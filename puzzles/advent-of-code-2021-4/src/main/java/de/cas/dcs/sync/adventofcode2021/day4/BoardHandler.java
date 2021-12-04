package de.cas.dcs.sync.adventofcode2021.day4;

public class BoardHandler {
	public void markNumber(int number, Board board) {

		for(int i=0; i < board.grid.length; i++) {
			int[] row = board.grid[i];
			for(int j=0; j < row.length; j++) {
				if(board.grid[i][j] == number) {
					board.matched[i][j] = true;
				}
			}
		}
	}

	public boolean isBoardFinished(Board board) {
		boolean hasCompleteColumn = false;
		boolean hasCompleteRow = false;

		for(int i=0; i < board.matched.length; i++) {
			boolean[] row = board.matched[i];
			boolean rowComplete = true;
			for(int j=0; j < row.length; j++) {
				if(!row[j]) {
					rowComplete = false;
					break;
				}
			}
			if(rowComplete) {
				hasCompleteRow = true;
				break;
			}
		}

		for(int i=0; i < board.matched.length; i++) {
			boolean columnComplete = true;
			for(int j=0; j < board.matched.length; j++) {
				if(!board.matched[j][i]) {
					columnComplete = false;
					break;
				}
			}
			if(columnComplete) {
				hasCompleteColumn = true;
				break;
			}
		}

		return hasCompleteColumn || hasCompleteRow;
	}

	public int sumOfAllUnmarkedFields(Board board) {
		int sum = 0;
		for(int i=0; i < board.matched.length; i++) {
			for(int j=0; j < board.matched.length; j++) {
				if(!board.matched[j][i]) {
					sum += board.grid[j][i];
				}
			}
		}

		return sum;
	}
}
