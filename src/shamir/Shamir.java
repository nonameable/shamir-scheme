package shamir;

import java.util.ArrayList;
import java.util.Random;


public class Shamir {
	// we select the 12th Mersenne Prime as our max prime.
	private static final double MAX_PRIME = 1301;//Math.pow(2, 127) - 1;
	private static final double MIN = 2;
	Random randomGenerator;
	
	
	public Shamir(){
		randomGenerator = new Random();
	}
		
	private double getRandom(){
		double rand = -1;
		int bound = (int) (( (MAX_PRIME - 1)  - MIN) + 1);
		rand = (double) randomGenerator.nextInt(bound) + MIN;
		return rand;
	}
	
	
	
	private double evaluatesPoly(double[] poly, double x){
		double acum = 0;
		for (int i = 0; i < poly.length; i++) {
			acum = acum + poly[i] * Math.pow(x, i);
		}
		return acum % MAX_PRIME;
	}
	
	public ArrayList<Point> get_points(double secret, int n, int k){
		ArrayList<Point> points = new ArrayList<>();
		double[] poly = new double[k];
		poly[0] = secret;
		for(int i = 1; i < poly.length; i++){
			poly[i] = getRandom();
		}
		for(int i = 1; i < n; i++){
			Point newPoint;
			double polyEvaluation = evaluatesPoly(poly, i);
			newPoint = new Point(i, polyEvaluation, MAX_PRIME);
			points.add(newPoint);
		}
		return points;
	}
	
	

	public static void main(String[] args) {
		
		System.out.println("----------- HIDING THE SECRET -----------");
		double secret = 12;
		int n = 5; int k = 2;
		System.out.println("Secret is: "+ secret +". N is: " + n + " and K is: " + k);
		Shamir shamir = new Shamir();
		ArrayList<Point> pointsToShare = shamir.get_points(secret, n, k);
		for (Point point : pointsToShare) {
			System.out.println(point.toString());
		}
		System.out.println("------------REVEALING THE SECRET ----------------");
		Lagrange lagrange = new Lagrange();
		double secretRevealed = lagrange.findSecret(pointsToShare, MAX_PRIME);
		System.out.println("secret Revealed is: " + secretRevealed);
		
	}

}
