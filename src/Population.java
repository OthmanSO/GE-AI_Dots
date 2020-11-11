
public class Population {

	Dot Dots_population[];
	Dot bestdotever;
   int indexOFBestDot=0;
 public static int MaxStep=1000;
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
Target target ;
float max = 0;
int maxIndex = 0;
for (int i = 0; i<Dots_population.length; i++) {
	
	if(Dots_population[i].isReached()) {
		
		if(Dots_population[i].max_steps<MaxStep) {
		MaxStep=Dots_population[i].max_steps; 
		
		indexOFBestDot=i;
		bestdotever =Dots_population[indexOFBestDot];}
		
	
	}//if reach 
	
	
		else {
			
			
		//	if (Dots_population[i].fitness > max) {
			   // max = Dots_population[i].fitness;
			  //  maxIndex = i;
			 // }
		
	}	

}//for

}

	// mutation function that iterates on all dots in the population and delegate it
	// to the mutation in dot class
	public void mutation() {
		for (int i = 0; i<Dots_population.length; i++) {
			
			Dots_population[i].mutate();
		}
		

	}
	
	 boolean allDead() {
		    for (int i = 0; i< Dots_population.length; i++) {
		      if (!Dots_population[i].isDead() && !Dots_population[i].isReached()) { 
		        return false;
		      }
		    }

		    return true;
		  }

}
