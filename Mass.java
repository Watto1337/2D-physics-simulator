import java.lang.Math;

import java.awt.Color;
import java.awt.Polygon;

public class Mass {
	// Member variables
	private Vector pos;
	private Vector vel;
	
	private double rot;
	private double rotVel;
	
	private double density;
	private double area;
	
	private Vector[] vertices;
	private Polygon polygon;
	
	private Color color;
	
	// Constructors
	public Mass(Vector pos, double rot, double density, int color, Vector... vertices) {
		this.pos = new Vector(pos);
		this.vel = new Vector(0, 0);
		
		this.rot = rot;
		this.rotVel = 0;
		
		this.color = new Color(color);
		
		this.vertices = new Vector[vertices.length + 1];
		this.polygon = new Polygon();
		
		this.density = density;
		this.area = 0;
		double centerX = 0;
		double centerY = 0;
		
		if (vertices.length >= 3) {
			// Finding the area and center of mass
			for (int i = 0, j = 1; i < vertices.length; i++, j = (j + 1) % vertices.length) {
				double partialArea = vertices[i].getX() * vertices[j].getY() - vertices[i].getY() * vertices[j].getX();
				
				centerX += partialArea * (vertices[i].getX() + vertices[j].getX());
				centerY += partialArea * (vertices[i].getY() + vertices[j].getY());
				area += partialArea;
			}
			
			centerX /= area * 3;
			centerY /= area * 3;
			area = Math.abs(area / 2);
			
			for (int i = 0; i < vertices.length; i++)
				this.vertices[i] = new Vector(vertices[i].getX() - centerX, vertices[i].getY() - centerY);
			
			this.vertices[vertices.length] = this.vertices[0];
		}
	}
	
	// Generating a mass with random vertices
	public static Mass random(Vector pos, double rot, double density, int color, int numPoints, double radius) {
		Vector[] vertices = new Vector[numPoints];
		
		for (int i = 0; i < numPoints; i++) {
			vertices[i] = new Vector(Math.random() * radius, 0);
			
			vertices[i].rotate(Math.PI * 2 * i / numPoints);
		}
		
		return new Mass(pos, rot, density, color, vertices);
	}
	
	// Methods
	// Move the mass by an amount dependent on time and constant acceleration (gravity)
	public void move(double t, Vector acc) {
		vel.multiply(t);
		pos.add(vel);
		vel.divide(t);
		
		acc.multiply(t * t * 0.5);
		pos.add(acc);
		acc.divide(t * 0.5);
		
		vel.add(acc);
		acc.divide(t);
		
		rot = (rot + rotVel) % (Math.PI * 2);
	}
	
	// Get the distance to another mass
	public double getDistance(Mass m, Vector p) {
		double d = Double.MAX_VALUE;
		
		for (Vector v : vertices) {
			v.rotate(rot);
			v.add(pos);
			
			double x = d;
			
			d = Math.min(d, m.getDistance(v));
			
			if (d != x) p.set(v);
			
			v.subtract(pos);
			v.rotate(-rot);
		}
		
		for (Vector v : m.vertices) {
			v.rotate(m.rot);
			v.add(m.pos);
			
			double x = d;
			
			d = Math.min(d, getDistance(v));
			
			if (d != x) p.set(v);
			
			v.subtract(m.pos);
			v.rotate(-m.rot);
		}
		
		return d;
	}
	
	// Get the distance from a point to the nearest point on the edge of the mass using the following formula:
	// d = ||v - ((v.b)/(||b||^2)) * b||
	public double getDistance(Vector v) {
		v.subtract(pos);
		v.rotate(-rot);
		
		double d = Double.MAX_VALUE;
		
		for (int i = 0; i < vertices.length - 1; i++) {
			Vector a = vertices[i];
			Vector b = new Vector(vertices[i+1]);
			
			// Setting the first point to be the origin
			v.subtract(a);
			b.subtract(a);
			
			// Getting the nearest point on the line to v
			double r = Math.min(Math.max(v.dot(b) / b.lenSquared(), 0), 1);
			b.multiply(r);
			
			v.add(a);
			b.add(a);
			
			// Getting the distance and negating it if it is inside the polygon
			double l = v.dist(b);
			if (l < Math.abs(d))
				d = l * (contains(v) ? -1 : 1);
		}
		
		v.rotate(rot);
		v.add(pos);
		
		return d;
	}
	
	// Detect if the mass contains a given point
	// It does this by finding the number of edges the point is to the left of
	// If that is an odd number, the point is inside the mass
	private boolean contains(Vector v) {
		int c = 0;
		
		for (int i = 0; i < vertices.length - 1; i++) {
			Vector a = vertices[i];
			Vector b = vertices[i + 1];
			
			// The basic equation for determining if a point v is to the left of a line defined by two points a and b:
			// v.x < a.x + (v.y - a.y) * (a.x - b.x) / (a.y - b.y)
			if (((v.getY() > a.getY() && v.getY() < b.getY()) || (v.getY() > b.getY() && v.getY() < a.getY())) && a.getY() != b.getY() &&
				v.getX() < (a.getX() + (v.getY() - a.getY()) * (a.getX() - b.getX()) / (a.getY() - b.getY())))
				c++;
		}
		
		return c % 2 == 1;
	}
	
	// Getters
	public Vector getPos() {return pos;}
	public Vector getVel() {return vel;}
	public double getRot() {return rot;}
	public double getRotVel() {return rotVel;}
	public double getDensity() {return density;}
	public double getArea() {return area;}
	public double getMass() {return density * area;}
	public Vector[] getVertices() {return vertices;}
	public Color getColor() {return color;}
	
	public Polygon getPoly() {
		polygon.npoints = 0;
		
		for (int i = 0; i < vertices.length - 1; i++) {
			vertices[i].rotate(rot);
			vertices[i].add(pos);
			
			polygon.addPoint((int)vertices[i].getX(), (int)vertices[i].getY());
			
			vertices[i].subtract(pos);
			vertices[i].rotate(-rot);
		}
		
		return polygon;
	}
	
	// Setters
	public void setRot(double rot) {this.rot = rot;}
	public void setRotVel(double rotVel) {this.rotVel = rotVel;}
	public void setDensity(double density) {this.density = density;}
	public void setColor(int color) {this.color = new Color(color);}
}
