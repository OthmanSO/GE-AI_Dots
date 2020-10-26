import java.util.ArrayList;
import java.util.Random;

public class Dot {
	int[] position = { 5, 5 };
	int max_steps;
	ArrayList<Integer> direction_set;
	int steps;
	boolean dead, reach;

	Dot() {
		dead = false;
		reach = false;
		steps = 0;
		max_steps = 1000;
		direction_set = new ArrayList<Integer>();
		for (int i = 0; i < max_steps; i++) {

			// up down right left
		}
	}

	// called by child and given parent void return//done
	// crossover
	public void inheriteFromDad(Dot dad) {
		this.position = dad.position;
		this.max_steps = dad.max_steps;
		for (int dir : dad.direction_set) {
			this.direction_set.add(dir);
		}
	}

	// mutation function
	public void mutate() {

		double mutationRate = 0.01;// chance that any vector in directions gets changed
		for (int i = 0; i < direction_set.size(); i++) {
			Random random = new Random();
			float rand = random.nextInt(1);
			if (rand < mutationRate) {
				// set this direction as a random direction
				float randomAngle = random.nextInt(4);
			}
		}

	}

	public boolean isDead() {

		if (dead)
			return true;
		else
			return false;// not yet implemented

	}

	public boolean isReached() {

		if (reach)
			return true;
		else
			return false;// not yet implemented

	}
}
