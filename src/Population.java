
public class Population {
	
	Bee beesPopulation[];
	Bee bestBeeEver;
	public static boolean reached;
	int popCount;

	public Population(int pop) {
		popCount = pop;
		beesPopulation = new Bee[pop];
		for (int i = 0; i < popCount; i++) {
			beesPopulation[i] = new Bee();
		}
	}

	// inherit to babies or offsprings ( crossover then mutation )
	// bestBee from the past generation is pushed in the first position of the
	// next generation to make sure that the system is not falling back and give
	// worst results
	// If a bee reached then 90% of the population mutate from the bestBee in
	// the past generation and the other 10% are scouts for another routes if found
	// if no bee reached the target yet then 50% only will mutate from bestBee
	// others are scouts in case of misleading fitness function
	public void crosssverAndMutation() {
		myPromisingBaby();
		double lim;
		lim = reached ? 0.9 : 0.5;
		beesPopulation[0] = bestBeeEver;
		for (int index = 1; index < (int) popCount * lim; index++) {
			beesPopulation[index].inheriteFromDad(beesPopulation[0]);
			beesPopulation[index].mutate();
		}
		for (int index = (int) (popCount * lim); index < popCount; index++) {
			beesPopulation[index] = new Bee();
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
		if (bestBeeEver == null)
			bestBeeEver = beesPopulation[0];
		for (Bee b : beesPopulation) {
			if (bestBeeEver.isReached()) {
				if (b.isReached() && b.steps < bestBeeEver.steps)
					bestBeeEver = b;
				reached = true;
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

	public int[][] GetAllPos() {
		int[][] posArray = new int[popCount][];
		for (int i = 0; i < popCount; i++)
			posArray[i] = beesPopulation[i].position;
		return posArray;
	}

	public void updateNextMove() {
		for (Bee b : beesPopulation)
			if(!b.isDead())
				b.nextMove();
	}

}
