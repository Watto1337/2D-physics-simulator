import java.lang.Math;

public class Vector {
	// Member variables
	private double x, y;
	
	// Constructors
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	// Methods
	public double len() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double lenSquared() {
		return x*x + y*y;
	}
	
	public void setLen(double l) {
		double len = len();
		x *= l / len;
		y *= l / len;
	}
	
	public double dot(Vector v) {
		return x*v.x + y*v.y;
	}
	
	public double dist(Vector v) {
		return Math.sqrt((x-v.x)*(x-v.x) + (y-v.y)*(y-v.y));
	}
	
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void add(Vector v) {
		x += v.x;
		y += v.y;
	}
	
	public void subtract(Vector v) {
		x -= v.x;
		y -= v.y;
	}
	
	// Getters
	public double getX() {return x;}
	public double getY() {return y;}
	
	// Setters
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
}
