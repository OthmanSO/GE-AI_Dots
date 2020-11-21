import org.graalvm.compiler.hotspot.stubs.OutOfBoundsExceptionStub;
import org.graalvm.compiler.nodes.java.ExceptionObjectNode;

public class MazeGenerator {

	boolean[][] maze;
	private static MazeGenerator mg = new MazeGenerator();

	private MazeGenerator() {
		maze = new boolean[32][32];
	}

	public static MazeGenerator singletonMazeGenerator() {
		return mg;
	}

	public void generate() {

	}

	public boolean isDeadByMaze(int[] position) {
		try {
			return maze[position[0]][position[1]];
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

}
