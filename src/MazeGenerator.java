import java.security.SecureRandom;
import java.util.Random;

import javax.swing.JPanel;

public class MazeGenerator extends  JPanel {


	private static int[][] maze;
	int rows = 31; // number of rows of cells in maze, including a wall around edges
	int columns = 31; // number of columns of cells in maze, including a wall around edges



	   final static int backgroundCode = 0;
	    final static int wallCode = 1;
	    final static int pathCode = 2;
	    final static int emptyCode = 3;
	    final static int visitedCode = 4;
	    
	    
	    
	    int n=32;
	   
	public MazeGenerator() {
	
		maze = new int[32][32];
		
		

		


       
	}


	// ---------------------------------------------------------

	void generate() {
		  // Create a random maze.  The strategy is to start with
        // a grid of disconnected "rooms" separated by walls.
        // then look at each of the separating walls, in a random
        // order.  If tearing down a wall would not create a loop
        // in the maze, then tear it down.  Otherwise, leave it in place.
    if (maze == null)
        maze = new int[rows][columns];
    int i,j;
    int emptyCt = 0; // number of rooms
    int wallCt = 0;  // number of walls
    int[] wallrow = new int[(rows*columns)/2];  // position of walls between rooms
    int[] wallcol = new int[(rows*columns)/2];
    for (i = 0; i<rows; i++)  // start with everything being a wall
        for (j = 0; j < columns; j++)
            maze[i][j] = wallCode;
    for (i = 1; i<rows-1; i += 2)  // make a grid of empty rooms
        for (j = 1; j<columns-1; j += 2) {
            emptyCt++;
            maze[i][j] = -emptyCt;  // each room is represented by a different negative number
            if (i < rows-2) {  // record info about wall below this room
                wallrow[wallCt] = i+1;
                wallcol[wallCt] = j;
                wallCt++; }
            if (j < columns-2) {  // record info about wall to right of this room
                wallrow[wallCt] = i;
                wallcol[wallCt] = j+1;
                wallCt++; }}
    repaint();
    int r;
    for (i=wallCt-1; i>0; i--) {
        r = (int)(Math.random() * i);  // choose a wall randomly and maybe tear it down
        tearDown(wallrow[r],wallcol[r]);
        wallrow[r] = wallrow[i];
        wallcol[r] = wallcol[i]; }
    for (i=1; i<rows-1; i++)  // replace negative values in maze[][] with emptyCode
        for (j=1; j<columns-1; j++)
            if (maze[i][j] < 0)
                maze[i][j] = emptyCode;}

synchronized void tearDown(int row, int col) {
        int speedSleep=5000;
	// Tear down a wall, unless doing so will form a loop.  Tearing down a wall
        // joins two "rooms" into one "room".  (Rooms begin to look like corridors
        // as they grow.)  When a wall is torn down, the room codes on one side are
        // converted to match those on the other side, so all the cells in a room
        // have the same code.   Note that if the room codes on both sides of a
        // wall already have the same code, then tearing down that wall would 
        // create a loop, so the wall is left in place.
    if (row % 2 == 1 && maze[row][col-1] != maze[row][col+1]) {
        // row is odd; wall separates rooms horizontally
        fill(row, col-1, maze[row][col-1], maze[row][col+1]);
        maze[row][col] = maze[row][col+1];
    
      /*  try { wait(speedSleep); }
        catch (InterruptedException e) { }
   */
        }
    else if (row % 2 == 0 && maze[row-1][col] != maze[row+1][col]) {
        // row is even; wall separates rooms vertically
        fill(row-1, col, maze[row-1][col], maze[row+1][col]);
        maze[row][col] = maze[row+1][col];
      
     /*   try { wait(speedSleep); }
        catch (InterruptedException e) { }*/
    }
}

void fill(int row, int col, int replace, int replaceWith) {
        // called by tearDown() to change "room codes".
    if (maze[row][col] == replace) {
        maze[row][col] = replaceWith;
        fill(row+1,col,replace,replaceWith);
        fill(row-1,col,replace,replaceWith);
        fill(row,col+1,replace,replaceWith);
        fill(row,col-1,replace,replaceWith);
    }
}
	
	//\**************************************************************************************/\\

	public static boolean isAWall(int row, int col) {
		try {
			//return maze[row][col];
			if ( maze[row][col] == 1  )return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	public static boolean isDeadByMaze(int[] position) {
		return isAWall(position[0], position[1]);
	}

}








