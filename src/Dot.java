import java.util.ArrayList;
import java.util.Random;

public class Dot {
	int [] position = { 680 , 250 };
	int max_steps;
	ArrayList<int[]> direction_set;
	int steps;
	Dot(){
		steps = 0;
		max_steps = 1000 ;
		max_steps= new Random().nextInt(20);
		direction_set = new ArrayList<int[]>();
		for (int i =0 ; i < max_steps ; i++) {			
			int [] direction = new int[2];
			position[0] =new Random().nextInt(5);
			position[1] =new Random().nextInt(5);
			direction_set.add(direction);
		}
	}
	
	
	public Dot inheriteFromDad(){
		Dot baby = new Dot();
		baby.position=this.position;
		baby.max_steps= this.max_steps;
		for(int[] dir : direction_set) {
			baby.direction_set.add(dir);
		}
		return baby;	
	}
}
