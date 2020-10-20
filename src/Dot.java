import java.util.ArrayList;
import java.util.Random;

public class Dot {
	int [] position = { 680 , 250 } ;
	int directions_num ;
	ArrayList<int[]> direction_set = new ArrayList<int[]>();
	int steps;
	Dot(){
		directions_num= new Random().nextInt(20);
		for (int i =0 ; i <directions_num ; i++) {
			int [] direction = new int[2];
			position[0] =new Random().nextInt(5);
			position[1] =new Random().nextInt(5);
			direction_set.add(direction);
		}
		
		
	}
}
