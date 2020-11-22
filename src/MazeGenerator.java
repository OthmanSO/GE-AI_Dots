import java.security.SecureRandom;
import java.util.Random;

public class MazeGenerator {

	private static boolean[][] maze;
	private static int[][] maz;
	int rows = 32; // number of rows of cells in maze, including a wall around edges
	int columns = 32; // number of columns of cells in maze, including a wall around edges



	   final static int backgroundCode = 0;
	    final static int wallCode = 1;
	    final static int pathCode = 2;
	    final static int emptyCode = 3;
	    final static int visitedCode = 4;
	    
	    
	    
	    int n=30;
	    private boolean[][] north;     // is there a wall to north of cell i, j
	    private boolean[][] east;
	    private boolean[][] south;
	    private boolean[][] west;
	    private boolean[][] visited;
	    private boolean done = false;
	public MazeGenerator() {
		maze = new boolean[32][32];
		maz = new int[32][32];
		
		
		
		//-------
		 // initialize border cells as already visited
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }


        // initialze all walls as present
        north = new boolean[n+2][n+2];
        east  = new boolean[n+2][n+2];
        south = new boolean[n+2][n+2];
        west  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < n+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
	}


	// ---------------------------------------------------------
/*
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
		
		for (i=1; i<rows-1; i++)  // replace negative values in maze[][] with emptyCode
            for (j=1; j<columns-1; j++)
                if (maz[i][j] < 0)
                    maz[i][j] = emptyCode;
        synchronized(this) {
            try { wait(1000); }
            catch (InterruptedException e) { }
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
	
	}*/
//----------------------------------------------------
	// generate the maze
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
            	Random random = new Random();
            int	r = random.nextInt(4);
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    public void generate() {
        generate(30,30);

/*
        // delete some random walls
        for (int i = 0; i < n; i++) {
            int x = 1 + StdRandom.uniform(n-1);
            int y = 1 + StdRandom.uniform(n-1);
            north[x][y] = south[x][y+1] = false;
        }

        // add some random walls
        for (int i = 0; i < 10; i++) {
            int x = n/2 + StdRandom.uniform(n/2);
            int y = n/2 + StdRandom.uniform(n/2);
            east[x][y] = west[x+1][y] = true;
        }
*/
     
    }


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//\**************************************************************************************/\\

	public static boolean isAWall(int row, int col) {
		try {
			//return maze[row][col];
			if ( maz[row][col] == 1  )return true ;
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
		return false;
	}

	public static boolean isDeadByMaze(int[] position) {
		return isAWall(position[0], position[1]);
	}

}








