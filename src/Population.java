
public class Population {

	Bee beesPulation[];
	Bee bestBeeEver;
	public static int MaxStep = 1000;

	public Population(int pop) {
		beesPulation = new Bee[pop];
		for (int i = 0; i < beesPulation.length; i++) {
			beesPulation[i] = new Bee();
		}
	}

	// inherit to babies or offsprings ( crossover )
	public Population() {

	}

	// fitness function that gets the distance between each Bee and target if none
	// of them reached
	// and store reference of it in the bestBeeEver
	// otherwise a Bee reached the target then the one with less steps should be
	// chosen
	// - if more than one has the same fitness the one that occurs first will be
	// token -
	public void myPromisingBaby() {
		for (Bee b : beesPulation) {
			if (bestBeeEver.isReached()) {
				if (b.isReached() && b.steps < bestBeeEver.steps)
					bestBeeEver = b;
			} else if (b.isReached())
				bestBeeEver = b;
			else if (b.DistanceToTarget() < bestBeeEver.DistanceToTarget())
				bestBeeEver = b;
		}
	}

	// mutation function that iterates on all dots in the population and delegate it
	// to the mutation in dot class
	public void mutation() {
		for (int i = 0; i < beesPulation.length; i++) {
			beesPulation[i].mutate();
		}

	}

	boolean allDead() {
		for (int i = 0; i < beesPulation.length; i++) {
			if (!beesPulation[i].isDead() && !beesPulation[i].isReached()) {
				return false;
			}
		}

		return true;
	}

}
