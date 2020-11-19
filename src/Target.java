
public class Target {
	
// target is always at [31][31] steps away 
	static int distanceFromTarget(int pos[]) {
		int distance = Math.abs(pos[0] - 31) + Math.abs(pos[1] - 31) ;
		return distance;
	}

}
