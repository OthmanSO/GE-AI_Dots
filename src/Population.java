
public class Population {

	Dot Dots_population[];
	Dot bestdotever;

	public Population(int pop) {
		Dots_population = new Dot[pop];
		for (int i = 0; i < Dots_population.length; i++) {
			Dots_population[i] = new Dot();
		}
	}

	// inherit to babies or offsprings ( crossover )
	public Population(Dot dad) {

	}

	// fitness function that gets the distance between each Dot and target if none
	// of them reached
	// and store reference of it in the bestdotever
	// otherwise a dot reached the target then the one with less steps should be
	// chosen
	// - if more than one has the same fitness the one that occurs first will be
	// token -
	public void myPromisingBaby() {

	}

	// mutation function that iterates on all dots in the population and delegate it
	// to the mutation in dot class
	public void mutation() {

	}

}
