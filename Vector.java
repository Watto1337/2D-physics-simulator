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
	public void rotate(double theta) {
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);
		
		double t = x;
		x = x * cos - y * sin;
		y = t * sin + y * cos;
	}
	
	public void rotate(double theta, Vector origin) {
		subtract(origin);
		rotate(theta);
		add(origin);
	}
	
	public void rotate(double theta, double originX, double originY) {
		add(-originX, -originY);
		rotate(theta);
		add(originX, originY);
	}
	
	public double len() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double lenSquared() {
		return x*x + y*y;
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
	
	public void multiply(double l) {
		x *= l;
		y *= l;
	}
	
	public void divide(double l) {
		multiply(1 / l);
	}
	
	// Getters
	public double getX() {return x;}
	public double getY() {return y;}
	
	// Setters
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector v) {
		x = v.x;
		y = v.y;
	}
	
	public void setLen(double l) {
		double len = len();
		x *= l / len;
		y *= l / len;
	}
}
