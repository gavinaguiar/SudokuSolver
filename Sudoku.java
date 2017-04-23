public class Sudoku {
	int[][] grid;

	public Sudoku(int[][] grid) {
		this.grid = grid;

		if (!isValid(grid))
			System.out.println("Invalid input");
		else if (solveSudoku(grid)) {
			System.out.println("The solution is found:");
			printGrid(grid);
		} else
			System.out.println("No solution");
	}

	// getting coordinate location of empty cells
	public static int[][] getEmptyCells(int[][] grid) {
		int totalEmptyCells = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (grid[i][j] == 0)
					totalEmptyCells++;

		int[][] emptyCellsList = new int[totalEmptyCells][2];
		int count = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (grid[i][j] == 0) {
					emptyCellsList[count][0] = i;
					emptyCellsList[count++][1] = j;
				}

		return emptyCellsList;
	}

	// Printing sudoku solution
	public static void printGrid(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}

	public static boolean solveSudoku(int[][] grid) {
		int[][] emptyCellList = getEmptyCells(grid); // empty cells list
		int currentCell = 0; // Start from the first free cell
		boolean found = false; // Check if solution is found

		while (!found) {
			int row = emptyCellList[currentCell][0];
			int col = emptyCellList[currentCell][1];
			if (grid[row][col] == 0)
				grid[row][col] = 1; // Inserting first value

			if (isValid(row, col, grid)) {
				if (currentCell + 1 == emptyCellList.length) { // All cells
																// filled
					found = true; // sudoku solved
				} else { // Next empty cell
					currentCell++;
				}
			} else if (grid[row][col] < 9) {
				grid[row][col] = grid[row][col] + 1; // Checking next value

			} else // backtracking
			{
				while (grid[row][col] == 9) {
					grid[row][col] = 0; // reinitializing cell to 0
					if (currentCell == 0) {
						return false; // No possible value
					}
					currentCell--; // Backtracking to previous cell
					row = emptyCellList[currentCell][0];
					col = emptyCellList[currentCell][1];
				}

				grid[row][col] = grid[row][col] + 1; // Check the next possible
														// value
			}
		}

		return true; // Sudoku solved
	}
	
	// checking if cell is valid in the grid
	public static boolean isValid(int[][] grid) {
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				if (grid[row][col] != 0 && !isValid(row, col, grid))
					return false; // invalid

		return true; // valid
	}

	// checking if current cell is valid
	public static boolean isValid(int i, int j, int[][] grid) {
		// Check whether grid[i][j] is valid at the i's row
		for (int column = 0; column < 9; column++)
			if (column != j && grid[i][column] == grid[i][j])
				return false;

		// Check whether grid[i][j] is valid at the j's column
		for (int row = 0; row < 9; row++)
			if (row != i && grid[row][j] == grid[i][j])
				return false;

		// Check whether grid[i][j] is valid in the box
		for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
			for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
				if (row != i && col != j && grid[row][col] == grid[i][j])
					return false; // Invalid

		return true; // Valid
	}


}