
public class MazeGenerator {

	boolean[][] maze;
	boolean path, wall;
	private static MazeGenerator mg = new MazeGenerator();

	private MazeGenerator() {
		maze = new boolean[32][32];
		path = true;
		wall = false;
	}

	public static MazeGenerator singletonMazeGenerator() {
		return mg;
	}

	public void generate() {

		// Create a random maze. The strategy is to start with
		// a grid of disconnected "rooms" separated by walls,
		// then look at each of the separating walls, in a random
		// order. If tearing down a wall would not create a loop
		// in the maze, then tear it down. Otherwise, leave it in place.
		int i, j;
		int emptyCt = 0; // number of rooms for path
		int wallCt = 0; // number of walls
		int[] wallrow = new int[32]; // position of walls between paths
		int[] wallcol = new int[32];
		for (i = 0; i < 32; i++) // start with everything being a wall
			for (j = 0; j < 32; j++)
				maze[i][j] = false;// value of wall =false
		for (i = 1; i < 31; i += 2) { // make a grid of empty rooms
			for (j = 1; j < 31; j += 2) {
				emptyCt++;
				maze[i][j] = true; // each room in the path is represented by a true value
				if (i < 30) { // record info about wall below this room
					wallrow[wallCt] = i + 1;
					wallcol[wallCt] = j;
					wallCt++;
				}
				if (j < 30) { // record info about wall to right of this room
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

		if (row % 2 == 1 && maze[row][col - 1] != maze[row][col + 1]) {
			// row is odd; wall separates rooms horizontally
			maze[row][col] = maze[row][col + 1];
		} else if (row % 2 == 0 && maze[row - 1][col] != maze[row + 1][col]) {
			// row is even; wall separates rooms vertically
			maze[row][col] = maze[row + 1][col];

		}
	}

	public boolean isDeadByMaze(int[] position) {
		try {
			return maze[position[0]][position[1]];
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

}
