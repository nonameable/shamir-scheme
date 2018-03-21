package shamir;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

public class Lagrange {
	private int n = 0;
	private double sum = 0, product = 0;
	
	
	private  double gcd(double a, double b) {
    BigInteger b1 = BigInteger.valueOf((long) a);
    BigInteger b2 = BigInteger.valueOf((long) b);
    BigInteger gcd = b1.gcd(b2);
    return gcd.intValue();
	}


	public double findSecret(ArrayList<Point> points, double prime){
		n = points.size();
		Vector<Double> xx = new Vector<Double>();
		Vector<Double> yy = new Vector<Double>();
		
		for (Point point : points) {
			xx.addElement(point.x);
			yy.addElement(point.y);
		}
		
		for (int i = 0; i < n; i++) {
			product = (double) yy.elementAt(i);
			for (int j = 0; j <  n; j++) {
				if (i != j) {
					double diff = xx.elementAt(j) - xx.elementAt(i);
					double gcd = gcd(diff, prime);
					product = product * xx.elementAt(j) / diff ;
				}
			}
			sum = sum + product;
		}
		return sum % prime;
	}
}