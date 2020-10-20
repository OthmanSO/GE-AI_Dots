
public class Target {
	int [] position = { 250 , 10 };
	
	 static int[] distanceFromTarget(int pos[]) {
		 int[] distance = {Math.abs(pos[0]-250) , Math.abs(pos[1]-10)};
		 return distance;
	 }
}
