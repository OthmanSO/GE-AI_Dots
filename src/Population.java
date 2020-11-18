
public class Population {

	Bee beesPulation[];
	Bee bestBeeEver;
	int indexOFBestBee = 0;
	public static int MaxStep = 1000;

	public Population(int pop) {
		beesPulation = new Bee[pop];
		for (int i = 0; i < beesPulation.length; i++) {
			beesPulation[i] = new Bee();
		}
	}

	// inherit to babies or offsprings ( crossover )
	public Population(Bee dad) {

	}

	// fitness function that gets the distance between each Bee and target if none
	// of them reached
	// and store reference of it in the bestBeeEver
	// otherwise a Bee reached the target then the one with less steps should be
	// chosen
	// - if more than one has the same fitness the one that occurs first will be
	// token -
	public void myPromisingBaby() {
		Target target;
		float max = 0;
		int maxIndex = 0;
		for (int i = 0; i < beesPulation.length; i++) {

			if (beesPulation[i].isReached()) {

				if (beesPulation[i].max_steps < MaxStep) {
					MaxStep = beesPulation[i].max_steps;

					indexOFBestBee = i;
					bestBeeEver = beesPulation[indexOFBestBee];
				}

			} // if reach

			else {

				// if (Dots_population[i].fitness > max) {
				// max = Dots_population[i].fitness;
				// maxIndex = i;
				// }

			}

		} // for

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
