
public class MazeGenerator {

	static boolean[][] maze;
	static int[][] maz;
	private static MazeGenerator mg = new MazeGenerator();
	int rows = 32; // number of rows of cells in maze, including a wall around edges
	int columns = 32; // number of columns of cells in maze, including a wall around edges

	final static int wallCode = 0;
	final static int pathCode = 1;

	public MazeGenerator() {
		maze = new boolean[32][32];
		maz = new int[32][32];

	}

	/*
	 * public static void main(String[] args) { System.out.print("hi print \n");
	 * MazeGenerator m = new MazeGenerator(); // m.generate(); m.printMaz();
	 * 
	 * }
	 */

	// ---------------------------------------------------------

	void generate() {
		// Create a random maze. The strategy is to start with
		// a grid of disconnected "rooms" separated by walls,
		// then look at each of theseparating walls, in a random
		// order. If tearing down a wall would not create a loop
		// in the maze, then tear it down. Otherwise, leave it in place.
		int i, j;
		int emptyCt = 0;
		// number of rooms
		int wallCt = 0;
		// number of walls
		int[] wallrow = new int[(rows * columns) / 2]; // position of walls between rooms
		int[] wallcol = new int[(rows * columns) / 2];
		for (i = 0; i < rows; i++)
			// start with everything being a wall
			for (j = 0; j < columns; j++)
				maz[i][j] = wallCode;
		for (i = 1; i < rows - 1; i += 2) { // make a grid of empty rooms
			for (j = 1; j < columns - 1; j += 2) {
				emptyCt++;
				maz[i][j] = -emptyCt;
				// each room is represented by a different negative number
				if (i < rows - 2) { // record info about wall below this room
					wallrow[wallCt] = i + 1;
					wallcol[wallCt] = j;
					wallCt++;
				}
				if (j < columns - 2) { // record info about wall to right of this room
					wallrow[wallCt] = i;
					wallcol[wallCt] = j + 1;
					wallCt++;
				}
			}
		}

		int r;
		for (i = wallCt - 1; i > 0; i--) {
			r = (int) (Math.random() * i); // choose a wall randomly and maybe tear it down
			tearDown(wallrow[r], wallcol[r]);
			wallrow[r] = wallrow[i];
			wallcol[r] = wallcol[i];
		}

	}

	void tearDown(int row, int col) {
		// Tear down a wall, unless doing so will form a loop. Tearing down a wall
		// joins two "rooms" into one "room". (Rooms begin to look like path
		// as they grow.) When a wall is torn down, the room codes on one side are
		// converted to match those on the other side, so all the cells in a room
		// have the same code. Note that if the room codes on both sides of a
		// wall already have the same code, then tearing down that wall would
		// create a loop, so the wall is left in place.
		if (row % 2 == 1 && maz[row][col - 1] != maz[row][col + 1]) {
			// row is odd; wall separates rooms horizontally
			maz[row][col] = maz[row][col + 1];

		} else if (row % 2 == 0 && maz[row - 1][col] != maz[row + 1][col]) {
			// row is even; wall separates rooms vertically

			maz[row][col] = maz[row + 1][col];

		}
		setMaze();
	}
//----------------------------------------------------

	public void setMaze() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {

				if (maz[i][j] == 0) {
					maze[i][j] = false;
				} else
					maze[i][j] = true;
			}
		}
	}

	public void printMaz() {
		setMaze();
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				if (j == 0) {
					System.out.print("\n");
				}
				System.out.print(maze[i][j]);
				System.out.print("\t |");
			}
		}
	}

	public static boolean isAWall(int row, int col) {
		try {
			return maze[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

	public static boolean isDeadByMaze(int[] position) {
		return isAWall(position[0], position[1]);
	}

}
