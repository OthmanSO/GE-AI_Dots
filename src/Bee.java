import java.util.ArrayList;
import java.util.Random;

public class Bee {
	ArrayList<Integer> direction_set;
	int steps;
	int max_steps;
	Target target;
	boolean dead, reach;
	int[] position = { 0, 0 };
	Bee() {
		dead = false;
		reach = false;
		steps = 0;
		max_steps = 1000;

		direction_set = new ArrayList<Integer>();
		for (int i = 0; i < max_steps; i++) {
			// up down right left
			Random random = new Random();
			direction_set.add(random.nextInt(4));
		}
	}

	// called by child and given parent void return//done
	// crossover
	public void inheriteFromDad(Bee dad) {
		this.steps = 0;
		dead = false;
		reach = false;
		this.position[0]=0;
		this.position[1]=0;
		this.max_steps = dad.reach? dad.steps:dad.max_steps;
		direction_set.clear();
		for (int i = 0; i < this.max_steps; i++) {
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
			}
		}
	}

	// next move add the movement to the next position
	// up down right left
	public void nextMove() {
		steps += 1;
		int dir = 0;
		try {
			dir = direction_set.get(steps);
		} catch (IndexOutOfBoundsException e) {
			dead = true;
			return;
		}
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

		dead = (dead || MazeGenerator.isDeadByMaze(position));
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
