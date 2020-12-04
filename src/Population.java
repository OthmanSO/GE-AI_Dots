
public class Population {

	Bee beesPopulation[];
	static Bee bestBeeEver;
	public static boolean reached;
	int popCount;

	public Population(int pop) {
		popCount = pop;
		beesPopulation = new Bee[pop];
		for (int i = 0; i < popCount; i++) {
			beesPopulation[i] = new Bee();
		}
		bestBeeEver = beesPopulation[0];
	}

	// inherit to babies or offsprings ( crossover then mutation )
	// bestBee from the past generation is pushed in the first position of the
	// next generation to make sure that the system is not falling back and give
	// worst results
	// If a bee reached then 90% of the population mutate from the bestBee in
	// the past generation and the other 10% are scouts for another routes if found
	// if no bee reached the target yet then 50% only will mutate from bestBee
	// others are scouts in case of misleading fitness function so they have given
	// more chance to be mutated
	public void crosssverAndMutation() {
		double lim;
		lim = reached ? 0.9 : 0.5;
		for (int index = 0; index < popCount; index++) {
			beesPopulation[index] = new Bee(bestBeeEver);
			beesPopulation[index].mutate(index < (int) popCount * lim ? 1 : 5);
		}
	}

	// fitness function that gets the distance between each Bee and target if none
	// of them reached
	// and store reference of it in the bestBeeEver
	// otherwise a Bee reached the target then the one with less steps should be
	// chosen
	// - if more than one has the same fitness the one that occurs first will be
	// token -
	public void myPromisingBaby() {
		reached = bestBeeEver.isReached();
		for (Bee b : beesPopulation) {
			if (reached) {
				if (b.isReached() && b.steps < bestBeeEver.steps) {
					bestBeeEver = b;
				} else
					continue;
			} else if (b.isReached())
				bestBeeEver = b;
			else if (b.DistanceToTarget() < bestBeeEver.DistanceToTarget())
				bestBeeEver = b;
		}
	}

	boolean allDead() {
		for (int i = 0; i < popCount; i++)
			if (!beesPopulation[i].isDead() && !beesPopulation[i].isReached())
				return false;
		return true;
	}

	public void updateNextMove() {
		for (Bee b : beesPopulation)
			if (!(b.isDead() || b.isReached()))
				b.nextMove();
	}

	public void runAll() {
		while (!allDead()) {
			updateNextMove();
		}
		myPromisingBaby();
	}

	public void simulateNextStepForBestBee(GamePane gamePane, int framerate) {
		Bee b = new Bee(bestBeeEver);
		GamePane.b = b;
	}

}
