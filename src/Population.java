
public class Population {
	
	Dot Dots_population [];
	static Target tar = new Target();
	
	public Population(int pop) {
		Dots_population = new Dot[pop];
		for (int i = 0; i < Dots_population.length; i++) {
			Dots_population[i]= new Dot();
		}
	}
	
	

}
