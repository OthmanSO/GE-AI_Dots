
public class Target {

// target is always at [31][31] steps away 
	static int distanceFromTarget(int pos[]) {
		int distance = (int)Math.sqrt(Math.pow(pos[0] - 31,2) + Math.pow(pos[1] - 31,2)) ;
		return distance;
	}

}
