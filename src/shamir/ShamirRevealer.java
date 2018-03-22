package shamir;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ShamirRevealer {
	private int n = 0;
	private double sum = 0, product = 0;

	public double findSecret(ArrayList<Point> points, double prime) {
		n = points.size();
		Vector<Double> x = new Vector<Double>();
		Vector<Double> y = new Vector<Double>();

		for (Point point : points) {
			x.addElement(point.x);
			y.addElement(point.y);
		}

		for (int i = 0; i < n; i++) {
			product = (double) y.elementAt(i);
			for (int j = 0; j < n; j++) {
				if (i != j) {
					double diff = x.elementAt(j) - x.elementAt(i);
					product = product * x.elementAt(j) / diff;
				}
			}
			sum = sum + product;
		}
		return sum % prime;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		ShamirRevealer revealer = new ShamirRevealer();
		while (true) {

			System.out.println("Hello human!");
			System.out.println("This programs finds a secret");
			System.out.println("Previously shared using Shamir's sharing scheme");
			System.out.println("Write quit at any time to stop the program");
			System.out.print("Enter the prime: ");
			String primeAsString = scanner.nextLine();
			if ("quit".equals(primeAsString)) {
				System.out.println("Exit!");
				break;
			}
			double prime = Double.parseDouble(primeAsString);
			System.out.print("Please enter the number of points you have: ");
			String numberOfPointsString = scanner.nextLine();
			
			if ("quit".equals(numberOfPointsString)) {
				System.out.println("Exit!");
				break;
			}
			System.out.println("Now, please enter every point separating by a comma [ x, y ]");
			System.out.println("Example: 1,347");
			int numberQuantity = 	Integer.parseInt(numberOfPointsString);
			ArrayList<Point> points = new ArrayList<>();
			for (int i = 0; i < numberQuantity; i++) {
				System.out.print("Please enter the point: ");
				String point = scanner.nextLine();
				String[] coordinates = point.split(",");
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				Point newPoint = new Point(x, y, prime);
				points.add(newPoint);
				
			}
			double secret = revealer.findSecret(points, prime);
			System.out.println("Secret is: " + secret );
			
		}

		scanner.close();

	}
}