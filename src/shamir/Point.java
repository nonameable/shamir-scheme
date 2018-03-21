package shamir;

public class Point {
	
	double x;
	double y;
	double prime;
	
	public Point(double x, double y, double prime){
		this.x = x;
		this.y = y;
		this.prime = prime;
	}
	
	public String toString(){
		return "Point is: " + x + "," + y +" $ Prime is: " + prime;
	}

}
