import java.util.ArrayList;
import java.util.Random;

public class Bee {
	int[] position = { 0, 0 };
	int max_steps;
	Target target;
	ArrayList<Integer> direction_set;
	int steps;
	boolean dead, reach;
	MazeGenerator maze;

	Bee() {
		maze = MazeGenerator.singletonMazeGenerator();
		dead = false;
		reach = false;
		steps = 0;
		max_steps = 1000;

		direction_set = new ArrayList<Integer>();
		for (int i = 0; i < max_steps; i++) {
			// up down right left
			direction_set.add((int) Math.random() * 4);
		}
	}

	// called by child and given parent void return//done
	// crossover
	public void inheriteFromDad(Bee dad) {
		this.steps = 0;
		dead = false;
		reach=false;
		this.position = dad.position;
		this.max_steps = dad.max_steps;
		direction_set.clear();
		for (int i = 0; i < max_steps; i++) {
			this.direction_set.add(dad.direction_set.get(i));
		}
	}

	// mutation function
	public void mutate() {

		double mutationRate = 0.05;// chance that any vector in directions gets changed
		for (Integer dir : direction_set) {
			Random random = new Random();
			float rand = random.nextInt(1);
			if (rand < mutationRate) {
				// set this direction as a random direction
				dir = random.nextInt(4);
				System.out.println(dir);
			}
		}
	}

	// next move add the movement to the next position
	// up down right left
	public void nextMove() {
		steps += 1;
		int dir = direction_set.get(steps);
		if (dir == 0) {
			// up
			position[1] = position[1] - 1;
		} else if (dir == 1) {
			// down
			position[1] = position[1] + 1;
		} else if (dir == 2) {
			// right
			position[0] = position[0] + 1;
		} else if (dir == 3) {
			// left
			position[0] = position[0] - 1;
		}
	}

	public boolean isDead() {

		dead = (dead || maze.isDeadByMaze(position));
		return dead;
	}

	public boolean isReached() {
		reach = reach ? true : DistanceToTarget() == 0;
		return reach;
	}

	public int DistanceToTarget() {
		return Target.distanceFromTarget(this.position);
	}
}
