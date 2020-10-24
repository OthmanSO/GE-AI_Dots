import java.util.ArrayList;
import java.util.Random;

public class Dot {
	int [] position = { 5 , 5 };
	int max_steps;
	ArrayList<Integer> direction_set;
	int steps;
	Dot(){
		steps = 0;
		max_steps = 1000 ;
		direction_set = new ArrayList<Integer>();
		for (int i =0 ; i < max_steps ; i++) {			
			// up down right left
		}
	}
	
	// called by child and given parent void return
	// crossover
	public void inheriteFromDad(Dot dad){
		this.position=dad.position;
		this.max_steps= dad.max_steps;
		for(int dir : direction_set) {
			////
		}
	}
	
	
	// mutation function  
	public void mutate() {
		
	}
	
}
