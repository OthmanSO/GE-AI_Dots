
public class Target {

// target is always at [29][29] steps away 
	static int distanceFromTarget(int pos[]) {
		int distance = (int)Math.sqrt(Math.pow(pos[0] - 29,2) + Math.pow(pos[1] - 29,2)) ;
		return distance;
	}

}
