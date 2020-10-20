import java.util.ArrayList;

public class Population {
	
	Dot Dots_population [];
	static Target tar = new Target();
	
	public Population(int pop) {
		Dots_population = new Dot[pop];
		for (Dot d : Dots_population) {
			d = new Dot();
		}
	}

}
